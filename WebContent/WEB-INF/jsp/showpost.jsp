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
<form action="showpost" method="post" name="form1" onSubmit="return check()">
  <c:forEach var="post" items="${data}">
  <table border="1">
    <tr><th>タイトル</th></tr>
    <tr><td>${post.title}</td></tr>
    </table>
    <%-- <input type="hidden" name="postno" value="${post.no}"> --%>
    <input type="hidden" name="postno" value="${3}">
    <input type="submit" value="詳細">
  </c:forEach>
</form>
</body>
</html>