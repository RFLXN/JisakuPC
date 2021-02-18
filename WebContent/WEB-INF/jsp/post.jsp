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
  <form action="postbuild" method="post" name="form1" onSubmit="return check()">
    <p>タイトル:<input type="text" name="title"></p>
    <p>コメント:<br>
    <textarea name="description" rows="5" cols="40"></textarea>
    </p>

    <c:forEach var="list" items="${ data }">
    <p>見積もりを選択：<input type="radio" name="buildno" value="${list.buildNo }" required>
    	${list.buildName}
    </p>
    </c:forEach>

    <p><input type="submit" value="送信"><input type="reset" value="リセット">
    </p>
  </form>

<!-- 0218 坂入 見積もりをforEachするので削除 -->
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>