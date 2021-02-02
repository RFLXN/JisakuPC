<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
  <title>Sign Up</title>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/signup.js"></script>

<h1>Sign Up</h1>
<div id="signup-area">
  <form id="signup-form" method="post" action="signup-result">
    ID <input id="signup-id" type="text" name="id"><br>
    PASSWORD <input id="signup-password" type="text" name="password">
  </form>
  <br>
  <div id="already-used-warning">
    <p id="warning-text"></p>
  </div>
  <br>
  <button id="signup-btn" onclick="signup();">Sign Up</button>
</div>

</body>
</html>
