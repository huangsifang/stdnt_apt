<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a>

<c:if test="${not empty repairList}">
	<ul class="nav nav-tabs">
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
	                <td>
	                	<shiro:hasPermission name="repair:view">
	                		<a href="${pageContext.request.contextPath}/repair/${repair.id}/record">查看</a>
	                    </shiro:hasPermission>
	                </td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
</c:if>

</body>
</html>