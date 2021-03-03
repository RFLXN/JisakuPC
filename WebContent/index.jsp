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
	<h2>見積り</h2>
  	見積り機能ではCPUやメモリ、マザーボードなどのパーツからそれぞれ選んで自分だけのPCを作れるようになっています。
  	自作PCの見積りを一括で簡単に作りたい人におすすめの機能になっています。
  	</p>
	</div>
	</td>
  </tr>
  <tr>
	<td width="50%" height="50%">
	<div class="setumei2">
	<p>
	<h2>投稿</h2>
  	投稿機能では見積りで作った自分のPCを投稿、削除することができます。また、
  	他の人の投稿も見ることができるので作成したPCを自慢したり他の人の作成したPCを見たい人におすすめの機能になっています。
  	</p>
	</div>
	</td>
	<td width="50%" height="50%">
	<div id="photo2">
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
	<h2>マイページ</h2>
  	マイページ機能ではログインすることで自分で作った見積りを保存でき、今まで作った見積りを閲覧することができます。
	また、作った見積りの削除も可能になっています。
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