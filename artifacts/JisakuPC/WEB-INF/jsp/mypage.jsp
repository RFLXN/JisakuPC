<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bean.UserFlag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>
<head>
  <title>My Page</title>
  <link href="css/mypage.css" rel="stylesheet" type="text/css"/>
  <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<jsp:include page="/header.jsp"/>

<main class="container">
  <div class="container col-xs-12">
    <div id="heading" class="col-xs-12">
      <h2>マイページ: ${sessionScope.loginFlag.userId}様</h2>
    </div>

    <div id="build-list-section" class="col-xs-12">
      <h3>見積りリスト</h3><br>
      <c:forEach var="build" items="${data}">
        <p>見積り名: ${build.buildName}</p>
        <table class="table table-hover">
          <tbody>
            <tr>
              <th>No</th>
              <th>パーツ名</th>
              <th>値段</th>
            </tr>
            <c:forEach var="part" items="${build.products}">
              <tr>
                <td>${part.no}</td>
                <td>${part.name}</td>
                <td>${part.price}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <br>
      </c:forEach>
    </div>

  </div>


</main>

<jsp:include page="/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
