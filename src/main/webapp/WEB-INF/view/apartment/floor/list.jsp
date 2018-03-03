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

<table class="table">
    <thead>
        <tr>
            <th>楼层号</th>
            <th>宿舍数量</th>
            <th>宿舍</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${floorList}" var="floor">
            <tr>
                <td>${floor.floorNo}</td>
                <td>
                	${floor.dormNum}
                	<form action="${floor.id}/dorm/create" method="post">
                		<input type="number" name="dormNum" value="${floor.dormNum}"/>
                		<input type="number" name="currentDormNum" value="${floor.dormNum}" hidden/>
                		<button type="submit">提交</button>
                	</form>
                </td>
                <td>
                	<c:forEach items="${floor.dormList}" var="dorm">
                		<a href="${pageContext.request.contextPath}/apartment/dorm/${dorm.id}">${dorm.dormNo}</a>
                	</c:forEach>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>