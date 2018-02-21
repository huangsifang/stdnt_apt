<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	欢迎[<shiro:principal/>]登录成功！<a href="logout">退出</a>
    <div>
        <h3 class="title"><span>今日得分</span></h3>
        <table border="1" width="30%" class="tab">
            <tr>
                <th>寝室号</th>
                <th>分数</th>
            </tr>
            <c:forEach var="score" items="${scores}">
                <tr>
                    <td>${score.dormID}</td>
                    <td>${score.score}</td>
                </tr>
            </c:forEach>
        </table>    
    </div>
</body>
</html>