<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<div class="container">
<h1>Internet shop</h1>
    <p> <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/injectData"
          role="button">Inject data</a> </p>
    <p> <a class="btn btn-outline-success"
          href="${pageContext.request.contextPath}/registration" role="button">Register</a> </p>
    <p> <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/login"
        role="button">Login</a> </p>
    <p> <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/users/all"
           role="button">All registered users</a> </p>
    <p> <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/addProduct"
           role="button">Add a product</a> </p>
    <p> <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/products/all"
        role="button">See all products (for users) </a> </p>
    <p> <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/products/admin"
        role="button">See all products (for admin)</a> </p>
    <p> <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/cart/all"
           role="button">See all products in a cart</a> </p>
    <p> <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/order/all"
           role="button">See all orders</a> </p>
</div>
</body>
</html>

