<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>

    <form:form method="post" commandName="apartment">
        <div class="form-group">
            <form:label path="apartId">公寓号：</form:label>
            <form:input path="apartId"/>
        </div>

        <div class="form-group">
            <form:label path="apartName">公寓名：</form:label>
            <form:input path="apartName"/>
        </div>

        <form:button>${op}</form:button>

    </form:form>

</body>
</html>