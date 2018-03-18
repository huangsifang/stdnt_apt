<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
</head>
<body>

欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a>

<form id="recordForm" action="" method="post" class="form-horizontal" role="form">
	<div class="form-group">
		<label for="holiId" class="col-sm-3 control-label">假期名：</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="holiName" name="holiName" value="${stdRecord.holiName}" disabled>
	   	</div>
	</div>
	<div class="form-group">
		<label for="holiId" class="col-sm-3 control-label">公寓名：</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="apartId" name="apartId" value="${stdRecord.apartName}" disabled>
	   	</div>
	</div>
	<div class="form-group">
		<label for="holiId" class="col-sm-3 control-label">留校|返家：</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="homeOrSchool" name="HomeOrSchool" value="${stdRecord.homeOrSchool}" disabled>
	   	</div>
	</div>
	<div class="form-group">
		<label for="startTime" class="col-sm-3 control-label">开始时间：</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="startTime" name="startTime" value="${stdRecord.startTime}" disabled>
	   	</div>
	</div>
	<div class="form-group">
		<label for="endTime" class="col-sm-3 control-label">结束时间：</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="endTime" name="endTime" value="${stdRecord.endTime}" disabled>
	   	</div>
	</div>
	<c:if test="${!stdRecord.inHome}">
		<div class="form-group">
			<label for="holiId" class="col-sm-3 control-label">是否外出：</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="isOut" name="isOut" value="${stdRecord.isOutStr}" disabled>
		   	</div>
		</div>
	</c:if>
	<c:if test="${stdRecord.inHome}">
		<div class="form-group" id="addressGroup">
			<label for="address" class="col-sm-3 control-label">地址：</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="address" name="address" value="${stdRecord.address}" disabled>
		   	</div>
		</div>
		<c:if test="${!hasSign}">
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-8">
					<button type="button" class="btn btn-default" onClick="sign(${stdRecord.holiId})">签到</button>
			   	</div>
			</div>
		</c:if>
		<c:if test="${hasSign}">
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-8">
					<button type="button" class="btn btn-default">已签到</button>
			   	</div>
			</div>
		</c:if>
	</c:if>
</form>
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
			if(data != "") {
				swal("成功！", data, "success");
			} else {
				swal("失败！", "签到失败", "error");
			}
		},
		error: function() {
        	alert('error');
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