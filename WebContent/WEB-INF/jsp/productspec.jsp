<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
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
    $(function () {
        productspec("${data.no}", ${data.price});
    });
</script>

<div id="product-spec">
  <h1>${data.name}</h1>
  <!-- <div id="product-spec-pid">PID : ${data.no}</div>
  <br>
  <div id="product-spec-name">NAME : ${data.name}</div>
  <br>-->
  <p>PRICE :</p>
  <div id="product-spec-price"> ${data.price}</div>
  <br>
  <div id="product-spec-brand">BRAND : ${data.brand}</div>
  <br>
  <div id="product-spec-type">TYPE : ${data.type}</div>
  <br>
  <div id="product-spec-rakuten-url"></div>
</div>

<jsp:include page="/footer.jsp"/>
</body>
</html>
