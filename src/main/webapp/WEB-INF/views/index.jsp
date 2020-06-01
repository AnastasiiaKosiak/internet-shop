<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/WEB-INF/css/index.css"%></style>
<html>
<head>
    <title>Index</title>
</head>
<body>
<div class="navbar">
    <a href="${pageContext.request.contextPath}/inject">Inject test data</a>
    <a href="${pageContext.request.contextPath}/registration">Register</a>
    <a href="${pageContext.request.contextPath}/login">Login</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
    <div class="dropdownUser">
        <a class="dropdownBtn">For Users
            <i class="fa fa-caret-down"></i>
        </a>
        <div class="dropdownUser-content">
            <a href="${pageContext.request.contextPath}/products/all">See all products</a>
            <a href="${pageContext.request.contextPath}/cart/all">See all products in a cart</a>
            <a href="${pageContext.request.contextPath}/order/all">See user orders</a>
        </div>
    </div>
    <div class="dropdownAdmin">
        <a class="dropdownAdminBtn">For Administrators
            <i class="fa fa-caret-down"></i>
        </a>
        <div class="dropdownAdmin-content">
            <a href="${pageContext.request.contextPath}/users/all">All registered users</a>
            <a href="${pageContext.request.contextPath}/products/add">Add products</a>
            <a href="${pageContext.request.contextPath}/products/admin">Products stock</a>
            <a href="${pageContext.request.contextPath}/order/admin">History of orders</a>
        </div>
    </div>
</div>
</body>
</html>

