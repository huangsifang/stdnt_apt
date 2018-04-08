<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>没有权限</title>
    <style>.error{color:red;}</style>
</head>
<body>

<div class="error">您没有权限，请确认登录账号或联系管理员<br />[${exception.message}]</div>
</body>
</html>