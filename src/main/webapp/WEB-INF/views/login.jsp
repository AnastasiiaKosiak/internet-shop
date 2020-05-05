<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<h1>Login page</h1>
<h4 style="color:red">${errMsg}</h4>
<form method="post" action="${pageContext.request.contextPath}/login">
    <div class="container">
        <div class="form-group">
            <label>Login</label>
            <input type="text" class="form-control" name="login" required>
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" class="form-control" name="pwd" required>
        </div>
        <p><button class="btn btn-outline-success">Login</button></p>
        <p> <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/registration">Register</a></p>
    </div>
</form>
</body>
</html>
