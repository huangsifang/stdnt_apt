<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
</head>
<body>

欢迎[<shiro:principal/>]登录成功！<a href="logout">退出</a>

<c:if test="${not empty msg}">
    <div>${msg}</div>
</c:if>

<form id="dormForm" action="" method="post">
	<input type="number" name="dormId" value="${dorm.id}" hidden/><br/>
	宿舍号：<input type="number" name="dormNo" value="${dorm.dormNo}" disabled/><br/>
	楼层：<input type="number" name="floorId" value="${dorm.floorId}" disabled/><br/>
	费用/人：<input type="number" name="fee" value="${dorm.fee}"/><br/>
	寝室长：<input type="number" name="leaderId" value="${dorm.leaderId}" id="leaderId"/>
	<span id="leaderName">${dorm.leaderName}</span><br/>
	<button type="button" id="dormUpdateBtn">提交</button>
</form>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script> 
<script>
	$(function() {
		$("#dormUpdateBtn").click(function() {
			$.ajax({
				type: "POST",
				datatype: "text",
				url: "update",
				data: $("#dormForm").serializeArray(),
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					alert(data);
				},
				error: function() {
		        	alert('error');
		        }
			});
		});
		$("#leaderId").change(function(){
			var leaderId = $("#leaderId").val();
			$.ajax({
				type: "GET",
				datatype: "json",
				url: getRootPath() + "/student/stdName",
				data: {stdId: leaderId},
				contentType: "application/x-www-form-urlencoded",
				success: function(data) {
					$("#leaderName").text(data);
				},
				error: function() {
		        	alert('error');
		        }
			});
		});
    });
	
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