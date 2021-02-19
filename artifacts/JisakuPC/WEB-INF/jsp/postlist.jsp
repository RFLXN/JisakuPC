<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Title</title>
  <link href="css/postlist.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="post-body">
  <div class="page">
    <h1>みんなの自作PC</h1>
  </div>
  <div id="new-post-section">
    <form action="post" method="get">
      <input type="submit" value="新規作成">
    </form>
  </div>
  <div class="page2">
    <table width="100%" cellpadding="30">
      <c:forEach var="list" items="${data}">
        <p>タイトル:<c:out value="${list.title}"/><br>
          説明:<c:out value="${list.description}"/></p>
      </c:forEach>
    </table>
  </div>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
