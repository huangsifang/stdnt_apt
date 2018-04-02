<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>导航栏</title>
	<link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/public/css/font-awesome.min.css" rel="stylesheet">
	<style>
		#backBtn {
			cursor: pointer;
		}
		#backBtn:hover {
			background-color: #eee;
		}
	</style>
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top" role="navigation">
	    <div class="container-fluid">
		    <div class="navbar-header">
		        <a class="navbar-brand" href="${pageContext.request.contextPath}/" id="apartmentBrand">学生公寓</a>
		        <a class="navbar-brand" id="backBtn" onClick="back()"><i class="fa  fa-angle-left"></i>&nbsp;返回</a>
		    </div>
		    <div>
		        <ul class="nav navbar-nav">
		        	<c:forEach items="${menus}" var="m">
		        		<li>
		        			<a href="${pageContext.request.contextPath}${m.url}" target="content">${m.name}</a>
		        		</li>
		        	</c:forEach>
		        </ul>
		    </div>
		    <div class="pull-right" id="loginInfo" style="margin-top:20px">欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a></div>
	    </div>
	</nav>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script>
$(function() {
	var pathname = window.document.location.pathname;
	var first = pathname.indexOf('/', 1);
	var second = pathname.indexOf('/', first+1);
	var url;
	if(second != -1) {
		url = pathname.substring(0, second);
		$("#apartmentBrand").hide();
		$("#backBtn").show();
		$(".nav li a[href='"+url+"']").parent().addClass("active");
	} else {
		$("#apartmentBrand").show();
		$("#backBtn").hide();
		$(".nav li a[href='"+pathname+"']").parent().addClass("active");
	}
});
function back(){
	history.back();
}
</script>