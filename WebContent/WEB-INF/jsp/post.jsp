<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>掲示板</title>
</head>
<body>
	<div>
		<jsp:include page="postbuild.jsp" flush="true"/>
	</div>
	<div>
		<c:forEach var="post" items="${data}">
			<form action="showpost" method="post" name="form1" onSubmit="return check()">
				<table border="1">
					<tr><th>タイトル</th></tr>
					<tr><td>${post.title}</td></tr>
				</table>
				<input type="hidden" name="postno" value="${post.no}">
				<input type="submit" value="詳細">
			</form>
		</c:forEach>
	</div>
</body>
</html>