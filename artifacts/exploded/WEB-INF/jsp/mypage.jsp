<%@ page import="bean.UserFlag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
<head>
  <title>My Page</title>
</head>
<body>
<h1>My Page</h1>
<%
  UserFlag user = (UserFlag) session.getAttribute("login-flag");
  out.println("ID : " + user.getUserId());
  out.println("<br>");
  out.println("User No : " + user.getUserNo());
%>
</body>
</html>
