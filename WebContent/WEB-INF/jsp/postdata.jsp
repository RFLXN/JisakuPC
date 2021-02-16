<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">

  <title>掲示板</title>
</head>
<body>
<table border="1">
  <tr>
    <th>pname</th>
  </tr>
  <c:forEach var="post" items="${data}">
    <tr>
      <td>${post.pname}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
