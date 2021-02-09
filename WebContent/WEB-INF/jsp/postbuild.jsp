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
<form action="postbuild" method="post">
<p>タイトル:<input type="text" name="title" required></p>
<p>コメント:<textarea name="description" rows="5" cols="40" required></textarea></p>
<p>build_no:<input type="text" name="buildno" required></p>
<p>user_no:<input type="text" name="userno" required></p>
<input type="hidden" name="userno" value="${post.userno}">
<p><input type="submit" value="送信"></p>
</form>
</body>
</html>