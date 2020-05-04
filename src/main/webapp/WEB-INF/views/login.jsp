<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>
<h4 style="color:red">${errMsg}</h4>
<form action="${pageContext.request.contextPath}/login" method="post">
    <br>Please provide your login:<input type="text" name="login" required>
    <br>Please provide your password:<input type="password" name="pwd" required>
    <br><button type="submit">Login</button>
</form>
<br> <a href="${pageContext.request.contextPath}/registration">Register</a>
</body>
</html>
