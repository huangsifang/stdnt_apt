<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/jquery.eeyellow.Timeline.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
    <style>
    	fieldset {
		  padding-bottom: 20px;
		  border-bottom: 1px dashed #eee;
		  margin-bottom: 20px;
		}
		fieldset.last-child,
		fieldset:last-child {
		  border-bottom: 0;
		}
		fieldset .form-group {
		  margin-bottom: 0;
		}
    </style>
</head>
<body>
            
欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a>

<div class="row">
	<div class="col-md-offset-1 col-md-4">
		<div class="panel panel-default">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	维修信息
		        </h3>
		    </div>
		    <div class="panel-body">
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
                         <span>${repair.remark}</span>
                      </div>
                   </div>
				</fieldset>
				<fieldset>
                   	<div class="form-group row">
                      	<label class="col-sm-4 control-label">状态：</label>
                      	<div class="col-sm-8">
							<c:if test="${repair.state == 0}">
			                		<span>未接单</span>
			                		<shiro:hasPermission name="repair:delete"> 
										<button onClick="deleteRepair(${repair.id})">取消订单</button>
									</shiro:hasPermission>
			                </c:if>
			                <c:if test="${repair.state == 1}">
			                	<span>已接单</span>
			                </c:if>
			                <c:if test="${repair.state == 2}">
			                	<span>已结束</span>
			                </c:if>
                      	</div>
                   	</div>
				</fieldset>
		    </div>
  		</div>
    </div>
    <c:if test="${not empty repairman}">
	    <div class="col-md-3">
			<div class="panel panel-default">
			    <div class="panel-heading">
			        <h3 class="panel-title">
			        	维修人员信息
			        </h3>
			    </div>
			    <div class="panel-body">
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
	                         <span>${repairman.repairmanSex}</span>
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
    <div class="col-md-3">
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
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/bootstrap.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/jquery.eeyellow.Timeline.js"></script>
<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
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
					swal("删除！", "您没有权限删除该维修!", "error");
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
</script>
</html>