<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
	<style type="text/css">
		header{text-align: center;color: #fff;background: url(${pageContext.request.contextPath}/public/images/bg.jpg) no-repeat scroll center center ;-webkit-background-size: cover;-moz-background-size: cover;background-size: cover;-o-background-size: cover;height: 650px;}	
		header img{display: block; margin: 200px auto  50px auto;}
		.navbar-default {border-color: transparent !important;background-color: #222 !important;margin-left: 50px !important;}
		.navbar-default .navbar-brand {color: #6ECADC !important;}
		.navbar-default .navbar-brand:hover,
		.navbar-default .navbar-brand:focus,
		.navbar-default .navbar-brand:active,
		.navbar-default .navbar-brand.active {color: #6ECADC !important;}
		
		.navbar-default .navbar-collapse {border-color: rgba(255,255,255,.02);}
		
		.navbar-default .navbar-toggle {border-color: #6ECADC;background-color: #6ECADC;}
		.navbar-default .navbar-toggle:hover,
		.navbar-default .navbar-toggle:focus {background-color: #6ECADC;}
		
		.navbar-default .nav li a {text-transform: uppercase;font-family: Helvetica,Arial,sans-serif;font-weight: 400;letter-spacing: 1px;color: #fff !important;font-size: 1.2em}
		.navbar-default .nav li a:hover,
		.navbar-default .nav li a:focus {outline: 0 !important;color: #6ECADC !important;}
		
		.navbar-default .navbar-nav>.active>a {border-radius: 0;color: #fff;background-color: #6ECADC;}
		.navbar-default .navbar-nav>.active>a:hover,
		.navbar-default .navbar-nav>.active>a:focus {color: #fff !important;background-color: #6ECADC !important;}
		
		@media(min-width:768px) {
		    .navbar-default {
		        padding: 25px 0 !important;
		        border: 0 !important;
		        background-color: transparent !important;
		    }
		    .navbar-default .navbar-brand {
		        font-size: 2em !important;
		    }
		    .navbar-default .navbar-nav>.active>a {
		        border-radius: 3px !important;
		    }
		    .navbar-default.navbar-shrink {
		        padding: 10px 0 !important;
		        background-color: #222 !important;
		    }
		}
		
		#loginInfo {
			color: #cdcdcd;
		}
	</style>
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<header></header>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script>
	$(function() {
		$("nav").removeClass("navbar-static-top");
		$("nav").addClass("navbar-fixed-top");
	});
</script>
</html>