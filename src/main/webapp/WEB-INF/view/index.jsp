<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>首页</title>
	<link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
	<style type="text/css">
		img {
			display: block;
		    max-width: 100%;
		    height: auto;
	    }
	    .glyphicon-lg{font-size:3em}
		.blockquote-box{border-right:5px solid #E6E6E6;margin-bottom:25px}
		.blockquote-box .square{width:100px;min-height:50px;margin-right:22px;text-align:center!important;background-color:#E6E6E6;padding:20px 0}
		.blockquote-box.blockquote-primary{border-color:#357EBD}
		.blockquote-box.blockquote-primary .square{background-color:#428BCA;color:#FFF}
		.blockquote-box.blockquote-success{border-color:#73b9b9}
		.blockquote-box.blockquote-success .square{background-color:#73b9b9;color:#FFF}
		.blockquote-box.blockquote-info{border-color:#46B8DA}
		.blockquote-box.blockquote-info .square{background-color:#5BC0DE;color:#FFF}
		.blockquote-box.blockquote-warning{border-color:#EEA236}
		.blockquote-box.blockquote-warning .square{background-color:#F0AD4E;color:#FFF}
		.blockquote-box.blockquote-danger{border-color:#D43F3A}
		.blockquote-box.blockquote-danger .square{background-color:#D9534F;color:#FFF}
		.triangle-left {
			float:left;
		    width: 0;
		    height: 0;
		    border-top: 20px solid transparent;
		    border-right: 40px solid #EDBA19;
		    border-bottom: 20px solid transparent;
		}
		.triangle-topleft {
		    width: 0;
		    height: 0;
		    border-top: 20px solid #EDBA19;
		    border-right: 40px solid transparent;
		}
		.triangle-bottomleft {
		    width: 0;
		    height: 0;
		    border-bottom: 20px solid #EDBA19;
		    border-right: 40px solid transparent;
		}
		.rect {
			float:left;
			width: 100px;
			height: 40px;
			background-color: #EDBA19;
		}
		#todayRota {
			position: absolute;
			line-height: 20px;
			margin-left: 50px;
			color: #fff;
			font-weight: bold;
		}
	</style>
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<img src="${pageContext.request.contextPath}/public/images/bg.jpg" height="400">
<div class="container" style="margin-top: 20px">
	<div class="row" style="margin-bottom:20px">
		<div class="triangle-left"></div>
		<div class="rect"></div>
		<div style="float:left">
			<div class="triangle-topleft"></div>
			<div class="triangle-bottomleft"></div>
		</div>
		<h4 id="todayRota">今日值班</h4>
	</div>
	<c:forEach items="${apartList}" var="apart">
		<c:if test="${apart.rotas.size()>0}">
			<div class="row">
				<label>${apart.apartName}</label>
			</div>
			<div class="row">
				<c:forEach items="${apart.rotas}" var="rota">
					<div class="col-md-6">
						<c:if test="${apart.apartId%5 == 1}">
					    <div class="blockquote-box blockquote-success animated wow fadeInLeft clearfix">
					    </c:if>
					    <c:if test="${apart.apartId%5 == 2}">
					    <div class="blockquote-box blockquote-primary animated wow fadeInLeft clearfix">
					    </c:if>
					    <c:if test="${apart.apartId%5 == 3}">
					    <div class="blockquote-box blockquote-warning animated wow fadeInLeft clearfix">
					    </c:if>
					    <c:if test="${apart.apartId%5 == 4}">
					    <div class="blockquote-box blockquote-danger animated wow fadeInLeft clearfix">
					    </c:if>
					    <c:if test="${apart.apartId%5 == 0}">
					    <div class="blockquote-box blockquote-info animated wow fadeInLeft clearfix">
					    </c:if>
					        <div class="square pull-left">
					            <img src="${pageContext.request.contextPath}/public/images/member3.png" alt="Feature-img" height="80" width="100">
					        </div>
					        <h4>
					            ${rota.staff.staffName}
					        </h4>
					        <p>
					        	<span>工号：${rota.staff.staffId}</span><br />
					       		<span>手机号：${rota.staff.staffTel}</span>
					        </p>
					    </div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</c:forEach>
</div>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
</html>