<%@ page import="bean.UserFlag" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
  <title>LOGIN</title>
</head>
<body>
  <h1>LOGIN</h1>

  <form method="post" action="login-process">
    ID <input type="text" name="id"><br>
    PASSWORD <input type="password" name="password"><br>
    <%
      UserFlag user = (UserFlag) request.getAttribute("data");
      if(user != null) {
        if(!user.isCorrectUser()) {
            out.print("Login Error");
        }
      }
    %>
    <input type="submit" name="LOGIN">
  </form>
</body>
</html>
