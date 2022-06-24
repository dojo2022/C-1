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
<body class = background>

	<header>
		<img alt="推しリス" src="/osilis/imgs/osirisu.png" class="osirisukun">
		<a href="/osilis/LogoutServlet" onclick="return confirm('ログアウトしますか？')">ログアウト</a>
	</header>

	<!--  全体を囲むdivクラスwrapper  -->
	<div class="wrapper">

		<h1>達成報酬</h1>
		<!-- 達成時の推しの写真 -->
		<!-- <img src="/osilis/imgs/推リス.png" width="250px" height="250px"
			class="reward_img"> -->
		<!-- 推しの写真との繋げ方 -->

		<c:if test="${user.clearCount>3}">
			<img src="imgs/${img.favorite_good_img}" width = "250px" height = "250px" alt="推しの写真" title="私の推し">
			<audio autoplay src = "imgs/${voice.favorite_good_voice}"></audio>
		</c:if>

		<c:if test="${user.clearCount<4}">
			<img src="imgs/${img.favorite_bad_img}" width = "250px" height = "250px" alt="推しの写真" title="私の推し">
			<audio autoplay src = "imgs/${voice.favorite_bad_voice}"></audio>
		</c:if>


		<!-- 達成時の推しのコメント -->
		<p id = "Otsukare">${user.user_name}さん、お疲れさまでした！！<br></p>

		<!-- 獲得ポイントの表示（文字） -->
		<p id = total_point>合計ポイントは<span class = "point">${user.point}</span>ポイントです！</p><br>

		<!-- 称号表示 -->
		<p>あなたの属性は「${reward_result.reward}」です！</p>

		<!-- 獲得ポイントの表示（メーター） -->
		<div id="container"></div>

		<!-- その日のリストの表示 -->
		<table class = cleared_list>

		<!-- 達成したイベント数の表示（文字） -->
		 <p id = cleared_comment>今日は<span class = "clear_count">${user.clearCount}</span>個無茶ぶりを達成しました！</p>

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

		<!-- 獲得ポイントに対してのコメント -->
		<p>すごいですね！</p>

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
		<footer>
		<div class="menu">
			 <ul id="nav">
			 	<li><a href="/osilis/TopServlet">Top</a></li>
				<li><a href="/osilis/EventEditServlet">予定の追加</a></li>
			    <li><a href="/osilis/MyPageServlet">MyPage</a></li>
			    <li><a href="/osilis/PastListServlet">履歴</a></li>
	         </ul>
	    </div>
	    <div id="footer">
	      <p>&copy;Copyright C1 GE★RA osilis. All rights reserved.</p>
	    </div>

	</footer>



	<script src="https://rawgit.com/kimmobrunfeldt/progressbar.js/master/dist/progressbar.js"></script>
	<script src="https://rawgit.com/kimmobrunfeldt/progressbar.js/master/dist/progressbar.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/reward.js"></script>

</body>
</html>