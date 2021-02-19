<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
  <link href="css/signup-result.css" rel="stylesheet" type="text/css"/>
  <title>Sign Up Result</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="signup-success">
  ${data.correctUser}
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
