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
		  <h1>${post.title}</h1>
		  <h3>${post.userid}</h3>
			<h3>${post.date}</h3>
			<h3>${post.description}</h3>
			<table border="1">
			<tr><th>商品番号</th><th>商品名</th></tr>
			<c:forEach var="posts" items="${post.list}">
			<tr><td>${posts.productno}</td><td>${posts.pname}</td></tr>
			</c:forEach>
			<input type="hidden" name="postno" value="${post.no}">
			<input type="submit" name="delete" value="削除">
			</table>
		</c:forEach>
	</form>
</body>
</html>
