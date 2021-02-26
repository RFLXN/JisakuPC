<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!-- 作成者：藤野優樹 -->
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>TOP</title>
  <link href="css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="page">
  <div id="text">
    <h1>JisakuPC</h1>
    <p>このサイトではPCの見積りや作成した見積りを投稿できます。また、他の人が作った見積りも閲覧できるので<br>
      PCパーツの知識があり、自作PCの見積りを一括で簡単に作りたい人。また、作成を自慢したり他の人の作成を<br>
      見たい人におすすめのサイトになっています。
    </p>
  </div>

  <br><br>
  <table width="100%">
    <tr>
      <td width="33%">
        <div id="photo1">
          <img src="image/JisakuPC01.jpg" height="300">
        </div>
      </td>
      <td width="34%">
        <div id="photo2">
          <img src="image/JisakuPC02.jpg" height="300">
        </div>
      </td>
      <td width="33%">
        <div id="photo3">
          <img src="image/JisakuPC03.jpg" height="300">
        </div>
      </td>
    </tr>
  </table>
</div>


<!--  <div class="page">
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
    </div>
</div>  -->


</body>
</html>