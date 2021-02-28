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
      <input type="submit" value="新規作成" class="sinki">
    </form>
  </div>
  <div class="page2">
    <table border="2" width="100%" cellpadding="30">
      <c:forEach var="list" items="${data}">
        <div class="page3"><p>タイトル:<c:out value="${list.title}"/><br>
          説明:<c:out value="${list.description}"/></p>
        <form action="showpost">
          <input type="hidden" name="postno" value="${list.no}">
          <input type="submit" name="show" value="詳細">
        </form></div>
      </c:forEach>
    </table>
  </div>
</div>

</body>
</html>
