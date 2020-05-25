<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>All orders</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>All orders page</h1>
    <p> <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/">Main page</a> </p>
    <table class="table">
        <thead>
        <tr>
            <th>Order ID</th>
            <th>User's ID</th>
            <th>Details</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td><c:out value="${order.id}"/></td>
                <td><c:out value="${order.userId}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/order/info?id=${order.id}">Details</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/order/delete?id=${order.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
