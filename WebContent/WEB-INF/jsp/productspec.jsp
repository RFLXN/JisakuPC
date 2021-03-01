<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
  <title>Product Spec : ${data.name}</title>
  <link href="css/productspec.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<jsp:include page="/header.jsp"/>

<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/productspec.js"></script>
<script>
    $(() => {
        productspec("${data.no}", ${data.price});
    });
</script>
<div id="product-spec">
  <h1 class="name">${data.name}</h1>
  <a href="javascript:history.back()" class="back">1ページ戻る</a>
  <div class="replacespec">
  <c:set var="oldspec" value="${data.spec}"/>
  <c:set var="newspec" value="${fn:replace(oldspec,'{', '')
	.replace('}', '').replace('\"', '').replace(',', ' /').replace('tdp', 'TDP').replace('date', '日付')
	.replace('core', 'コア').replace('socket', 'Socket').replace('thread', 'スレッド').replace('frequency', 'GHz')
  }" />
	<p>変換後 : ${newspec}</p>
	</div>

  <div id="product-spec">SPEC : ${data.spec}</div>
  <br>
  <div class="image">
  <img id="product-image" src="${pageContext.request.contextPath}/image/transparent.png" alt="${data.name}"/>
  </div>
  <div class="spec">
	  <div id="product-spec-price">PRICE : ${data.price}</div>
	  <br>
	  <div id="product-spec-brand">BRAND : ${data.brand}</div>
	  <br>
	  <div id="product-spec-type">TYPE : ${data.type}</div>
	  <br>
	  <div id="product-spec-rakuten-url"></div>
	  </div>
</div>

</body>
</html>
