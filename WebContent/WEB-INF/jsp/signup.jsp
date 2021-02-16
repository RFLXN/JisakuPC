<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
  <link href="css/signup.css" rel="stylesheet" type="text/css"/>
  <title>Sign Up</title>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/signup.js"></script>

<jsp:include page="/header.jsp"/>
<div class="signup-body">
  <h1>Sign Up</h1>
  <div id="signup-area">
    <form id="signup-form" method="post" action="signup-result">
      ID <input id="signup-id" type="text" name="id"><br>
      PASSWORD <input id="signup-password" type="text" name="password">
      <div class="warning-container">
        <p class="warning-text"></p>
      </div>
    </form>
    <div class="signup-submit">
      <button id="signup-btn" onclick="signup()">新規登録</button>
    </div>
  </div>
</div>

<jsp:include page="/footer.jsp"/>
</body>
</html>
