<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<script type="text/javascript">

function check(){
    var flag = 0;

    if(document.form1.name.value == ""){
        flag = 1;
    }
    else if(document.form1.comment.value == ""){
        flag = 1;
    }

    if(flag){
        window.alert('タイトルとコメントを入力してください');
        return false; // 送信を中止
    }
    else{
        return true; // 送信を実行
    }
}
</script>

<title>掲示板</title>
</head>
<body>
<form action="postbuild" method="post" name="form1" onSubmit="return check()">
<p>タイトル:<input type="text" name="title"></p>
<p>コメント:</ br>
<textarea name="description" rows="5" cols="40"></textarea>
</p>
<p><input type="submit" value="送信"><input type="reset" value="リセット">
</p>
</form>

<c:forEach var="list" items="${data}">
<p>タイトル:<c:out value="${list.title}"/><br>
説明:<c:out value="${list.description}"/></p>
</c:forEach>

</body>
</html>