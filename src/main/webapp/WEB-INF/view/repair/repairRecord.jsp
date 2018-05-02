<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>维修记录</title>
    <link href="${pageContext.request.contextPath}/public/css/jquery.eeyellow.Timeline.css" rel="stylesheet">
    <style>
    	fieldset {
		  padding-bottom: 20px !important;
		  border-bottom: 1px dashed #eee !important;
		  margin-bottom: 20px !important;
		}
		fieldset.last-child,
		fieldset:last-child {
		  border-bottom: 0 !important;
		}
		fieldset .form-group {
		  margin-bottom: 0 !important;
		}
		.change_link{
			width: 100%;
            font-size: 16px;
            padding-bottom: 10px;
            border-top: 1px solid rgb(219, 229, 232);
            -webkit-border-radius: 0 0  5px 5px;
               -moz-border-radius: 0 0  5px 5px;
                    border-radius: 0 0  5px 5px;
            background: rgb(225, 234, 235);
            background: -moz-repeating-linear-gradient(-45deg, 
            rgb(247, 247, 247) , 
            rgb(247, 247, 247) 15px, 
            rgb(225, 234, 235) 15px, 
            rgb(225, 234, 235) 30px, 
            rgb(247, 247, 247) 30px
            );
            background: -webkit-repeating-linear-gradient(-45deg, 
            rgb(247, 247, 247) , 
            rgb(247, 247, 247) 15px, 
            rgb(225, 234, 235) 15px, 
            rgb(225, 234, 235) 30px, 
            rgb(247, 247, 247) 30px
            );
            background: -o-repeating-linear-gradient(-45deg, 
            rgb(247, 247, 247) , 
            rgb(247, 247, 247) 15px, 
            rgb(225, 234, 235) 15px, 
            rgb(225, 234, 235) 30px, 
            rgb(247, 247, 247) 30px
            );
            background: repeating-linear-gradient(-45deg, 
            rgb(247, 247, 247) , 
            rgb(247, 247, 247) 15px, 
            rgb(225, 234, 235) 15px, 
            rgb(206, 247, 232) 30px, 
            rgb(247, 247, 247) 30px
            );
        }
    </style>
</head>
<body>
<jsp:include page="../navbar.jsp"></jsp:include>
     
<div class="container">

	<div class="row">
		<div class="col-md-4">
			<div class="card">
				<div class="change_link">
				    <div class="header">
				    	<h4>维修信息</h4>
				    </div>
			    </div>
			    <div class="content">
					<fieldset>
	                   <div class="form-group row">
	                      <label class="col-sm-4 control-label">公寓名：</label>
	                      <div class="col-sm-8">
	                         <span>${repair.apartName}</span>
	                      </div>
	                   </div>
					</fieldset>
					<fieldset>
	                   <div class="form-group row">
	                      <label class="col-sm-4 control-label">寝室号：</label>
	                      <div class="col-sm-8">
	                         <span>${repair.dormNo}</span>
	                      </div>
	                   </div>
					</fieldset>
					<fieldset>
	                   <div class="form-group row">
	                      <label class="col-sm-4 control-label">维修类型：</label>
	                      <div class="col-sm-8">
	                         <span>${repair.repairTypeName}</span>
	                      </div>
	                   </div>
					</fieldset>
					<fieldset>
	                   <div class="form-group row">
	                      <label class="col-sm-4 control-label">申请者：</label>
	                      <div class="col-sm-8">
	                         <span>${repair.applicantName}</span>
	                      </div>
	                   </div>
					</fieldset>
					<fieldset>
	                   <div class="form-group row">
	                      <label class="col-sm-4 control-label">申请者手机号：</label>
	                      <div class="col-sm-8">
	                         <span>${repair.applicantTel}</span>
	                      </div>
	                   </div>
					</fieldset>
					<fieldset>
	                   <div class="form-group row">
	                      <label class="col-sm-4 control-label">备注：</label>
	                      <div class="col-sm-8">
	                         <p style="width:200px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis"><a href="#" class="tooltip-test" data-toggle="tooltip" title="${repair.remark}">${repair.remark}</a></p>
	                      </div>
	                   </div>
					</fieldset>
					<fieldset>
	                   	<div class="form-group row">
	                      	<label class="col-sm-4 control-label">状态：</label>
	                      	<div class="col-sm-8">
								<c:if test="${repair.state == 0}">
			                		<span><span class="label label-warning">未接单 </span></span>
			                		<shiro:hasPermission name="repair:delete"> 
										<button style="margin-left:5px" class="btn btn-danger" onClick="deleteRepair(${repair.id})">取消订单</button>
									</shiro:hasPermission>
				                </c:if>
				                <c:if test="${repair.state == 1}">
				                	<span><span class="label label-info">已接单</span></span>
				                </c:if>
				                <c:if test="${repair.state == 2}">
				                	<span><span class="label label-success">已结束</span></span>
				                </c:if>
	                      	</div>
	                   	</div>
					</fieldset>
			    </div>
	  		</div>
	    </div>
	    <c:if test="${not empty repairman}">
		    <div class="col-md-4">
				<div class="card">
				    <div class="change_link">
					    <div class="header">
					    	<h4>维修人员信息</h4>
					    </div>
				    </div>
				    <div class="content">
				    	<fieldset>
		                   <div class="form-group row">
		                      <label class="col-sm-4 control-label">工号：</label>
		                      <div class="col-sm-8">
		                         <span>${repairman.repairmanId}</span>
		                      </div>
		                   </div>
						</fieldset>
						<fieldset>
		                   <div class="form-group row">
		                      <label class="col-sm-4 control-label">姓名：</label>
		                      <div class="col-sm-8">
		                         <span>${repairman.repairmanName}</span>
		                      </div>
		                   </div>
						</fieldset>
						<fieldset>
		                   <div class="form-group row">
		                      <label class="col-sm-4 control-label">性别：</label>
		                      <div class="col-sm-8">
			                      <c:if test="${repairman.repairmanSex == 1}">
			                      	<span>男</span>
			                      </c:if>
			                      <c:if test="${repairman.repairmanSex == 2}">
			                      	<span>女</span>
			                      </c:if>
			                      <c:if test="${repairman.repairmanSex == 0}">
			                      	<span>未知</span>
			                      </c:if>
		                      </div>
		                   </div>
						</fieldset>
						<fieldset>
		                   <div class="form-group row">
		                      <label class="col-sm-4 control-label">电话：</label>
		                      <div class="col-sm-8">
		                         <span>${repairman.repairmanTel}</span>
		                      </div>
		                   </div>
						</fieldset>
				    </div>
				</div>
			</div>
		</c:if>
	    <div class="col-md-4">
		  	<div class="row">
		    	<div class="col-md-12">
		      		<div class="VivaTimeline">
		        		<dl>
		          			<dt>申请时间</dt>
		          			<dd class="clearfix">
			          			<div class="circ"></div>
		              			<div class="time"><fmt:formatDate value="${repair.applyTime}" pattern="yyyy-MM-dd HH:mm" /></div>
			              		<div style="height:70px"></div>
		          			</dd>
		          			<c:forEach items="${recordHistory}" var="history">
		          				<c:if test="${not empty history.acceptTime}">
				          			<dt>接单时间</dt>
			         	 			<dd class="clearfix">
				          				<div class="circ"></div>
				              			<div class="time"><fmt:formatDate value="${history.acceptTime}" pattern="yyyy-MM-dd HH:mm" /></div>
				              			<div style="height:70px"></div>
			          				</dd>
			          			</c:if>
			          			<dt>取消订单</dt>
			          			<shiro:hasPermission name="repair:*"> 
				          			<dd class="clearfix">
				          				<div class="circ"></div>
				              			<div class="time">操作人：${history.repairman.repairmanName}</div>
				              			<div style="height:70px"></div>
			          				</dd>
		          				</shiro:hasPermission>
		          			</c:forEach>
		          			<c:if test="${not empty record.acceptTime}">
			          			<dt>接单时间</dt>
		         	 			<dd class="clearfix">
			          				<div class="circ"></div>
			              			<div class="time"><fmt:formatDate value="${record.acceptTime}" pattern="yyyy-MM-dd HH:mm" /></div>
			              			<div style="height:70px"></div>
		          				</dd>
		          			</c:if>
				          	<c:if test="${not empty record.repairTime}">
					          	<dt>维修时间</dt>
					          	<dd class="clearfix">
					          		<div class="circ"></div>
					              		<div class="time"><fmt:formatDate value="${record.repairTime}" pattern="yyyy-MM-dd HH:mm" /></div>
					              	<div style="height:70px"></div>
					          	</dd>
				          	</c:if>
		        		</dl>
		      		</div>
		    	</div>
		  	</div>
		</div>
	</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery.eeyellow.Timeline.js"></script>
<script>
$(function() {
	$('.VivaTimeline').vivaTimeline({
	    carousel: true
	});
});
function deleteRepair(repairId) {
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
			url: getRootPath() + "/repair/"+repairId+"/delete",
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				if(data == "") {
					swal("删除！", "只有本人才可以删除哦!", "error");
				} else if(data == "删除成功!") {
					swal({ 
					  title: "成功", 
					  text: data, 
					  type: "success"
					},
					function(){
						window.location.href = getRootPath() + "/repair";
					});
				} else if(data == "删除失败!") {
					swal("失败！", data, "error");
				}
			},
			error: function() {
				swal("失败！", "发生错误", "error");
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
</script>
</html>