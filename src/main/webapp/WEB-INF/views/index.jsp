<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<h1>Internet shop</h1>
<a href="${pageContext.request.contextPath}/injectData">Inject test data into the DB</a>
<br> <a href="${pageContext.request.contextPath}/registration">Register</a>
<br> <a href="${pageContext.request.contextPath}/users/all">See registered users</a>
<br> <a href="${pageContext.request.contextPath}/addProduct">Add products</a>
<br> <a href="${pageContext.request.contextPath}/products/all">See all products (for users)</a>
<br> <a href="${pageContext.request.contextPath}/products/admin">See all products (for admin)</a>
<br> <a href="${pageContext.request.contextPath}/cart/all">See products in a cart</a>
<br> <a href="${pageContext.request.contextPath}/order/all">See all orders</a>
</body>
</html>

