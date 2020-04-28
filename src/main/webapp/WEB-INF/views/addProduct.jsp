<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<h1>Add a product</h1>
<form method="post" action="${pageContext.request.contextPath}/addProduct">
    Please enter product name:<input type="text" name="name" required>
    <br>Please enter price:<input type="text" name="price" required>
    <br><button type="submit">Add</button>
</form>
</body>
</html>
