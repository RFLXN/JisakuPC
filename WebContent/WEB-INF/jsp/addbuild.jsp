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

    <table width="100%" cellpadding="30">
      <form method="get" action="addbuild">
        <input type="hidden" name="parts" value="cpu">
        <div id="cpu">
          <tr>
            <td width="20%">CPU</td>
            <td width="20%"><input type="submit" value="追加"></td>
            <td width="60%">おすすめ:Ryzen5 Core i9 Corei7</td>
          </tr>
        </div>
      </form>
      <form method="get" action="addbuild">
        <input type="hidden" name="parts" value="gpu">
        <div id="gpu">
          <tr>
            <td width="20%">GPU</td>
            <td width="20%"><input type="submit" value="追加"></td>
            <td width="60%">おすすめ:RTX3070 RTX2070 Super RX 5700 XT</td>
          </tr>
        </div>
      </form>
      <form method="get" action="addbuild">
        <input type="hidden" name="parts" value="ram">
        <div id="ram">
          <tr>
            <td width="20%">メモリ</td>
            <td width="20%"><input type="submit" value="追加"></td>
            <td width="60%">おすすめ:DDR4-2666 16GB×2 8GB×2</td>
          </tr>
        </div>
      </form>
      <form method="get" action="addbuild">
        <input type="hidden" name="parts" value="cpu_cooler">
        <div id="cpu_cooler">
          <tr>
            <td width="20%">クーラー</td>
            <td width="20%"><input type="submit" value="追加"></td>
            <td width="60%">おすすめ:Noctua Socket AM4 LGA1200</td>
          </tr>
        </div>
      </form>
      <form method="get" action="addbuild">
        <input type="hidden" name="parts" value="case">
        <div id="case">
          <tr>
            <td width="20%">ケース</td>
            <td width="20%"><input type="submit" value="追加"></td>
            <td width="60%">おすすめ:MicroATX ATX Mini-ATX</td>
          </tr>
        </div>
      </form>
      <form method="get" action="addbuild">
        <input type="hidden" name="parts" value="mother_board">
        <div id="mother_board">
          <tr>
            <td width="20%">マザーボード</td>
            <td width="20%"><input type="submit" value="追加"></td>
            <td width="60%">おすすめ:Mini ITX B550 Z490</td>
          </tr>
        </div>
      </form>
      <form method="get" action="addbuild">
        <input type="hidden" name="parts" value="storage">
        <div id="storage">
          <tr>
            <td width="20%">ストレージ</td>
            <td width="20%"><input type="submit" value="追加"></td>
            <td width="60%">おすすめ:M.2(Type2280) 480GB~512GB未満 2.5インチ</td>
          </tr>
        </div>
      </form>
      <form method="get" action="addbuild">
        <input type="hidden" name="parts" value="power_supply">
        <div id="power_supply">
          <tr>
            <td width="20%">電源</td>
            <td width="20%"><input type="submit" value="追加"></td>
            <td width="60%">おすすめ:SFX 300W~600W未満 600W~800W未満</td>
          </tr>
        </div>
      </form>
      <form method="get" action="addbuild">
        <input type="hidden" name="parts" value="case_fan">
        <div id="case_fan">
          <tr>
            <td width="20%">ケースファン</td>
            <td width="20%"><input type="submit" value="追加"></td>
            <td width="60%">おすすめ:140mm角 120mm角 クーラーマスター Corsair</td>
          </tr>
        </div>
      </form>
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