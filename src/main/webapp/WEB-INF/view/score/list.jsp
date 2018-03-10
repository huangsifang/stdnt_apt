<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

欢迎[<shiro:principal/>]登录成功！<a href="logout">退出</a>

<c:if test="${not empty msg}">
    <div>${msg}</div>
</c:if>

<shiro:hasPermission name="score:create">
    <form id="dormScoreForm" action="" method="post">
    	公寓号：<input type="number" id="apartId" name="apartId" /><br />
    	寝室号：<input type="number" id="floorDormId" name="floorDormId" /><br />
    	分数：<input type="number" id="score" name="score" /><br />
    	<button type="button" id="scoreSubmitBtn">提交</button>
    </form>
</shiro:hasPermission>

<table class="table">
    <thead>
        <tr>
            <th>公寓号</th>
            <th>公寓名</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${apartList}" var="apart">
            <tr>
                <td>${apart.apartId}</td>
                <td><a href="score/${apart.apartId}">${apart.apartName}</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<c:forEach items="${newScoreList}" var="score">
	<div class="col-sm-3 panel panel-default">
		<div class="panel-body">
		<label for="apartId">公寓号：${score.apartId}</label><br />
		<label for="apartId">寝室号：${score.floorDormNo}</label><br />
		<label for="apartId">分数：${score.score}</label>
		</div>
	</div>
</c:forEach>

</body>
<script src="${pageContext.request.contextPath}/public/js/jquery-3.3.1.min.js" ></script>
<script>
$(function() {
	$("#scoreSubmitBtn").click(function() {
		$.ajax({
			type: "POST",
			datatype: "text",
			url: "score/create",
			data: $("#dormScoreForm").serializeArray(),
			contentType: "application/x-www-form-urlencoded",
			success: function(data) {
				alert(data);
			},
			error: function() {
	        	alert('error');
	        }
		});
	});
});
</script>
</html>