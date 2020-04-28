<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Hello! Please write your user details</h1>
<h4 style="color:red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    Please provide your name:<input type="text" name="name" required>
    <br>Please provide your login:<input type="text" name="login" required>
    <br>Please provide your password:<input type="password" name="pwd" required>
    <br>Please repeat your password:<input type="password" name="pwd-repeat" required>
    <br><button type="submit">Register</button>
</form>
</body>
</html>
