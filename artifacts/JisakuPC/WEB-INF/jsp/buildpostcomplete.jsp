<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link href="css/buildpostcomplete.css" rel="stylesheet" type="text/css"/>
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
作成が完了しました
<div id="buildpost">
  <form method="get" action="postlist">
    <button id="buildpost" onclick=" goNextPage();">投稿TOPへ</button>
  </form>
</div>
</body>
</html>