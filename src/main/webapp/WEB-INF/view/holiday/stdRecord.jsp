<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>我的假期记录</title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
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
    </style>
</head>
<body>
<jsp:include page="../navbar.jsp"></jsp:include>

<div style="margin:20px 50px">
	<div class="pull-right">欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a></div>

	<div class="row">
		<div class="col-md-offset-3 col-md-4">
			<div class="panel panel-default">
			    <div class="panel-heading">
			        <h3 class="panel-title">
			        	我的假期信息
			        </h3>
			    </div>
			    <div class="panel-body">
					<form id="recordForm" action="" method="post">
						<fieldset>
							<div class="form-group">
								<label for="holiId" class="col-sm-4 control-label">假期名：</label>
								<div class="col-sm-8">
									<span>${stdRecord.holiName}</span>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="holiId" class="col-sm-4 control-label">公寓名：</label>
								<div class="col-sm-8">
									<span>${stdRecord.apartName}</span>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="holiId" class="col-sm-4 control-label">留校|返家：</label>
								<div class="col-sm-8">
									<span>${stdRecord.homeOrSchool}</span>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="startTime" class="col-sm-4 control-label">开始时间：</label>
								<div class="col-sm-8">
									<span>${stdRecord.startTime}</span>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="endTime" class="col-sm-4 control-label">结束时间：</label>
								<div class="col-sm-8">
									<span>${stdRecord.endTime}</span>
							   	</div>
							</div>
						</fieldset>
						<fieldset>
							<c:if test="${!stdRecord.inHome}">
								<div class="form-group">
									<label for="holiId" class="col-sm-4 control-label">是否外出：</label>
									<div class="col-sm-8">
										<span>${stdRecord.isOutStr}</span>
								   	</div>
								</div>
							</c:if>
						</fieldset>
						<c:if test="${stdRecord.inHome}">
							<fieldset>
								<div class="form-group" id="addressGroup">
									<label for="address" class="col-sm-4 control-label">地址：</label>
									<div class="col-sm-8">
										<span>${stdRecord.address}</span>
								   	</div>
								</div>
							</fieldset>
							<c:if test="${!hasSign}">
								<fieldset>
									<div class="form-group">
										<div class="col-sm-offset-4 col-sm-8">
											<button type="button" class="btn btn-default" onClick="sign(${stdRecord.holiId})">签到</button>
									   	</div>
									</div>
								</fieldset>
							</c:if>
							<c:if test="${hasSign}">
								<fieldset>
									<div class="form-group">
										<div class="col-sm-offset-4 col-sm-8">
											<button type="button" class="btn btn-default">已签到</button>
									   	</div>
									</div>
								</fieldset>
							</c:if>
						</c:if>
					</form>
			    </div>
	  		</div>
	    </div>
	</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/bootstrap.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
<script>
function sign(holiId) {
	$.ajax({
		type: "POST",
		datatype: "json",
		url: getRootPath() + "/holiday/" + holiId + "/sign",
		contentType: "application/x-www-form-urlencoded",
		success: function(data) {
			if(data == "success") {
				swal("成功！", "签到成功", "success");
			} else if(data == 'errorHasSign') {
				swal("失败！", "您已签到过，无需重复签到！", "warning");
			} else if(data == 'error') {
				swal("失败！", "签到失败", "error");
			}
		},
		error: function() {
			swal("失败！", "发生错误", "error");
        }
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