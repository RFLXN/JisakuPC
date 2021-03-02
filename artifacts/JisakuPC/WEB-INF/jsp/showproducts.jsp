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
<jsp:include page="/header.jsp"/>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/axios.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/showproducts.js"></script>
<jsp:include page="/header.jsp"/>

<form method="get" action="searchproduct" id="type-change-form"></form>
<form id="search-options-form" method="get" action="searchproduct">
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
      <button type="button" id="type-select-button" onclick="showTypeSelect()">パーツ種類</button>
      <button type="button" id="spec-search-button" onclick="showSpecSearch()">スペック条件</button>
    </div>
    <div id="product-type-select" class="product-type-select-hidden">
      <button name="productType" value="cpu" form="type-change-form">CPU</button>
      <button name="productType" value="gpu" form="type-change-form">GPU</button>
      <button name="productType" value="mother_board" form="type-change-form">マザーボード</button>
      <button name="productType" value="ram" form="type-change-form">RAM</button>
      <button name="productType" value="storage" form="type-change-form">ストレージ</button>
      <button name="productType" value="case" form="type-change-form">ケース</button>
      <button name="productType" value="power_supply" form="type-change-form">電源</button>
      <button name="productType" value="cpu_cooler" form="type-change-form">CPUクーラー</button>
      <button name="productType" value="case_fan" form="type-change-form">ケースファン</button>
    </div>
    <div id="type-selected-buffer" style="display: none">
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
    <div id="product-spec-search" class="spec-search-hidden"></div>
  </div>
</form>

<div class="products">
  <c:choose>
    <c:when test="${data.size() eq 0}">
      <p>検索結果がありません。</p>
    </c:when>
    <c:otherwise>
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
            <td align="center" width="10%">
              <c:choose>
                <c:when test="${product.price eq 0}">
                  -
                </c:when>
                <c:otherwise>
                  ${product.price}
                </c:otherwise>
              </c:choose>

            </td>
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
    </c:otherwise>
  </c:choose>
</div>

<div id="paginator-container" class="container">
  <div id="pagination-section" class="row col-xs-offset-4 col-xs-8">
    <ul id="paginator" class="pagination row col-xs-12">
    </ul>
  </div>
</div>
</body>

</html>