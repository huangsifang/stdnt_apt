<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>我的维修</title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../navbar.jsp"></jsp:include>

<!-- 完成维修模态框（Modal） -->
<div class="modal fade" id="repairRecordModal" tabindex="-1" role="dialog" aria-labelledby="repairRecordModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="repairRecordModalLabel">完成维修</h4>
            </div>
            <div class="modal-body">
			    <form id="repairRecordForm" action="" method="post" class="form-horizontal" role="form">
			    	<input type="text" class="form-control" id="repairId" name="repairId" style="display:none"/>
			    	<div class="form-group">
						<label for="repairTime" class="col-sm-3 control-label">维修时间：</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="repairTime" name="repairTime" placeholder="请选择维修时间">
				    	</div>
					</div>
			    </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="repairRecordBtn">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="container">
	<div class="card">
		<div class="content table-responsive">
			<table class="table">
			    <thead>
			        <tr>
			        	<th>公寓名</th>
			            <th>寝室号</th>
			            <th>维修类型</th>
			            <th>申请者</th>
			            <th>申请手机号</th>
			            <th>申请时间</th>
			            <th>备注</th>
			            <th>接单时间</th>
			            <th>维修时间</th>
			            <th>状态</th>
			            <th>操作</th>
			        </tr>
			    </thead>
			    <tbody>
			    	<c:if test="${empty myRepairRecordList}">
			    		<tr>
			    			<td colspan="10" style="text-align:center">您还没有接受任何维修！</td>
			    		</tr>
					</c:if>
			        <c:forEach items="${myRepairRecordList}" var="record">
			            <tr>
			            	<td>${record.repair.apartName}</td>
			                <td>${record.repair.dormNo}</td>
			                <td>${record.repair.repairTypeName}</td>
			                <td>${record.repair.applicantName}</td>
			                <td>${record.repair.applicantTel}</td>
			                <td><fmt:formatDate value="${record.repair.applyTime}" pattern="yyyy-MM-dd HH:mm" /></td>
			                <td>${record.repair.remark}</td>
			                <td><fmt:formatDate value="${record.acceptTime}" pattern="yyyy-MM-dd HH:mm" /></td>
			                <td>
			                	<c:if test="${not empty record.repairTime}">
			                		<fmt:formatDate value="${record.repairTime}" pattern="yyyy-MM-dd HH:mm" />
			                	</c:if>
			                </td>
			                <c:if test="${record.state == 1}">
			                	<td>
				                	<span class="label label-info">已接单 </span>
			                	</td>
			                </c:if>
			                <c:if test="${record.state == 2}">
			                	<td><span class="label label-default">已结束</span></td>
			                </c:if>
		                	<td>
		                		<c:if test="${record.state == 1}">
			                		<button style="margin-left:5px" class="btn btn-primary" data-toggle="modal" data-target="#repairRecordModal" type="button" onClick="finishOrder(${record.repairId})"><i class="fa fa-check-square-o"></i>  完成</button>
				                	<button class="btn btn-danger" onClick="deleteRepairRecord(${record.repairId})"><i class="fa fa-trash-o"></i></button>
			                	</c:if>
		                	</td>
			                
			            </tr>
			        </c:forEach>
			    </tbody>
			</table>
			<c:if test="${allCount != 0}">
				<ul class="pagination tablePage">
				    <li><a href="${pageContext.request.contextPath}/myRepair?start=${start-10}">&laquo;</a></li>
				    <c:forEach begin="0" end="${allCount-1}" var="item" step="10">
				    	<li value="${item/10+1}"><a href="${pageContext.request.contextPath}/myRepair?start=${item}"><fmt:formatNumber type="number" value="${item/10+1}" maxFractionDigits="0"/></a></li>
				    </c:forEach>
				    <li><a href="${pageContext.request.contextPath}/myRepair?start=${start+10}">&raquo;</a></li>
				</ul>
			</c:if>
		</div>
	</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/public/js/bootstrap-datetimepicker.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/bootstrap-datetimepicker.zh-CN.js" ></script>
<script>
function finishOrder(repairId) {
	$("#repairId").val(repairId);
}
$(function() {
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
	
	$("#repairRecordBtn").click(function() {
		$.ajax({
			type: "POST",
			datatype: "json",
			data: $("#repairRecordForm").serializeArray(),
			url: getRootPath() + "/myRepair/record/finish",
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data != "处理失败!") {
					swal("成功！", data, "success");
				} else if(data == "处理失败!") {
					swal("成功！", data, "error");
				}
				$('#repairRecordModal').modal('hide');
			},
			error: function() {
	        	alert('error');
	        }
		});
	});
});

function deleteRepairRecord(repairId) {
	swal({ 
		title: "确定删除该维修吗？", 
		text: "删除后将无法恢复", 
		type: "info", 
		showCancelButton: true, 
		closeOnConfirm: false
	},
	function(){
		$.ajax({
			type: "POST",
			datatype: "json",
			url: getRootPath() + "/myRepair/"+repairId+"/record/delete",
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data == "") {
					swal("删除！", "您没有权限删除该维修!", "error");
				} else if(data == "删除成功!") {
					swal("成功！", data, "success");
				} else if(data == "删除失败!") {
					swal("成功！", data, "error");
				}
			},
			error: function() {
	        	alert('error');
	        }
		});
	});
}

function getRootPath() {//获得根目录
	var strFullPath = window.document.location.href;
	var strPath = window.document.location.pathname;
	var pos = strFullPath.indexOf(strPath);
	var prePath = strFullPath.substring(0, pos);
	var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
	return (prePath + postPath);
}

$("#repairTime").datetimepicker({
	language:  'zh-CN', 
	format:'yyyy-mm-dd', 
	weekStart: 1, /*以星期一为一星期开始*/
	todayBtn:  1,
	autoclose: 1, 
	minView:2, /*精确到天*/
	pickerPosition: "bottom-left" 
}).on("changeDate",function(ev){  //值改变事件
	
});
</script>
</html>