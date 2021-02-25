<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link href="css/productmanagement.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/header.jsp"/>
<form method="post" action="addnewproduct">
  Product Name: <input type="text" name="productName"><br>
  Product Brand: <input type="text" name="productBrand"><br>
  Spec: <input type="text" name="spec"><br>
  Price: <input type="number" name="price"><br>
  Product Type: <input type="text" name="productType"><br>
  <input type="submit">
</form>
<form method="post" action="deleteproduct">
  Delete Product No: <input type="text" name="productNo"><br>
  <input type="submit">
</form>
<form method="post" action="deletepost">
  Delete Post No: <input type="text" name="postno"><br>
  <input type="submit">
</form>
</body>
</html>
