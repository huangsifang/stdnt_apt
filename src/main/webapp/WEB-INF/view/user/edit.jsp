<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>

    <form:form method="post" commandName="user">
        <form:hidden path="id"/>
        <form:hidden path="salt"/>
        <form:hidden path="locked"/>

        <c:if test="${op ne '新增'}">
            <form:hidden path="password"/>
        </c:if>

        <div class="form-group">
            <form:label path="username">用户名：</form:label>
            <form:input path="username"/>
        </div>

        <c:if test="${op eq '新增'}">
            <div class="form-group">
                <form:label path="password">密码：</form:label>
                <form:password path="password"/>
            </div>
        </c:if>

        <div class="form-group">
            <form:label path="roleIds">角色列表：</form:label>
            <form:select path="roleIds" items="${roleList}" itemLabel="description" itemValue="id" multiple="true"/>
            (按住shift键多选)
        </div>

        <form:button>${op}</form:button>

    </form:form>

</body>
</html>