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
    <h1>ヘッダーずれた</h1>
  </div>

  <br><br>
  <table width="200" height="300">
    <tr>
      <td width="50%" height="50%">
        <div class="photo1">
          <img src="image/JisakuPC01.jpg" width="450" height="300">
        </div>
      </td>
      <td width="50%" height="50%">
        <div class="setumei">
          <p>
            見積り機能
            好きなパーツを追加して自分だけの見積りを作ることができます。
          </p>
        </div>
      </td>
    </tr>
    <tr>
      <td width="50%" height="50%">
        <div id="photo2">
          <p>
            投稿機能
            作成した見積りを他の人に公開することができます。
          </p>
        </div>
      </td>
      <td width="50%" height="50%">
        <div class="setumei">
          <img src="image/JisakuPC02.jpg" width="450" height="300">
        </div>
      </td>
    </tr>
    <tr>
      <td width="50%" height="50%">
        <div id="photo3">
          <img src="image/JisakuPC03.jpg" width="450" height="300">
        </div>
      </td>
      <td width="50%" height="50%">
        <div class="setumei">
          <p>
            マイページ機能
            ログインすると使用できます。
            過去の自分の見積りを見たりできます。
          </p>
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