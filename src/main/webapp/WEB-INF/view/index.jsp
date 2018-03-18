<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<div>
    功能菜单<br/>
    <c:forEach items="${menus}" var="m">
        <a href="${pageContext.request.contextPath}${m.url}" target="content">${m.name}</a><br/>
    </c:forEach>
</div>
<ul>
    <li><a href="uploadInfo/uploadInfo" />uploadInfo</li>
    <li><a href="login" />login</li>
    <li><a href="user" />user</li>
    <li><a href="apartment" />apartment</li>
</ul>
</body>
</html>