<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>見積り</title>
  <link href="css/addbuild.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/header.jsp"/>
  <div class="page">
    <h1>自作PCを作成</h1>
    <br>
    <h3>パーツリスト</h3>

    <table width="100%" cellpadding="20">
		<div id="cpu">
		<tr>
			<td width="20%">CPU</td>
			<td width="20%">
				<form method="get" action="addbuild">
					<input type="hidden" name="parts" value="cpu">
					<input type="submit" value="追加">
				</form>
			</td>
			<form method="get" action="sortproduct">
				<input type="hidden" name="word" value="a">
				<td width="60%">おすすめ:123
					<input type="submit" name="words" value="Ryzen 5">
					<input type="submit" name="words" value="Core i9">
					<input type="submit" name="words" value="Core i7">
				</td>
			</form>
		</tr>
		</div>

		<div id="cpu_cooler">
		<tr>
			<td width="20%">CPUクーラー</td>
			<td width="20%">
				<form method="get" action="addbuild">
					<input type="hidden" name="parts" value="cpu_cooler">
					<input type="submit" value="追加">
				</form>
			</td>
			<form method="get" action="sortproduct">
				<input type="hidden" name="word" value="a">
				<td width="60%">おすすめ:1
					<input type="submit" name="words" value="Noctua">
					<input type="submit" name="words" value="!Socket AM4">
					<input type="submit" name="words" value="!LGA1200">
				</td>
			</form>
		</tr>
		</div>

		<div id="mother_board">
		<tr>
			<td width="20%">マザーボード</td>
			<td width="20%">
				<form method="get" action="addbuild">
					<input type="hidden" name="parts" value="mother_board">
					<input type="submit" value="追加">
				</form>
			</td>
			<form method="get" action="sortproduct">
				<input type="hidden" name="word" value="formfactor">
				<td width="60%">おすすめ:123
					<input type="submit" name="words" value="Mini ITX">
					<input type="submit" name="words" value="B550">
					<input type="submit" name="words" value="Z490">
				</td>
			</form>
		</tr>
		</div>

		<div id="ram">
		<tr>
			<td width="20%">メモリ</td>
			<td width="20%">
				<form method="get" action="addbuild">
					<input type="hidden" name="parts" value="ram">
					<input type="submit" value="追加">
				</form>
			</td>
			<form method="get" action="sortproduct">
				<input type="hidden" name="word" value="a">
				<td width="60%">おすすめ:boostClockとか
					<input type="submit" name="words" value="DDR4-2666">
					<input type="submit" name="words" value="!16GB×2">
					<input type="submit" name="words" value="!8GB×2">
				</td>
			</form>
		</tr>
		</div>

		<div id="gpu">
		<tr>
			<td width="20%">ビデオカード</td>
			<td width="20%">
				<form method="get" action="addbuild">
					<input type="hidden" name="parts" value="gpu">
					<input type="submit" value="追加">
				</form>
			</td>
			<form method="get" action="sortproduct">
				<input type="hidden" name="word" value="a">
				<td width="60%">おすすめ:123
					<input type="submit" name="words" value="RTX 3070">
					<input type="submit" name="words" value="RTX 2070 SUPER">
					<input type="submit" name="words" value="RX 5700 XT">
				</td>
			</form>
		</tr>
		</div>

		<div id="storage">
		<tr>
			<td width="20%">ストレージ</td>
			<td width="20%">
				<form method="get" action="addbuild">
					<input type="hidden" name="parts" value="storage">
					<input type="submit" value="追加">
				</form>
			</td>
			<form method="get" action="sortproduct">
				<input type="hidden" name="word" value="a">
				<td width="60%">おすすめ:1
					<input type="submit" name="words" value="M.2 (Type2280)">
					<input type="submit" name="words" value="2.5インチ">
					<form method="get" action="sortproduct">
						<input type="hidden" name="word" value="volume">
						<input type="submit" name="words" value="480GB~512GB未満">
					</form>
				</td>
			</form>
		</tr>
		</div>

		<div id="case">
		<tr>
			<td width="20%">ケース</td>
			<td width="20%">
				<form method="get" action="addbuild">
					<input type="hidden" name="parts" value="case">
					<input type="submit" value="追加">
				</form>
			</td>
			<form method="get" action="sortproduct">
				<input type="hidden" name="word" value="a">
				<td width="60%">おすすめ:123
					<input type="submit" name="words" value="MicroATX">
					<input type="submit" name="words" value="ATX">
					<input type="submit" name="words" value="Mini-ITX">
				</td>
			</form>
		</tr>
		</div>

		<div id="power_supply">
		<tr>
			<td width="20%">電源</td>
			<td width="20%">
				<form method="get" action="addbuild">
					<input type="hidden" name="parts" value="power_supply">
					<input type="submit" value="追加">
				</form>
			</td>
			<form method="get" action="sortproduct">
				<input type="hidden" name="word" value="a">
				<td width="60%">おすすめ:1
					<input type="submit" name="words" value="SFX">
				<input type="hidden" name="word" value="W">
					<input type="submit" name="words" value="300W~600W未満">
				<input type="hidden" name="word" value="W">
					<input type="submit" name="words" value="600W~800W未満">
				</td>
			</form>
		</tr>
		</div>

		<div id="case_fan">
		<tr>
			<td width="20%">ケースファン</td>
			<td width="20%">
				<form method="get" action="addbuild">
					<input type="hidden" name="parts" value="case_fan">
					<input type="submit" value="追加">
				</form>
			</td>
			<form method="get" action="sortproduct">
				<input type="hidden" name="word" value="a">
				<td width="60%">おすすめ:34
					<input type="submit" name="words" value="140mm角">
					<input type="submit" name="words" value="120mm角">
					<input type="submit" name="words" value="クーラーマスター">
					<input type="submit" name="words" value="Corsair">
				</td>
			</form>
		</tr>
		</div>
	</table>
	</div>

  <%--  <br><tr><td>総計</td></tr><tr><td>円</td></tr>
  <div>
   <form method="post" action="">
    <br><input type="submit" value="保存">
   </form>
  </div>--%>
  <jsp:include page="/footer.jsp"/>
</body>
</html>