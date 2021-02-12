<%@ page import="bean.UserFlag" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
  <link href="css/login.css" rel="stylesheet" type="text/css" />
  <title>LOGIN</title>
</head>
<body>
  <jsp:include page="/header.jsp" />
  <div class="loginbody">
  <h1>LOGIN</h1>

  <form method="post" action="login-process">
  <div class="form-item">
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
   </div>
   <div class="loginsubmit">
    <input type="submit" name="LOGIN">
   </div>
  </form>
  </div>
  <jsp:include page="/footer.jsp" />
</body>
</html>
