<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>寝室得分</title>
	<link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/public/css/table.css" rel="stylesheet">
	<style>
		.badge {
	    		position: absolute;
	    		right: -10px;
	    		top: 15px;
	    		font-size: 1.2em !important;
	    		background-color: #52a4db !important;
	    	}
	</style>
</head>
<body>
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<!-- 修改得分模态框（Modal） -->
	<div class="modal fade" id="scoreModal" tabindex="-1" role="dialog" aria-labelledby="scoreModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="scoreModalLabel">修改得分</h4>
	            </div>
	            <div class="modal-body">
	            	<form id="dormScoreForm" method="post" class="form-horizontal" role="form">
						<input id="scoreId" type="number" name="scoreId" hidden/>
						<div class="form-group">
							<label for="score" class="col-sm-3 control-label">分数：</label>
							<div class="col-sm-8">
								<input class="form-control" type="number" name="score"/>
					    	</div>
						</div>
					</form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" id="scoreSubmitBtn">提交更改</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	
	<div style="margin:20px 50px">
		<c:if test="${apartId == 0 || floorDormNo == 0}">
			<div class="panel">
				<div class="panel-body">
					<span>您还未加入任何寝室，请联系公寓管理员</span>
				</div>
			</div>
		</c:if>
		<c:if test="${apartId != 0 && floorDormNo != 0}">
			<div class="row">
				<c:if test="${showTop}">
				<div class="col-sm-9">
				</c:if>
					<div class="row" style="text-align:center">
						<label>我的寝室得分</label>
					</div>
				    <div id="main" style="height: 400px; border: 1px solid #ccc; padding: 10px;"></div>
				    
				    <table class="table" style="margin-top:20px">
					    <thead>
					        <tr>
					            <th>寝室号</th>
					            <th>分数</th>
					            <th>打分者</th>
					            <th>打分时间</th>
					            <shiro:hasPermission name="score:update">
					            	<th>操作</th>
					            </shiro:hasPermission>
					        </tr>
					    </thead>
					    <tbody>
					    	<c:if test="${empty oneDormScores}">
					    		<tr>
					    			<td colspan="4" style="text-align:center">寝室该没有任何得分j！</td>
					    		</tr>
					    	</c:if>
					        <c:forEach items="${oneDormScores}" var="score">
					            <tr>
					                <td>${floorDormNo}</td>
					                <td>${score.score}</td>
					                <td>${score.staffName}</td>
					                <td><fmt:formatDate value="${score.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
					                <shiro:hasPermission name="score:update">
						            	<td>
						            		<button class="btn btn-default" data-toggle="modal" data-target="#scoreModal" onClick="updateScore(${score.id})"><i class="fa fa-edit"></i></button>
						            		<button class="btn btn-danger" onClick="deleteScore(${score.id})"><i class="fa fa-trash-o"></i></button>
						            	</td>
						            </shiro:hasPermission>
					            </tr>
					        </c:forEach>
					    </tbody>
					</table>
					<c:if test="${allCount != 0}">
						<ul class="pagination tablePage">
						    <li><a href="${pageContext.request.contextPath}/score/${apartId}/dorm/${floorDormNo}?start=${start-10}">&laquo;</a></li>
						    <c:forEach begin="0" end="${allCount-1}" var="item" step="10">
						    	<li value="${item/10+1}"><a href="${pageContext.request.contextPath}/score/${apartId}/dorm/${floorDormNo}?start=${item}"><fmt:formatNumber type="number" value="${item/10+1}" maxFractionDigits="0"/></a></li>
						    </c:forEach>
						    <li><a href="${pageContext.request.contextPath}/score/${apartId}/dorm/${floorDormNo}?start=${start+10}">&raquo;</a></li>
						</ul>
					</c:if>
				</div>
				<c:if test="${showTop}">
				<div class="col-sm-2" style="margin-left:20px">
					<div class="row" style="text-align:center">
						<label>今日排行</label>
					</div>
					<% int j=0; %>
					<c:if test="${empty dayTopScoreList}">
						<div class="panel">
							<div class="panel-body">
								<span>今日未有任何分数记录</span>
							</div>
						</div>
					</c:if>
					<c:forEach items="${dayTopScoreList}" var="score">
						<div class="row">
							<a href="${pageContext.request.contextPath}/score/${score.apartId}/dorm/${score.floorDormNo}">
								<div class="panel panel-default">
									<div class="panel-body">
					                   <label class="col-sm-6 control-label"><%=j+1 %></label>
					                   <span>${score.floorDormNo}</span>
										<c:if test="${score.avgScore < 60}">
				                      	 	<span class="badge" style="background-color:#ee9e7e !important">
				                      	 		<fmt:formatNumber type="number" value="${score.avgScore}" maxFractionDigits="2"/>
				                      	 	</span>
				                        </c:if>
				                        <c:if test="${score.avgScore < 80 && score.avgScore >= 60}">
				                      	 	<span class="badge" style="background-color:#f4db59 !important">
				                      	 		<fmt:formatNumber type="number" value="${score.avgScore}" maxFractionDigits="2"/>
				                      	 	</span>
				                        </c:if>
				                        <c:if test="${score.avgScore < 90 && score.avgScore >= 80 }">
											<span class="badge">
					                			<fmt:formatNumber type="number" value="${score.avgScore}" maxFractionDigits="2"/>
					               			</span>
				               			</c:if>
				               			<c:if test="${score.avgScore >= 90}">
											<span class="badge" style="background-color:#9fe8ba !important">
					                			<fmt:formatNumber type="number" value="${score.avgScore}" maxFractionDigits="2"/>
					               			</span>
				               			</c:if>
				               		</div>
								</div>
							</a>
						</div>
						<% j++; %>
					</c:forEach>
				</div>
			</div>
			</c:if>
		</c:if>
	</div>
	<script src="<c:url value='/public/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/public/js/echarts.common.min.js'/>"></script> 
	<script src="${pageContext.request.contextPath}/public/js/bootstrap.min.js" ></script>
	<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
    <script>
    	$(function() {
    		$("#scoreSubmitBtn").click(function() {
    			$.ajax({
    				type: "POST",
    				datatype: "text",
    				url: getRootPath() + "/score/update",
    				data: $("#dormScoreForm").serializeArray(),
    				contentType: "application/x-www-form-urlencoded",
    				success: function(data) {
    					if(data == "success") {
    						swal("成功！", "新增成功", "success");
    						$('#scoreModal').modal('hide');
    					} else {
    						swal("失败！", "新增失败", "error");
    					}
    				},
    				error: function() {
    					swal("错误！", "发生错误", "error");
    		        }
    			});
    		});
    	});
	    var start = Number('${start}');
		var pageNum = start/10+1;
		$(".tablePage li").eq(pageNum).addClass("active");
		if(start <= 0) {
			$(".tablePage li").eq(0).find("a").attr("href","#");
			$(".tablePage li").eq(0).addClass("disabled");
		}
		if(start+10 >= Number('${allCount}')){
			$(".tablePage li").eq(-1).find("a").attr("href","#");
			$(".tablePage li").eq(-1).addClass("disabled");
		}
		
		function getRootPath() {//获得根目录
			var strFullPath = window.document.location.href;
			var strPath = window.document.location.pathname;
			var pos = strFullPath.indexOf(strPath);
			var prePath = strFullPath.substring(0, pos);
			var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
			return (prePath + postPath);
		}
		
		function updateScore(scoreId) {
			$("#scoreId").val(scoreId);
		}
		function deleteScore(scoreId) {
			swal({ 
				title: "确定删除该得分记录吗？", 
				text: "删除后将无法恢复", 
				type: "info", 
				showCancelButton: true, 
				closeOnConfirm: false
			},
			function(){
				$.ajax({
					type: "POST",
					datatype: "json",
					url: getRootPath() + "/score/"+scoreId+"/delete",
					contentType: "application/x-www-form-urlencoded",
					success: function(data) {
						if(data == "success") {
    						swal("成功！", "删除成功", "success");
    					} else {
    						swal("失败！", "删除失败", "error");
    					}
					},
					error: function() {
						swal("失败！", "发生错误", "error");
			        }
				});
			});
		}
		
        var siteArray = new Array();
        var xArray = new Array();
        var i = "${oneDormScores.size()-1}";
        var year = "";
        var day= "";
        
        <c:forEach var="item" items="${oneDormScores}">
            siteArray[i]= new Object();
            year = "${item.createTime}".slice(-4);
            day = "${item.createTime}".slice(4, -18);
            siteArray[i].name = day + " " + year;
            siteArray[i].value = ${item.score};
            xArray[i] = day + " " + year;
            i--;
        </c:forEach>
        
        $().ready(function() {
            var myChart = echarts.init(document.getElementById('main'));
            //图表显示提示信息
            myChart.showLoading();
            //定义图表options
            var options = {
                title: {
                    text: "分数趋势图",
                    subtext: "hznu"
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ["${floorDormNo}"]
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: false
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        data: xArray
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        splitArea: { show: true }
                    }
                ],
                series : [  
                    {  
                        name: "${floorDormNo}",  
                        type: 'line',
                        data : siteArray
                          
                    }
                ]
            };

            myChart.hideLoading();
            myChart.setOption(options);

        });
    </script>
</body>
</html>