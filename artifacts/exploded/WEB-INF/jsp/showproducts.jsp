<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>商品</title>
</head>
<body>
<div id="search">
  <form method="get" action="searchproduct">
    <input type="text" name="moji" value="${param.moji}" size="36" placeholder="キーワードで検索" maxlength="20">
    <input type="submit" value="検索">
<%-- 	<input type="submit" name="syoujunn" value="昇順" />
	<input type="submit" name="koujunn" value="降順" />--%>
	  <label><input type="radio" name="sort-by-cost" value="asc">昇順</label>
    <label><input type="radio" name="sort-by-cost" value="desc">降順</label>
  </form>
</div>

<h1>商品一覧</h1>
<table border="1">
  <tr><th>商品番号</th><th>商品名</th><th>値段</th><th>スペック</th><th>ブランド</th><th>商品タイプ</th></tr>
  <c:forEach var="product" items="${data}">
    <tr><td>${product.no}</td><td>${product.name}</td><td>${product.price}</td><td>${product.spec}</td><td>${product.brand}</td><td>${product.type}</td></tr>
  </c:forEach>
</table>
</body>
</html>