<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
<head>
  <title>My Page</title>
  <link href="css/mypage.css" rel="stylesheet" type="text/css"/>
  <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="css/mypage.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<jsp:include page="/header.jsp"/>

<main class="container">
  <div class="container col-xs-12">
    <div id="heading" class="col-xs-12">
      <h2>マイページ: ${sessionScope.loginFlag.userId}様</h2>
    </div>
    <div id="user-control-section" class="col-xs-12">
      <form action="logout" method="get">
        <button class="btn btn-danger" type="button" onclick="checkLogout(this)">ログアウト</button>
      </form>
    </div>
    <div id="build-list-section" class="col-xs-12">
      <h3>見積りリスト</h3><br>
      <c:forEach var="build" items="${data}">
        <p>見積り名: ${build.buildName}</p>
        <form>
          <input type="hidden" name="buildNo" value="${build.buildNo}">
          <button class="btn btn-default" formmethod="get" formaction="post">
            投稿
          </button>

          <button class="btn btn-danger delete-button" formmethod="get" formaction="deletebuild">
            削除
          </button>
        </form>
        <table class="table table-hover">
          <tbody>
          <tr>
            <th>No</th>
            <th>パーツ名</th>
            <th>値段</th>
          </tr>
          <c:forEach var="part" items="${build.getStackedProducts()}">
            <tr onclick="location.href='productspec?pid=${part.product.no}'">
              <td>${part.product.no}</td>
              <td>${part.product.name} * ${part.stack}</td>
              <td>${part.getStackedPrice()}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <br>
        <div class="sokei">総計: ${build.getTotalPrice()} 円
        </div>
      </c:forEach>
    </div>

    <c:if test="${sessionScope.loginFlag.admin}">
      <div id="admin-pannel" class="col-xs-12">
        <form action="productmanagement">
          <button type="submit" class="btn btn-default">管理者専用ぺぇじ</button>
        </form>
      </div>
    </c:if>
  </div>
</main>

<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/mypage.js"></script>

</body>
</html>
