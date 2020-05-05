<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>All products page </h1>
    <p> <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/">Main page</a> </p>
    <table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${products}">
    <tr>
        <td><c:out value="${product.id}"/></td>
        <td><c:out value="${product.name}"/></td>
        <td><c:out value="${product.price}"/></td>
        <td>
            <a href="${pageContext.request.contextPath}/products/delete?id=${product.id}">Delete</a>
        </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p> <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/addProduct">Add a product</a> </p>
</div>
</body>
</html>