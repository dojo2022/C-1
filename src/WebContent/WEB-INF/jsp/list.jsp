<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>リストTop | 推リス</title>
<link rel="icon" href="${pageContext.request.contextPath}/imgs/推リスicon.png">
<link rel="stylesheet" type="text/css" href="/osilis/CSS/common.css">
<link rel="stylesheet" type="text/css" href="/osilis/CSS/list.css">
</head>

<body>
<!--  全体を囲むdivクラスwrapper  -->
<div class="wrapper">



<h1>今日のリスト</h1>

<table></table>



	<!-- 作りかけです！
	<div class="menu">
		 <ul id="nav">
		 	<li><a href="/simpleBC/MenuServlet">Top</a></li>
			<li><a href="/simpleBC/ListServlet">予定の追加</a></li>
		    <li><a href="/simpleBC/RegistServlet">MyPage</a></li>
		    <li><a href="/simpleBC/SearchServlet">履歴</a></li>
		 </ul>
	</div> -->


	<div id="footer">
	      <p>&copy;Copyright C1 GE★RA osilis. All rights reserved.</p>
	</div>


</div>

<script src="${pageContext.request.contextPath}/js/list.js"></script>

</body>

</html>