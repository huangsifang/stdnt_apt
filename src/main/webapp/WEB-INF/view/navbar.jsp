<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>导航栏</title>
	<link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/public/css/sweetalert.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/public/css/font-awesome.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/public/css/table.css" rel="stylesheet">
	<style>
		#backBtn {
			cursor: pointer;
		}
		#backBtn:hover {
			background-color: #eee;
		}
		.navbar-default .navbar-nav > .active > a,
		.navbar-default .navbar-nav > .active > a:hover,
		.navbar-default .navbar-nav > .active > a:focus {
		  color: #fff;
		  background-color: #6BAAA8;
		}
		.active{
          background:#74B9B9 !important;
          color: white;
        }
        .navbar {
          font-weight: bold;
          padding-top: 15px;
          padding-bottom: 15px;
          margin-bottom: 0;
          position: relative;
		  min-height: 50px;
		  border: 1px solid transparent;
		  background: #fff;
        }
        .navbar-nav > li > a {
          padding: 10px 15px;
          margin: 5px 5px;
          border-radius: 4px;
        }
        body {
            font-size: 15px;
            line-height: 24px;
            /*background:#F8FAFF;*/
            background:#f4f3ef
        }

        a {
            color: #55CBD2;
            text-decoration: none;
            -o-transition: all .3s;
            -moz-transition: all .3s;
            -webkit-transition: all .3s;
            -ms-transition: all .3s;
        }

        a:hover { color: #888; text-decoration: none; }
        
        #loginInfo {
        	position:absolute;
        	right:10px;
        	top:50px;
        	color:#777;
        	font-family: "微软雅黑";
        	z-index: 1039;
        }
        .card {
        	margin-top: 20px
        }
	</style>
</head>
<body>
	<div id="loginInfo">欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a></div>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="navMenu">
	    <div class="container">
		    <div class="navbar-header">
		        <a class="navbar-brand" id="backBtn" onClick="back()"><i class="fa  fa-angle-left"></i>&nbsp;返回</a>
		    </div>
		    <div>
		        <ul class="nav navbar-nav pull-left">
		        	<li>
	        			<a href="${pageContext.request.contextPath}/" target="content">首页</a>
	        		</li>
		        	<c:forEach items="${menus}" var="m">
		        		<li>
		        			<a href="${pageContext.request.contextPath}${m.url}" target="content">${m.name}</a>
		        		</li>
		        	</c:forEach>
		        </ul>
		    </div>
	    </div>
	</nav>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/bootstrap.min.js" ></script>
<script src="${pageContext.request.contextPath}/public/js/sweetalert.min.js" ></script>
<script>
$(function() {
	var pathname = window.document.location.pathname;
	var first = pathname.indexOf('/', 1);
	var second = pathname.indexOf('/', first+1);
	var url;
	if(second != -1) {
		url = pathname.substring(0, second);
		//$("#apartmentBrand").hide();
		$("#backBtn").show();
		$("#navMenu .nav li a[href='"+url+"']").parent().addClass("active");
		if(url.substring(first, second) == "/user") {
			var stdUrl = pathname.substring(0, first)+"/user/role/4";
			$("#navMenu .nav li a[href='"+stdUrl+"']").parent().addClass("active");
		}
	} else {
		//$("#apartmentBrand").show();
		$("#backBtn").hide();
		$("#navMenu .nav li a[href='"+pathname+"']").parent().addClass("active");
	}
});
function back(){
	history.back();
}
</script>