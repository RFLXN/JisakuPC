<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link href="css/productmanagement.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div id=addproduct>
<h2>商品登録</h2>
<form method="post" action="addnewproduct">
  Name: <input type="text" name="productName"><br>
  Brand: <input type="text" name="productBrand"><br>
  Spec: <input type="text" name="spec"><br>
  Price: <input type="number" name="price"><br>
  Type: <input type="text" name="productType"><br>
  <input type="submit" value="登録">
</form>
</div><br>
<div id=deleteproduct>
<h2>商品削除</h2>
<form method="post" action="deleteproduct">
  No: <input type="text" name="productNo"><br>
  <input type="submit" value="削除">
</form>
</div><br>
<div id=deletepost>
<h2>投稿削除</h2>
<form method="post" action="deletepost">
  No: <input type="text" name="postno"><br>
  <input type="submit" value="削除">
</form>
</div>
</body>
</html>
