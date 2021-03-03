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
      <input type="submit" value="投稿する(ログイン必須)" class="sinki">
    </form>
  </div>
  <div class="page2">

    <c:forEach var="list" items="${data}">
    <table width="100%" cellpadding="30">
      <tr>

        <td width="70%">
          <div class="page3"><p>タイトル: <c:out value="${list.title}"/></p>
            <p>コメント:
            <div class="page4">${list.description}</div>
            </p>
        </td>
        <td width="30%">
          <form action="showpost">
            <input type="hidden" name="postno" value="${list.no}">
            <input type="submit" name="show" value="詳細" class="show">
          </form>
  </div>
  </td>
  </tr>
  </table>
  </c:forEach>
</div>
</div>

</body>
</html>
