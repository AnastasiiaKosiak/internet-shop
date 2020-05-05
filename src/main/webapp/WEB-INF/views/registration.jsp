<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<h1>Registration page</h1>
<h4 style="color:red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <div class="container">
    <div class="form-group">
        <label>Name</label>
        <input type="text" class="form-control" name="name">
    </div>
    <div class="form-group">
        <label>Login</label>
        <input type="text" class="form-control" name="login">
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" class="form-control" name="pwd">
    </div>
    <div class="form-group">
        <label>Repeat password</label>
        <input type="password" class="form-control" name="pwd-repeat">
    </div>
    <button class="btn btn-outline-success">Register</button>
    </div>
</form>
</body>
</html>
