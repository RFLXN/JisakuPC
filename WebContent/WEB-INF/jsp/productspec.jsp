<%@ page import="bean.Product" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="bean.ProductTypeInfo" %>
<%@ page import="org.json.JSONException" %>
<%@ page import="bean.ProductSpecInfo" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
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
  <br>
  <div class="image">
    <img id="product-image" src="${pageContext.request.contextPath}/image/noimg.png" alt="${data.name}"/>
  </div>
  <div class="spec">
    <div id="product-spec-price">値段 : ${data.price}</div>
    <br>
    <div id="product-spec-brand">ブランド名: ${data.brand}</div>
    <br>
    <div id="product-spec-type">種類: ${ProductTypeInfo.getTranslatedTypeName(data.type)}</div>
    <br>
    <div id="product-spec-rakuten-url"></div>
  </div>
  <div id="product-spec-section">
    <%
      Product product = (Product) request.getAttribute("data");
      if (!product.getSpec().equals("")) {
        JSONObject spec = new JSONObject(product.getSpec());
        Iterator<String> keys = spec.keys();

        Map<String, ProductSpecInfo> infoMap = ProductSpecInfo.getInfoMap();

        StringBuilder resultBuff = new StringBuilder();
        while (keys.hasNext()) {
          String specName = keys.next();

          String translatedSpecName = specName;

          ProductSpecInfo info = null;

          if (infoMap.containsKey(specName)) {
            info = infoMap.get(specName);
            translatedSpecName = info.getSpecName();
          }

          resultBuff.append("<tr><td>").append(translatedSpecName).append("</td><td>");

          Object valueBuff = spec.get(specName);

          String valueText = "";

          try {
            StringBuilder valueTextBuff = new StringBuilder();
            JSONArray values = (JSONArray) valueBuff;
            for (int i = 0; i < values.length(); i++) {
              String value = values.getString(i);
              valueTextBuff.append(", ").append(value);
            }
            valueTextBuff.delete(0, 2);
            valueText = valueTextBuff.toString();
          } catch (ClassCastException e) {
            try {
              valueText = spec.getString(specName);
            } catch (JSONException ee) {
              try {
                valueText = Integer.toString(spec.getInt(specName));
              } catch (JSONException eee) {
                try {
                  valueText = Double.toString(spec.getDouble(specName));
                } catch (JSONException eeee) {
                  try {
                    boolean b = spec.getBoolean(specName);
                    if (b) {
                      valueText = "O";
                    } else {
                      valueText = "X";
                    }
                  } catch (JSONException ignored) {
                  }
                }
              }
            }
          }
          if (info != null && info.getUnit() != null) {
            valueText = valueText + info.getUnit();
          }
          resultBuff.append(valueText).append("</td></tr>");
        }
        out.print("<table>" + resultBuff.toString() + "</table>");
      }
    %>
  </div>
</div>

</body>
</html>
