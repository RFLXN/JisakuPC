<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
  <link href="css/signup.css" rel="stylesheet" type="text/css" />
  <title>Sign Up</title>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/signup.js"></script>

<jsp:include page="/header.jsp" />
<div class="signup-body">
<h1>Sign Up</h1>

<form id="signup-form" method="post" action="signup-result">
  <div id="signup-area">
    ID <input id="signup-id" type="text" name="id"><br>
    PASSWORD <input id="signup-password" type="text" name="password">

  <div id="already-used-warning">
    <p id="warning-text"></p>
  </div>
 </div>
<div class="signup-submit">
   <button id="signup-btn" onclick="signup();">新規登録</button>
</div>
</form>
</div>

<jsp:include page="/footer.jsp" />
</body>
</html>
