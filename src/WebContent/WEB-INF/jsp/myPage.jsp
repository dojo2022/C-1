<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>マイページ | 推リス</title>
<link rel="icon" href="${pageContext.request.contextPath}/imgs/推リスicon.png">
<link rel="stylesheet" type="text/css" href="/osilis/css/common.css">
<link rel="stylesheet" type="text/css" href="/osilis/css/myPage.css">
</head>
<body>
<!--  全体を囲むdivクラスwrapper  -->
<div class="wrapper">

<h1>マイページ</h1>

<!-- ユーザマスタから称号を照合 -->

<!--ページの遷移-->
 <c:forEach var="e" items="${user}" >
 	<h2>  ${e.reward} </h2>
 </c:forEach>

<div id="container"></div>

<button type="button" onclick="location.href='/osilis/OsiServlet'">推し設定</button>
<button type="button" onclick="location.href='/osilis/UserEditServlet'">ユーザー設定</button>


<!-- 作りかけです！
	<div class="menu">
		 <ul id="nav">
		 	<li><a href="/simpleBC/MenuServlet">Top</a></li>
			<li><a href="/simpleBC/ListServlet">予定の追加</a></li>
		    <li><a href="/simpleBC/RegistServlet">MyPage</a></li>
		    <li><a href="/simpleBC/SearchServlet">履歴</a></li>
		 </ul>
	</div> -->


	<!-- フッター -->
	<div id="footer">
	      <p>&copy;Copyright C1 GE★RA osilis. All rights reserved.</p>
	</div>


</div>
	<script src="https://rawgit.com/kimmobrunfeldt/progressbar.js/master/dist/progressbar.js"></script>
	<script src="https://rawgit.com/kimmobrunfeldt/progressbar.js/master/dist/progressbar.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/myPage.js"></script>
</body>
</html>