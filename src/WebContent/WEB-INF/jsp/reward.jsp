<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>達成画面 | 推リス</title>
<link rel="icon"
	href="${pageContext.request.contextPath}/imgs/推リスicon.png">
<link rel="stylesheet" type="text/css" href="/osilis/css/common.css">
<link rel="stylesheet" type="text/css" href="/osilis/css/reward.css">
</head>
<body>

	<header>
		<img alt="推しリス" src="/osilis/imgs/osirisu.png" class="osirisukun">
		<a href="/osilis/LogoutServlet">ログアウト</a>
	</header>

	<!--  全体を囲むdivクラスwrapper  -->
	<div class="wrapper">

		<h1>達成報酬</h1>
		<!-- 達成時の推しの写真 -->
		<img src="/osilis/imgs/推リス.png" width="250px" height="250px"
			class="reward_img">
		<!-- 推しの写真との繋げ方 -->
		<!-- 達成時の推しのコメント -->
		<p>${user.user_name}さん、お疲れさまでした！！</p>

		<!-- 獲得ポイントの表示（文字） -->
		<p>合計ポイントは${user.point}ポイントです！</p><br>
		<div id="container"></div>
		<!-- 獲得ポイントの表示（メーター） -->

		<!-- 獲得ポイントに対してのコメント -->
		<p>すごいですね！</p>

		<!-- その日のリストの表示 -->
		<table class = cleared_list>
			<tr>
				<th>
				今日やったこと
				</th>
			</tr>
			<c:forEach var="e" items="${clearList}">
				<!-- Cタグのif文 -->
				<tr>
					<td>
						${e.event}
					</td>
				</tr>
			</c:forEach>
		</table>

		<!-- 達成したイベント数の表示（文字） -->
		<p>今日は${user.clearCount}個無茶ぶりを達成しました！</p>

	</div>

	<!-- フロートメニュー -->
	<div class="menu">
		<ul id="nav">
			<li><a href="/osilis/TopServlet">Top</a></li>
			<li><a href="/osilis/EventEditServlet">予定の追加</a></li>
			<li><a href="/osilis/MyPageServlet">MyPage</a></li>
			<li><a href="/osilis/PastListServlet">履歴</a></li>
		</ul>
	</div>

	<!-- フッター -->
	<div id="footer">
		<p>&copy;Copyright C1 GE★RA osilis. All rights reserved.</p>
	</div>



	<script src="https://rawgit.com/kimmobrunfeldt/progressbar.js/master/dist/progressbar.js"></script>
	<script src="https://rawgit.com/kimmobrunfeldt/progressbar.js/master/dist/progressbar.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/reward.js"></script>

</body>
</html>