<%@ page import="bean.UserFlag" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
  <link href="css/header.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<header>

  <table width="100%">
    <div class="header-body">
      <tr>
        <td width="20%">
          <div id="logo">
            <a href="/JisakuPC/index.jsp">JisakuPC</a>
          </div>
        </td>
        <td width="20%">
          <div id="build">
            <a href="build">見積り</a>
          </div>
        </td>
        <td width="20%">
          <div id="post">
            <a href="postlist">投稿</a>
          </div>
        </td>
        <c:choose>
          <c:when test="${sessionScope.loginFlag.correctUser}">
            <td width="40%">
              <div id="user-info">
                <a href="mypage">${sessionScope.loginFlag.userId} 様</a>
              </div>
            </td>
          </c:when>
          <c:otherwise>
            <td width="20%">
              <div id="login">
                <a href="login">ログイン</a>
              </div>
            </td>
            <td width="20%">
              <div id="signup">
                <a href="signup">新規登録</a>
              </div>
            </td>
          </c:otherwise>
        </c:choose>

      </tr>
    </div>
  </table>
</header>
</body>
</html>