<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>商品</title>
  <link href="css/showproducts.css" rel="stylesheet" type="text/css"/>
  <link href="css/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/axios.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/showproducts.js"></script>
<jsp:include page="/header.jsp"/>

<form method="get" action="searchproduct">
  <div class="search">
    <input type="text" name="productName" value="${param.productName}" size="36" placeholder="キーワードで検索" maxlength="20">
    <input type="submit" value="検索">
  </div>
  <div class="search-sort">
    <div class="sort-select">
      <input type="radio" id="sort-asc" name="sortByCost" value="asc"
             <c:if test="${param.sortByCost eq 'asc'}">checked</c:if>>
      <label for="sort-asc" class="sort">昇順</label>
      <input type="radio" id="sort-desc" name="sortByCost" value="desc"
             <c:if test="${param.sortByCost eq 'desc'}">checked</c:if>>
      <label for="sort-desc" class="sort">降順</label>
      <button type="button" id="type-select-button" onclick="showTypeSelect()">type</button>
      <button type="button" id="spec-search-button" onclick="showSpecSearch()">spec</button>
    </div>
    <!--   <input type="radio" name="sort-by-cost" value="asc">
      <label>昇順</label>
      <input type="radio" name="sort-by-cost" value="desc">
      <label>降順</label>-->

    <div id="product-type-select" class="product-type-select-hidden">
      <input type="radio" name="productType" value="cpu" id="type-cpu"
             <c:if test="${param.productType eq 'cpu'}">checked</c:if>>
      <label class="sort" for="type-cpu">CPU</label>
      <input type="radio" name="productType" value="gpu" id="type-gpu"
             <c:if test="${param.productType eq 'gpu'}">checked</c:if>>
      <label class="sort" for="type-gpu">GPU</label>
      <input type="radio" name="productType" value="ram" id="type-ram"
             <c:if test="${param.productType eq 'ram'}">checked</c:if>>
      <label class="sort" for="type-ram">RAM</label>
      <input type="radio" name="productType" value="mother_board" id="type-mother_board"
             <c:if test="${param.productType eq 'mother_board'}">checked</c:if>>
      <label class="sort" for="type-mother_board">マザーボード</label>
      <input type="radio" name="productType" value="storage" id="type-storage"
             <c:if test="${param.productType eq 'storage'}">checked</c:if>>
      <label class="sort" for="type-storage">ストレージ</label>
      <input type="radio" name="productType" value="case" id="type-case"
             <c:if test="${param.productType eq 'case'}">checked</c:if>>
      <label class="sort" for="type-case">ケース</label>
      <input type="radio" name="productType" value="power_supply" id="type-power_supply"
             <c:if test="${param.productType eq 'power_supply'}">checked</c:if>>
      <label class="sort" for="type-power_supply">電源</label>
      <input type="radio" name="productType" value="cpu_cooler" id="type-cpu_cooler"
             <c:if test="${param.productType eq 'cpu_cooler'}">checked</c:if>>
      <label class="sort" for="type-cpu_cooler">CPUクーラー</label>
      <input type="radio" name="productType" value="case_fan" id="type-case_fan"
             <c:if test="${param.productType eq 'case_fan'}">checked</c:if>>
      <label class="sort" for="type-case_fan">ケースファン</label>
    </div>
    <div id="product-spec-search" class="spec-search-hidden">

    </div>
  </div>
</form>

<div class="products">
  <h1>商品一覧</h1>
  <table width="100%" class="show">
    <tr>
      <th>商品名</th>
      <th>値段</th>
      <th>詳細</th>
      <th>見積りへ</th>
    </tr>
    <c:forEach var="product" items="${data}">
    <div id="product-${product.no}" class="button product-section">
      <tr>
        <td align="center" width="60%">
          <img id="product-img-${product.no}" width="128" height="128"
               src="${pageContext.request.contextPath}/image/transparent.png" alt="${product.name}">
            ${product.name}
        </td>
        <td align="center" width="10%">${product.price}</td>
        <td align="center" width="10%">${product.spec}</td>
        <td align="center" width="10%">
          <form method="get" action="productspec">
            <div class="details-button">
              <input class="pid-value" type="hidden" name="pid" value="${product.no}">
              <input type="submit" value="詳細">
            </div>
          </form>
        </td>
        <td align="center" width="10%">
          <form method="get" action="addbuildpart">
            <div class="add-button">
              <input type="hidden" name="pid" value="${product.no}">
              <input type="submit" value="追加">
            </div>
          </form>
        </td>
      </tr>
      </c:forEach>
  </table>
</div>

<jsp:include page="/footer.jsp"/>
</body>

</html>