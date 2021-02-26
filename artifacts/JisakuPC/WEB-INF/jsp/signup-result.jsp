<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>登録結果</title>
  <link href="css/signup-result.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/header.jsp"/>

<main>
  <h1>登録結果</h1>

  <div class="result-area">
    <c:choose>
      <%-- 登録に成功した時 --%>
      <c:when test="${data.correctUser}">
        <div class="result-text">
          <label>${data.userId} 様</label> <br>
          登録に成功しました。 <br>
        </div>


        <div class="result-button-container">
          <a class="result-button" href="${pageContext.request.contextPath}/">トップに戻る</a>
        </div>
      </c:when>

      <%-- 登録に失敗した時 --%>
      <c:otherwise>
        <div class="result-text">
          登録に失敗しました。 <br>
        </div>

        <div class="result-button-container">
          <a class="result-button" href="${pageContext.request.contextPath}/">トップに戻る</a>
          <a class="result-button" href="${pageContext.request.contextPath}/signup">新規登録に戻る</a>
        </div>
      </c:otherwise>
    </c:choose>
  </div>

</main>
</body>
</html>
