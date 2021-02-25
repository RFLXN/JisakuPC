<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">

  <title>掲示板</title>
  <link href="css/showpost.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/header.jsp"/>
<form action="deletepost" method="post">
  <c:forEach var="post" items="${data}">
    <h1>${post.title}</h1>
    <h3>${post.userid}</h3>
    <h3>${post.date}</h3>
    <h3>${post.description}</h3>
    <table border="1">
      <tr>
        <th>分類</th>
        <th>商品名</th>
        <th>値段</th>
        <th>商品番号</th>
      </tr>
      <c:forEach var="posts" items="${post.list}">
        <tr>
          <td>${posts.type}</td>
          <td>${posts.pname}</td>
          <td>${posts.price}</td>
          <td>${posts.productno}</td>
        </tr>
      </c:forEach>
      <input type="hidden" name="postno" value="${post.no}">
      <c:if test="${post.userid == sessionScope.loginFlag.userId || sessionScope.loginFlag.isAdmin()}">
        <input type="submit" name="delete" value="削除">
      </c:if>
    </table>
  </c:forEach>
</form>
</body>
</html>
