<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <link href="css/signup.css" rel="stylesheet" type="text/css"/>
  <title>Sign Up</title>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/signup.js"></script>
<script>
function checkForm(target)
{
    let str=target.value;
    while(str.match(/[^A-Z^a-z\d\-]/))
    {
        str=str.replace(/[^A-Z^a-z\d\-]/,"");
    }
    target.value=str;
}
function CheckPassword(confirm){
	// 入力値取得
	var input1 = document.form1.password.value;
	var input2 = confirm.value;
	// パスワード比較
	if(input1 != input2){
		confirm.setCustomValidity("入力値が一致しません。");
	}else{
		confirm.setCustomValidity('');
	}
}
</script>
<jsp:include page="/header.jsp"/>
<div class="signup-body">
  <h1>Sign Up</h1>
  <div id="signup-area">
    <form id="signup-form" method="post" action="signup-result" name="form1">
      ID(半角) <input id="signup-id" type="text" name="id" onInput="checkForm(this)"><br>
      PASSWORD(半角) <input id="signup-password" type="text" name="password" onInput="checkForm(this)">
      <%-- Re:PASSWORD(半角) <input id="signup-password"type="text" name="confirm" onInput="CheckPassword(this)"> --%>
      <div class="warning-container">
        <p class="warning-text"></p>
      </div>
    </form>
    <div class="signup-submit">
      <button id="signup-btn" onclick="signup()">新規登録</button>
    </div>
  </div>
</div>
</body>
</html>
