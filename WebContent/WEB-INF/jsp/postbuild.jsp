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
<p>タイトル:<input type="text" name="title"></p>
<p>コメント:<br>
<textarea name="description" rows="5" cols="40"></textarea>
</p>
<p><input type="submit" value="送信"><input type="reset" value="リセット">
</p>
</form>


</body>
</html>