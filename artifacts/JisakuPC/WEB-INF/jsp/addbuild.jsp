<%@ page import="bean.UserFlag" %>
<%@ page import="bean.Build" %>
<%@ page import="db.dao.factory.AbstractDaoFactory" %>
<%@ page import="db.dao.DAOException" %>
<%@ page import="db.dao.build.BuildDao" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
  UserFlag user = (UserFlag) session.getAttribute("loginFlag");
  List<Build> buildList = null;
  if (user != null && user.getUserNo() != null && !user.getUserNo().equals("")) {
    try {
      BuildDao dao = AbstractDaoFactory.getFactory().getBuildDao();
      buildList = dao.getUserBuilds(user.getUserNo());
      pageContext.setAttribute("buildList", buildList);
    } catch (DAOException ignored) {
    }
  }
%>

<html>
<head>
  <meta charset="UTF-8">
  <title>見積り</title>
  <link href="css/addbuild.css" rel="stylesheet" type="text/css"/>
  <link href="css/bootstrap.table.css" rel="stylesheet" type="text/css"/>
</head>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/addbuild.js"></script>
<script>
    var ctlKey = false;
    document.addEventListener('keydown', function (e) {
        if (e.ctrlKey) ctlKey = true;
        if ((e.which || e.keyCode) == 82 && ctlKey) e.preventDefault();
        if ((e.which || e.keyCode) == 116) e.preventDefault();
    });
    document.addEventListener('keyup', function (e) {
        if (e.ctrlKey) ctlKey = false;
    });
    $(document).on('keydown', function (e) {
        if ((e.which || e.keyCode) == 116) {
            return false;
        }
    });
</script>
<body>
<jsp:include page="/header.jsp"/>

<div class="page">
  <div class="ti">
    <h1>自作PCを作成</h1>

    <br>
    <h3>パーツリスト</h3>
    <br>
    <div id="build-select-section">
      <c:if test="${not empty pageScope.buildList}">
        <c:if test="${pageScope.buildList.size() gt 0}">
          <form method="get" action="selectbuild">
            <label for="builds">過去の見積りを選択</label>
            <select id="builds" name="buildNo">
              <option value="new">新しい見積もり</option>
              <c:forEach var="build" items="${buildList}">
                <c:choose>
                  <c:when test="${param.buildNo eq build.buildNo}">
                    <option selected="selected" value="${build.buildNo}"><c:out value="${build.buildName}"/></option>
                  </c:when>
                  <c:when test="${param.buildName eq build.buildName}">
                    <option selected="selected" value="${build.buildNo}"><c:out value="${build.buildName}"/></option>
                  </c:when>
                  <c:otherwise>
                    <option value="${build.buildNo}"><c:out value="${build.buildName}"/></option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </select>
            <input type="submit" value="選択">&nbsp;&nbsp;&nbsp;<button type="button" onclick="resetBuild()">リセット</button>
          </form>
        </c:if>
      </c:if>
    </div>

    <%-- tableいborderを追加、tdにalignを追加 --%>
    <table class="table">
      <tr id="cpu-section">
        <td>CPU</td>
        <td width="50%" id="cpu-section-parts">
          <c:forEach var="part" items="${sessionScope.build.getStackedProducts()}">
            <c:if test="${part.product.type eq 'cpu'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.product.no}"><c:out value="${part.product.name}"/>
                  * <c:out value="${part.stack}"/>:
                  <c:out value="${part.product.price}"/></a>
                <input type="hidden" name="partNo" value="${part.product.no}">
                <button formmethod="get" formaction="deletebuildpart">削除</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td id="cpu-section-actions">
          <form action="searchproduct" method="get">
            <input type="hidden" name="productType" value="cpu">
            <button type="button" class="add">追加</button>
          </form>
        </td>
        <td id="cpu-section-recommends">
          <form method="get" action="searchproduct">
            <input type="hidden" name="productType" value="cpu">おすすめ:
            <input type="submit" name="productName" value="Ryzen 5" class="osusume">
            <input type="submit" name="productName" value="Core i9" class="osusume">
            <input type="submit" name="productName" value="Core i7" class="osusume">
          </form>
        </td>
      </tr>

      <tr id="gpu-section">
        <td>GPU</td>
        <td width="50%" id="gpu-setcion-parts">
          <c:forEach var="part" items="${sessionScope.build.getStackedProducts()}">
            <c:if test="${part.product.type eq 'gpu'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.product.no}"><c:out value="${part.product.name}"/>
                  * <c:out value="${part.stack}"/>:
                  <c:out value="${part.product.price}"/></a>
                <input type="hidden" name="partNo" value="${part.product.no}">
                <button formmethod="get" formaction="deletebuildpart">削除</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td id="gpu-section-actions">
          <form action="searchproduct" method="get">
            <input type="hidden" name="productType" value="gpu">
            <button type="button" class="add">追加</button>
          </form>
        </td>
        <td id="gpu-section-recommends">
          <form method="get" action="searchproduct">
            <input type="hidden" name="productType" value="gpu">おすすめ:
            <input type="submit" name="productName" value="RTX 3070" class="osusume">
            <input type="submit" name="productName" value="RTX 2070 SUPER" class="osusume">
            <input type="submit" name="productName" value="RX 5700 XT" class="osusume">
          </form>
        </td>
      </tr>

      <tr id="ram-section">
        <td>メモリ</td>
        <td width="50%" id="ram-section-parts">
          <c:forEach var="part" items="${sessionScope.build.getStackedProducts()}">
            <c:if test="${part.product.type eq 'ram'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.product.no}"><c:out value="${part.product.name}"/>
                  * <c:out value="${part.stack}"/>:
                  <c:out value="${part.product.price}"/></a>
                <input type="hidden" name="partNo" value="${part.product.no}">
                <button formmethod="get" formaction="deletebuildpart">削除</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td id="ram-section-actions">
          <form action="searchproduct" method="get">
            <input type="hidden" name="productType" value="ram">
            <button type="button" class="add">追加</button>
          </form>
        </td>
        <td id="ram-section-recommends">
          <form method="get" action="searchproduct">
            <input type="hidden" name="productType" value="ram">おすすめ:
            <input type="submit" name="clock" value="2666" class="osusume">
          </form>
        </td>
      </tr>


      <tr id="cooler-section">
        <td>クーラー</td>
        <td width="50%" id="cooler-section-parts">
          <c:forEach var="part" items="${sessionScope.build.getStackedProducts()}">
            <c:if test="${part.product.type eq 'cpu_cooler'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.product.no}"><c:out value="${part.product.name}"/>
                  * <c:out value="${part.stack}"/>:
                  <c:out value="${part.product.price}"/></a>
                <input type="hidden" name="partNo" value="${part.product.no}">
                <button formmethod="get" formaction="deletebuildpart">削除</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td id="cooler-section-actions">
          <form action="searchproduct" method="get">
            <input type="hidden" name="productType" value="cpu_cooler">
            <button type="button" class="add">追加</button>
          </form>
        </td>
        <td id="cooler-section-recommends">
          <form method="get" action="searchproduct">
            <input type="hidden" name="productType" value="cpu_cooler">おすすめ:
            <input type="submit" name="productName" value="Noctua" class="osusume">
          </form>
        </td>
      </tr>

      <tr id="case-section">
        <td>ケース</td>
        <td width="50%" id="case-section-parts">
          <c:forEach var="part" items="${sessionScope.build.getStackedProducts()}">
            <c:if test="${part.product.type eq 'case'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.product.no}"><c:out value="${part.product.name}"/>
                  * <c:out value="${part.stack}"/>:
                  <c:out value="${part.product.price}"/></a>
                <input type="hidden" name="partNo" value="${part.product.no}">
                <button formmethod="get" formaction="deletebuildpart">削除</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td id="case-section-actions">
          <form action="searchproduct" method="get">
            <input type="hidden" name="productType" value="case">
            <button type="button" class="add">追加</button>
          </form>
        </td>
        <td id="case-section-recommends">
          <form method="get" action="searchproduct">
            <input type="hidden" name="productType" value="case">おすすめ:
            <input type="submit" name="factor" value="MicroATX" class="osusume">
            <input type="submit" name="factor" value="ATX" class="osusume">
            <input type="submit" name="factor" value="Mini-ITX" class="osusume">
          </form>
        </td>
      </tr>

      <tr width="50%" id="motherboard-section">
        <td>マザーボード</td>
        <td id="motherboard-section-parts">
          <c:forEach var="part" items="${sessionScope.build.getStackedProducts()}">
            <c:if test="${part.product.type eq 'mother_board'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.product.no}"><c:out value="${part.product.name}"/>
                  * <c:out value="${part.stack}"/>:
                  <c:out value="${part.product.price}"/></a>
                <input type="hidden" name="partNo" value="${part.product.no}">
                <button formmethod="get" formaction="deletebuildpart">削除</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td id="motherboard-section-actions">
          <form action="searchproduct" method="get">
            <input type="hidden" name="productType" value="mother_board">
            <button type="button" class="add">追加</button>
          </form>
        </td>
        <td id="motherboard-section-recommends">
          <form method="get" action="searchproduct">
            <input type="hidden" name="productType" value="mother_board">おすすめ:
            <input type="submit" name="formfactor" value="Mini ITX" class="osusume">
            <input type="submit" name="chipset" value="AMD B550" class="osusume">
            <input type="submit" name="chipset" value="INTEL Z490" class="osusume">
          </form>
        </td>
      </tr>

      <tr id="storage-section">
        <td>ストレージ</td>
        <td width="50%" id="storage-setcion-parts">
          <c:forEach var="part" items="${sessionScope.build.getStackedProducts()}">
            <c:if test="${part.product.type eq 'storage'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.product.no}"><c:out value="${part.product.name}"/>
                  * <c:out value="${part.stack}"/>:
                  <c:out value="${part.product.price}"/></a>
                <input type="hidden" name="partNo" value="${part.product.no}">
                <button formmethod="get" formaction="deletebuildpart">削除</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td id="storage-section-actions">
          <form action="searchproduct" method="get">
            <input type="hidden" name="productType" value="storage">
            <button type="button" class="add">追加</button>
          </form>
        </td>
        <td id="storage-section-recommends">
          <form method="get" action="searchproduct">
            <input type="hidden" name="productType" value="storage">おすすめ:
            <input type="submit" name="size" value="M.2 (Type2280)" class="osusume">
            <form method="get" action="searchproduct">
              <input type="hidden" name="size" value="2.5">
              <input type="submit" value="2.5インチ" class="osusume">
            </form>
            <form method="get" action="searchproduct">
              <input type="hidden" name="volume" value="480,512">
              <input type="submit" value="480GB~512GB未満" class="osusume">
            </form>
          </form>
        </td>
      </tr>

      <tr id="power-section">
        <td>電源</td>
        <td width="50%" id="power-section-parts">
          <c:forEach var="part" items="${sessionScope.build.getStackedProducts()}">
            <c:if test="${part.product.type eq 'power_supply'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.product.no}"><c:out value="${part.product.name}"/>
                  * <c:out value="${part.stack}"/>:
                  <c:out value="${part.product.price}"/></a>
                <input type="hidden" name="partNo" value="${part.product.no}">
                <button formmethod="get" formaction="deletebuildpart">削除</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td id="power-section-actions">
          <form action="searchproduct" method="get">
            <input type="hidden" name="productType" value="power_supply">
            <button type="button" class="add">追加</button>
          </form>
        </td>
        <td id="power-section-recommends">
          <form method="get" action="searchproduct">
            <input type="hidden" name="productType" value="power_supply">おすすめ:
            <form method="get" action="searchproduct">
              <input type="hidden" name="factor" value="[&quot;SFX&quot;]">
              <input type="submit" value="SFX" class="osusume">
            </form>
            <form method="get" action="searchproduct">
              <input type="hidden" name="W" value="300,600">
              <input type="submit" value="300W~600W未満" class="osusume">
            </form>
            <form method="get" action="searchproduct">
              <input type="hidden" name="W" value="600,800">
              <input type="submit" value="600W~800W未満" class="osusume">
            </form>
          </form>
        </td>
      </tr>

      <tr id="case_fan-section">
        <td>ケースファン</td>
        <td width="50%" id="case_fan-section-parts">
          <c:forEach var="part" items="${sessionScope.build.getStackedProducts()}">
            <c:if test="${part.product.type eq 'case_fan'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.product.no}"><c:out value="${part.product.name}"/>
                  * <c:out value="${part.stack}"/>:
                  <c:out value="${part.product.price}"/></a>
                <input type="hidden" name="partNo" value="${part.product.no}">
                <button formmethod="get" formaction="deletebuildpart">削除</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td id="case_fan-setcion-actions">
          <form action="searchproduct" method="get">
            <input type="hidden" name="productType" value="case_fan">
            <button type="button" class="add">追加</button>
          </form>
        </td>
        <td id="case_fan-section-actions">
          <form method="get" action="searchproduct">
            <input type="hidden" name="productType" value="case_fan">おすすめ:
            <form method="get" action="searchproduct">
              <input type="hidden" name="size" value="140">
              <input type="submit" value="140mm角" class="osusume">
            </form>
            <form method="get" action="searchproduct">
              <input type="hidden" name="size" value="120">
              <input type="submit" value="120mm角" class="osusume">
            </form>
            <form method="get" action="searchproduct">
              <input type="submit" name="productName" value="クーラーマスター" class="osusume">
              <input type="submit" name="productName" value="Corsair" class="osusume">
            </form>
          </form>
        </td>
      </tr>
    </table>
    <div id="total-prize-section">
      <h4>総計金額: <c:out value="${sessionScope.build.getTotalPrice()}"/> 円</h4>
    </div>
    <br>
    <div id="build-action-pannel">
      <form method="get" action="savebuild">
        <c:if test="${not empty pageScope.buildList}">
          <c:if test="${pageScope.buildList.size() gt 0}">
            <c:forEach var="build" items="${buildList}">
              <c:if test="${build.buildNo eq param.buildNo}">
                <c:set var="buildName" value="${build.buildName}"/>
              </c:if>
            </c:forEach>
          </c:if>
        </c:if>
        <c:choose>
          <c:when test="${not empty param.buildName}">
            見積り名 <input type="text" name="buildName" class="build-action-input" value="${param.buildName}" required>
          </c:when>
          <c:otherwise>
            見積り名 <input type="text" name="buildName" class="build-action-input" value="${buildName}" required>
          </c:otherwise>
        </c:choose>
        <button type="button" class="mitsu">この見積を保存</button>
      </form>
    </div>

  </div>

  <%--  <br><tr><td>総計</td></tr><tr><td>円</td></tr>
   <form method="post" action="">
    <br><input type="submit" value="保存">
   </form>
  </div>--%>


</body>
</html>