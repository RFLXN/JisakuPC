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
</head>
<body>
<jsp:include page="/header.jsp"/>

<div class="page">
  <h1>自作PCを作成</h1>
  <br>
  <h3>パーツリスト</h3>
  <br>
  <div id="build-select-section">
    <c:if test="${not empty pageScope.buildList}">
      <c:if test="${pageScope.buildList.size() gt 0}">
        <form method="get" action="selectbuild">
          <label for="builds">見積りを選択</label>
          <select id="builds" name="buildNo">
            <c:forEach var="build" items="${buildList}">
              <c:choose>
                <c:when test="${param.buildNo eq build.buildNo}">
                  <option selected="selected" value="${build.buildNo}"><c:out value="${build.buildName}"/></option>
                </c:when>
                <c:otherwise>
                  <option value="${build.buildNo}"><c:out value="${build.buildName}"/></option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </select>
          <input type="submit" value="選択">
        </form>
      </c:if>
    </c:if>
  </div>


  <table width="100%" cellpadding="30">

    <div id="cpu">
      <tr>
        <td width="20%">CPU</td>
        <td width="55%">
          <c:forEach var="part" items="${sessionScope.build.products}">
            <c:if test="${part.type eq 'cpu'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.no}"><c:out value="${part.name}"/>: <c:out
                        value="${part.price}"/></a>
                <input type="hidden" name="partNo" value="${part.no}">
                <button formmethod="get" formaction="deletebuildpart">X</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td width="5%">
          <form>
            <input type="hidden" name="productType" value="cpu">
            <input type="submit" formaction="searchproduct" formmethod="get" value="追加">
          </form>
        </td>
        <form method="get" action="searchproduct">
          <input type="hidden" name="productType" value="cpu">
          <td width="60%">おすすめ:123
            <input type="submit" name="productName" value="Ryzen 5">
            <input type="submit" name="productName" value="Core i9">
            <input type="submit" name="productName" value="Core i7">
          </td>
        </form>
      </tr>
    </div>

    <div id="gpu">
      <tr>
        <td width="20%">GPU</td>
        <td width="55%">
          <c:forEach var="part" items="${sessionScope.build.products}">
            <c:if test="${part.type eq 'gpu'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.no}"><c:out value="${part.name}"/>: <c:out
                        value="${part.price}"/></a>
                <input type="hidden" name="partNo" value="${part.no}">
                <button formmethod="get" formaction="deletebuildpart">X</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td width="5%">
          <form>
            <input type="hidden" name="productType" value="gpu">
            <input type="submit" formaction="searchproduct" formmethod="get" value="追加">
          </form>
        </td>
        <form method="get" action="searchproduct">
          <input type="hidden" name="productType" value="gpu">
          <td width="60%">おすすめ:123
            <input type="submit" name="productName" value="RTX 3070">
            <input type="submit" name="productName" value="RTX 2070 SUPER">
            <input type="submit" name="productName" value="RX 5700 XT">
          </td>
        </form>
      </tr>
    </div>

    <div id="ram">
      <tr>
        <td width="20%">メモリ</td>
        <td width="55%">
          <c:forEach var="part" items="${sessionScope.build.products}">
            <c:if test="${part.type eq 'ram'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.no}"><c:out value="${part.name}"/>: <c:out
                        value="${part.price}"/></a>
                <input type="hidden" name="partNo" value="${part.no}">
                <button formmethod="get" formaction="deletebuildpart">X</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td width="5%">
          <form>
            <input type="hidden" name="productType" value="ram">
            <input type="submit" formaction="searchproduct" formmethod="get" value="追加">
          </form>
        </td>
        <form method="get" action="searchproduct">
          <input type="hidden" name="productType" value="ram">
          <td width="60%">おすすめ:boostClockとか
            <input type="submit" name="clock" value="2666">
            <input type="submit" name="words" value="!16GB×2">
            <input type="submit" name="words" value="!8GB×2">
          </td>
        </form>
      </tr>
    </div>

    <div id="cpu_cooler">
      <tr>
        <td width="20%">クーラー</td>
        <td width="55%">
          <c:forEach var="part" items="${sessionScope.build.products}">
            <c:if test="${part.type eq 'cpu_cooler'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.no}"><c:out value="${part.name}"/>: <c:out
                        value="${part.price}"/></a>
                <input type="hidden" name="partNo" value="${part.no}">
                <button formmethod="get" formaction="deletebuildpart">X</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td width="5%">
          <form>
            <input type="hidden" name="productType" value="cpu_cooler">
            <input type="submit" formaction="searchproduct" formmethod="get" value="追加">
          </form>
        </td>
        <form method="get" action="searchproduct">
          <input type="hidden" name="productType" value="cpu_cooler">
          <td width="60%">おすすめ:1
            <input type="submit" name="productName" value="Noctua">
            <input type="submit" name="words" value="!Socket AM4">
            <input type="submit" name="words" value="!LGA1200">
          </td>
        </form>
      </tr>
    </div>

    <div id="case">
      <tr>
        <td width="20%">ケース</td>
        <td width="55%">
          <c:forEach var="part" items="${sessionScope.build.products}">
            <c:if test="${part.type eq 'case'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.no}"><c:out value="${part.name}"/>: <c:out
                        value="${part.price}"/></a>
                <input type="hidden" name="partNo" value="${part.no}">
                <button formmethod="get" formaction="deletebuildpart">X</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td width="5%">
          <form>
            <input type="hidden" name="productType" value="case">
            <input type="submit" formaction="searchproduct" formmethod="get" value="追加">
          </form>
        </td>
        <form method="get" action="searchproduct">
          <input type="hidden" name="productType" value="case">
          <td width="60%">おすすめ:123
            <input type="submit" name="factor" value="MicroATX">
            <input type="submit" name="factor" value="ATX">
            <input type="submit" name="factor" value="Mini-ITX">
          </td>
        </form>
      </tr>
    </div>

    <div id="mother_board">
      <tr>
        <td width="20%">マザーボード</td>
        <td width="55%">
          <c:forEach var="part" items="${sessionScope.build.products}">
            <c:if test="${part.type eq 'mother_board'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.no}"><c:out value="${part.name}"/>: <c:out
                        value="${part.price}"/></a>
                <input type="hidden" name="partNo" value="${part.no}">
                <button formmethod="get" formaction="deletebuildpart">X</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td width="5%">
          <form>
            <input type="hidden" name="productType" value="mother_board">
            <input type="submit" formaction="searchproduct" formmethod="get" value="追加">
          </form>
        </td>
        <form method="get" action="searchproduct">
          <input type="hidden" name="productType" value="mother_board">
          <td width="60%">おすすめ:123
            <input type="submit" name="formfactor" value="Mini ITX">
            <input type="submit" name="chipset" value="AMD B550">
            <input type="submit" name="chipset" value="INTEL Z490">
          </td>
        </form>
      </tr>
    </div>

    <div id="storage">
      <tr>
        <td width="20%">ストレージ</td>
        <td width="55%">
          <c:forEach var="part" items="${sessionScope.build.products}">
            <c:if test="${part.type eq 'storage'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.no}"><c:out value="${part.name}"/>: <c:out
                        value="${part.price}"/></a>
                <input type="hidden" name="partNo" value="${part.no}">
                <button formmethod="get" formaction="deletebuildpart">X</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td width="5%">
          <form>
            <input type="hidden" name="productType" value="storage">
            <input type="submit" formaction="searchproduct" formmethod="get" value="追加">
          </form>
        </td>
        <form method="get" action="searchproduct">
          <input type="hidden" name="productType" value="storage">
          <td width="60%">おすすめ:1
            <input type="submit" name="size" value="M.2 (Type2280)">
            <form method="get" action="searchproduct">
              <input type="hidden" name="size" value="2.5">
              <input type="submit" value="2.5インチ">
            </form>
            <form method="get" action="searchproduct">
              <input type="hidden" name="volume" value="480,512">
              <input type="submit" value="480GB~512GB未満">
            </form>
          </td>
        </form>
      </tr>
    </div>

    <div id="power_supply">
      <tr>
        <td width="20%">電源</td>
        <td width="55%">
          <c:forEach var="part" items="${sessionScope.build.products}">
            <c:if test="${part.type eq 'power_supply'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.no}"><c:out value="${part.name}"/>: <c:out
                        value="${part.price}"/></a>
                <input type="hidden" name="partNo" value="${part.no}">
                <button formmethod="get" formaction="deletebuildpart">X</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td width="5%">
          <form>
            <input type="hidden" name="productType" value="power_supply">
            <input type="submit" formaction="searchproduct" formmethod="get" value="追加">
          </form>
        </td>
        <form method="get" action="searchproduct">
          <input type="hidden" name="productType" value="power_supply">
          <td width="60%">おすすめ:1
            <form method="get" action="searchproduct">
              <input type="hidden" name="factor" value="[&quot;SFX&quot;]">
              <input type="submit" value="SFX">
            </form>
            <form method="get" action="searchproduct">
              <input type="hidden" name="W" value="300,600">
              <input type="submit" value="300W~600W未満">
            </form>
            <form method="get" action="searchproduct">
              <input type="hidden" name="W" value="600,800">
              <input type="submit" value="600W~800W未満">
            </form>
          </td>
        </form>
      </tr>
    </div>

    <div id="case_fan">
      <tr>
        <td width="20%">ケースファン</td>
        <td width="55%">
          <c:forEach var="part" items="${sessionScope.build.products}">
            <c:if test="${part.type eq 'case_fan'}">
              <form>
                <a style="color: black;" href="productspec?pid=${part.no}"><c:out value="${part.name}"/>: <c:out
                        value="${part.price}"/></a>
                <input type="hidden" name="partNo" value="${part.no}">
                <button formmethod="get" formaction="deletebuildpart">X</button>
              </form>
              <br>
            </c:if>
          </c:forEach>
        </td>
        <td width="5%">
          <form>
            <input type="hidden" name="productType" value="case_fan">
            <input type="submit" formaction="searchproduct" formmethod="get" value="追加">
          </form>
        </td>
        <form method="get" action="searchproduct">
          <input type="hidden" name="productType" value="case_fan">
          <td width="60%">おすすめ:34
            <form method="get" action="searchproduct">
              <input type="hidden" name="size" value="140">
              <input type="submit" value="140mm角">
            </form>
            <form method="get" action="searchproduct">
              <input type="hidden" name="size" value="120">
              <input type="submit" value="120mm角">
            </form>
            <form method="get" action="searchproduct">
              <input type="submit" name="productName" value="クーラーマスター">
              <input type="submit" name="productName" value="Corsair">
            </form>
          </td>
        </form>
      </tr>
    </div>

  </table>
  <div id="total-prize-section">
    <c:set var="totalPrice" value="0"/>
    <c:forEach var="part" items="${sessionScope.build.products}">
      <fmt:parseNumber var="i" type="number" value="${part.price}"/>
      <fmt:parseNumber var="j" type="number" value="${totalPrice}"/>
      <c:set var="totalPrice" value="${i+j}"/>
    </c:forEach>
    <h4>総計金額: <c:out value="${totalPrice}"/></h4>
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
      見積り名 <input type="text" name="buildName" class="build-action-input" value="${buildName}">
      <input type="submit" value="この見積を保存">
    </form>
  </div>

</div>

<%--  <br><tr><td>総計</td></tr><tr><td>円</td></tr>
<div>
 <form method="post" action="">
  <br><input type="submit" value="保存">
 </form>
</div>--%>
<jsp:include page="/footer.jsp"/>

</body>
</html>