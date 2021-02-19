<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link href="css/post.css" rel="stylesheet" type="text/css"/>


  <script type="text/javascript">

      function check() {
          var flag = 0;

          if (document.form1.name.value == "") {
              flag = 1;
          } else if (document.form1.comment.value == "") {
              flag = 1;
          }

          if (flag) {
              window.alert('タイトルとコメントを入力してください');
              return false; // 送信を中止
          } else {
              return true; // 送信を実行
          }
      }
  </script>

  <title>掲示板</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="post-body">
  <form action="postbuild" class="post"  method="post" name="form1" onSubmit="return check()">
  	<h3>新規投稿</h3>
    <p>タイトル:<input type="text" name="title" placeholder="自作PC"></p>
    <p>コメント:</ br>
    <textarea name="description" rows="5" cols="40" placeholder="Intel Core i9 9900KF BOX..."></textarea>
    </p>

    <p><div class="button"><input type="submit" value="送信"><input type="reset" value="リセット"></div>
    </p>
  </form>
	<div class="page">
	<h1>みんなの自作PC</h1>
	</div>
	<div class="page2">
	<table width="100%" cellpadding="30">
  <c:forEach var="list" items="${data}">
    <p>タイトル:<c:out value="${list.title}"/><br>
      説明:<c:out value="${list.description}"/></p>
  </c:forEach>
  	</table>
  	</div>


</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>