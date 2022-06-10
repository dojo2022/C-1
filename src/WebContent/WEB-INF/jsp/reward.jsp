<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>達成画面 | 推リス</title>
<link rel="icon" href="${pageContext.request.contextPath}/imgs/推リスicon.png">
<link rel="stylesheet" type="text/css" href="/osilis/css/common.css">
<link rel="stylesheet" type="text/css" href="/osilis/css/reward.css">
</head>
<body>

<!--  全体を囲むdivクラスwrapper  -->
<div class="wrapper">

	<h1>達成報酬</h1>
	<!-- 達成時の推しの写真 -->
	<img  src="/osilis/imgs/推リス.png" width="250px" height="250px" class="reward_img">
	<!-- 達成時の推しのコメント -->
	<p>お疲れさまでした！！</p>

	<!-- 獲得ポイントの表示（文字） -->
	<p>今日の獲得ポイント100ポイントです！</p>
	<!-- 獲得ポイントの表示（メーター） -->

	<!-- 獲得ポイントに対してのコメント -->
	<p>すごいですね！</p>

	<!-- その日のリストの表示 -->

	<!-- 達成したイベント数の表示（文字） -->
	<p>今日は4個無茶ぶりを達成しました！</p>

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

	<script src="${pageContext.request.contextPath}/js/list.js"></script>

</body>
</html>