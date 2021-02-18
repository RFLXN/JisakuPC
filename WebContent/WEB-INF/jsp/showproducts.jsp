<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>商品</title>
  <link href="css/showproducts.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<jsp:include page="/header.jsp"/>

<form method="get" action="searchproduct">
  <div class="search">
    <input type="text" name="moji" value="${param.moji}" size="36" placeholder="キーワードで検索" maxlength="20">
    <input type="submit" value="検索">
  </div>
  <div class="search-sort">
    <label><input type="radio" name="sort-by-cost" value="asc">昇順</label>
    <label><input type="radio" name="sort-by-cost" value="desc">降順</label>
    <br><input type="submit" name="parts" value="cpu">
    <br><input type="submit" name="parts" value="ram">
    <br><input type="submit" name="parts" value="gpu">
    <br><input type="submit" name="parts" value="storage">
  </div>
</form>


<h1>商品一覧</h1>
<table width="100%" class="show">
  <tr>
    <th>商品名</th>
    <th>値段</th>
    <th>詳細</th>
    <th>見積りへ</th>
  </tr>
  <c:forEach var="product" items="${data}">
    <tr>
      <td align="center" width="60%">${product.name}</td>
      <td align="center" width="10%">${product.price}</td>
      <td align="center" width="10%">${product.spec}</td>
      <td align="center" width="10%">
        <form method="get" action="productspec">
          <input type="hidden" name="pid" value="${product.no}">
          <input type="submit" value="詳細">
        </form>
      </td>
      <td align="center" width="10%">
        <form method="get" action="addbuildpart">
          <input type="hidden" name="pid" value="${product.no}">
          <input type="submit" value="追加">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
<jsp:include page="/footer.jsp"/>
</html>