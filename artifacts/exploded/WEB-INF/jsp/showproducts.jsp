<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>商品</title>
</head>
<body>
<h1>商品一覧</h1>
<table border="1">
  <tr><th>商品番号</th><th>商品名</th><th>スペック</th><th>ブランド</th><th>商品タイプ</th></tr>
  <c:forEach var="product" items="${data}">
    <tr><td>${product.no}</td><td>${product.name}</td><td>${product.spec}</td><td>${product.brand}</td><td>${product.type}</td></tr>
  </c:forEach>
</table>
</body>
</html>