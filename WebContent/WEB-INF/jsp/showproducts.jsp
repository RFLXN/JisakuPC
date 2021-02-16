<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>商品</title>
  <link href="css/showproducts.css" rel="stylesheet" type="text/css" />
</head>
<jsp:include page="/header.jsp" />
<body>
<div class="search">
  <form method="get" action="searchproduct">
    <input type="text" name="moji" value="${param.moji}" size="36" placeholder="キーワードで検索" maxlength="20">
    <input type="submit" value="検索">
</div>
<div class="search-sort">
	<label><input type="radio" name="sort-by-cost" value="asc">昇順</label>
    <label><input type="radio" name="sort-by-cost" value="desc">降順</label>
    <br><input type="submit" name="parts" value="cpu" onClick="this.form.moji.value=''">
	<br><input type="submit" name="parts" value="ram" onClick="this.form.moji.value=''">
	<br><input type="submit" name="parts" value="gpu" onClick="this.form.moji.value=''">
	<br><input type="submit" name="parts" value="storage" onClick="this.form.moji.value=''">
</div>
  </form>
</div>

<h1>商品一覧</h1>
<table width="100%" class="show">
  <tr><th>商品名</th><th>値段</th><th>詳細</th><th>見積りへ</th></tr>
  <c:forEach var="product" items="${data}">
    <tr><td align="center" width="70%">${product.name}</td><td align="center" width="10%">${product.price}</td>
    <td align="center" width="10%"><form action="details"><input type="submit" value="詳細"></form></td>
    <td align="center" width="10%"><form action="add"><input type="submit" value="追加"></form></td>
    </tr>
  </c:forEach>
</table>
</body>
<jsp:include page="/footer.jsp" />
</html>