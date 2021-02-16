<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!-- 作成者：藤野優樹 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TOP</title>
    <link href="css/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="header.jsp" />
<div class="page">
<div id="main">
    <form method="get" action="showproducts">
        <input type="submit">
    </form>
</div>
<div id="search">
	<form method="get" action="showproducts">
		<input type="text" name="search" value size="36" maxvalue="30" placeholder="キーワードで検索" maxlength="20">
		<input type="submit" value="検索">
	</form>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>