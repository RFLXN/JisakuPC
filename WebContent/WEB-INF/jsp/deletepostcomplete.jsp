<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link href="css/deletepostcomplete.css" rel="stylesheet" type="text/css"/>
  <script>
  <%-- 戻るボタンを制限 --%>
history.pushState(null, null, location.href);
window.addEventListener('popstate', (e) => {
  history.go(1);
});
</script>
</head>
<body>
<jsp:include page="/header.jsp"/>
削除が完了しました
<div id="deletepost">
  <form method="get" action="post">
    <button id="deletepost">TOP</button>
  </form>
</div>
</body>
</html>