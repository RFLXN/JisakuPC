<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>掲示板</title>
</head>
<body>
<form action="deletepost" method="post">
  <c:forEach var="post" items="${data}">
  <table border="1">
    <tr><th>post_no</th><th>user_id</th><th>build_id</th><th>タイトル</th><th>説明</th><th>投稿日</th></tr>
    <tr><td>${post.no}</td><td>${post.userno}</td><td>${post.buildno}</td><td>${post.title}</td><td>${post.description}</td><td>${post.date}</td></tr>
    </table>
	<input type="hidden" name="postno" value="${3}">
    <input type="submit" name="delete" value="削除">
    <!-- 戻るボタンみたいなの作る -->
  </c:forEach>
</form>
</body>
</html>