<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello mates!</h1>
<a href="${pageContext.request.contextPath}/injectData">Inject test data into the DB</a>
<br> <a href="${pageContext.request.contextPath}/registration">Register</a>
<br> <a href="${pageContext.request.contextPath}/users/all">See registered users</a>
<br> <a href="${pageContext.request.contextPath}/addProducts">Add products</a>
<br> <a href="${pageContext.request.contextPath}/products/all">See all products</a>
</body>
</html>

