<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
</head>
<body>

欢迎[<shiro:principal/>]登录成功！<a href="${pageContext.request.contextPath}/logout">退出</a>

<c:if test="${not empty msg}">
	<script>alert("${msg}")</script>
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
                	<span>${floor.dormNum}</span>
                	<button type="button" onClick="showNumForm(${floor.id},${floor.dormNum})">修改</button>
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
<form id="dormBedNumForm" method="post">
	宿舍数量：<input id="dormNum" type="number" name="dormNum"/><br />
	<input id="currentDormNum" type="number" name="currentDormNum" hidden/>
	床位数量/宿舍：<input type="number" name="aDormBedNum" value="4"/><br />
	费用/床：<input type="number" name="dormFee" value="1200"/><br />
	<button type="submit">提交</button>
</form>
</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script> 
<script>
function showNumForm(floorId, dormNum) {
	$("#dormBedNumForm").attr("action",floorId+"/dorm/create");
	$("#dormNum").val(dormNum);
	$("#currentDormNum").val(dormNum);
}
</script>
</html>