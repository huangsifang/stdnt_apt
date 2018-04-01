<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/css/table.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../navbar.jsp"></jsp:include>

<div style="margin:20px 50px">

	<ul class="nav nav-tabs" id="apartUl">
		<c:forEach items="${apartList}" var="apart">
			<li><a href="${pageContext.request.contextPath}/repair/apart/${apart.apartId}">${apart.apartName}</a></li>
	    </c:forEach>
	</ul>
	
	<table class="table">
	    <thead>
	        <tr>
	            <th>寝室号</th>
	            <th>申请者</th>
	            <th>维修类型</th>
	            <th>申请时间</th>
	            <th>备注</th>
	            <th>状态</th>
	            <th>操作</th>
	        </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${empty repairList}">
	    		<tr>
	    			<td colspan="7" style="text-align:center">该公寓没有任何维修信息！</td>
	    		</tr>
	    	</c:if>
	        <c:forEach items="${repairList}" var="repair">
	            <tr>
	                <td>${repair.dormNo}</td>
	                <td>${repair.applicantName}</td>
	                <td>${repair.repairTypeName}</td>
	                <td><fmt:formatDate value="${repair.applyTime}" pattern="yyyy-MM-dd HH:mm" /></td>
	                <td>${repair.remark}</td>
	                <c:if test="${repair.state == 0}">
	                	<td>未接单</td>
	                </c:if>
	                <c:if test="${repair.state == 1}">
	                	<td>已接单</td>
	                </c:if>
	                <c:if test="${repair.state == 2}">
	                	<td>已结束</td>
	                </c:if>
	                <c:if test="${repair.state == 3}">
	                	<td>已取消</td>
	                </c:if>
	                <td>
	                	<c:if test="${repair.state != 3}">
		                	<shiro:hasPermission name="repair:view">
		                		<a href="${pageContext.request.contextPath}/repair/${repair.id}/record">查看</a>
		                    </shiro:hasPermission>
	                    </c:if>
	                </td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
	<c:if test="${allCount != 0}">
		<ul class="pagination tablePage">
		    <li><a href="${pageContext.request.contextPath}/repair/apart/${apartId}?start=${start-10}">&laquo;</a></li>
		    <c:forEach begin="0" end="${allCount-1}" var="item" step="10">
		    	<li value="${item/10+1}"><a href="${pageContext.request.contextPath}/repair/apart/${apartId}?start=${item}"><fmt:formatNumber type="number" value="${item/10+1}" maxFractionDigits="0"/></a></li>
		    </c:forEach>
		    <li><a href="${pageContext.request.contextPath}/repair/apart/${apartId}?start=${start+10}">&raquo;</a></li>
		</ul>
	</c:if>
</div>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script> 
<script>
	$(function() {
		var nowApartId = Number('${apartId}')-1;
		$("#apartUl li").eq(nowApartId).addClass("active");
		
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
	});
</script>
</html>