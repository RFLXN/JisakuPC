<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bean.UserFlag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
<head>
  <title>My Page</title>
</head>
<body>
<h1>My Page</h1>
ID : ${sessionScope.loginFlag.userId}
<br>
User No : ${sessionScope.loginFlag.userNo}

<c:forEach var="build" items="${data}">
  <table>
    <tr>
      <th>${build.buildNo}</th>
      <th>${build.buildName}</th>
    </tr>
    <c:forEach var="part" items="${build.products}">
      <tr>
        <td>${part.no}</td>
        <td>${part.name}</td>
        <td>${part.price}</td>
      </tr>
    </c:forEach>
  </table>
</c:forEach>
</body>
</html>
