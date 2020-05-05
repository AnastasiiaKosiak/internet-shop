<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a product</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<h1 >Add a product</h1>
<form method="post" action="${pageContext.request.contextPath}/products/add">
    <div class="container">
        <div class="form-group">
            <label>Product name</label>
            <input type="text" class="form-control" name="name" required>
        </div>
        <div class="form-group">
            <label>Price</label>
            <input type="text" class="form-control" name="price" required>
        </div>
        <button class="btn btn-outline-success" type="submit">Add</button>
    </div>
</form>
</body>
</html>
