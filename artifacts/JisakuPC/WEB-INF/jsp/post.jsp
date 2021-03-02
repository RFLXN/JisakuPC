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
  <link href="css/post.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="post-body">
  <form action="postbuild" class="post" method="post" name="form1" onSubmit="return check()">
    <p>タイトル:<input type="text" name="title" placeholder="文字まで" maxlength='60' required></p>
    <p>コメント:<br>
      <textarea name="description" rows="5" cols="40" placeholder="文字まで" maxlength='800' cols="3" required></textarea>
    </p>

    <div id="build-select-section">
      <label for="build-select">見積もりを選択</label>
      <select id="build-select" name="buildno" required>
        <c:forEach var="list" items="${ data }">
          <c:choose>
            <c:when test="${param.buildNo eq list.buildNo}">
              <option value="${list.buildNo}" selected>${list.buildName}</option>
            </c:when>
            <c:otherwise>
              <option value="${list.buildNo}">${list.buildName}</option>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </select>
    </div>
    <br>
    <div class="button"><input type="submit" value="送信"><input type="reset" value="リセット"></div>

  </form>

  <!-- 0218 坂入 見積もりをforEachするので削除 -->
</div>
</body>
</html>