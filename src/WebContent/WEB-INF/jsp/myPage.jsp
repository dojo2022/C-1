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

<header>
	<img alt="推しリス" src="/osilis/imgs/osirisu.png" class="osirisukun">
	<a href="/osilis/LogoutServlet" onclick="return confirm('ログアウトしますか？')">ログアウト</a>
</header>

<!--  全体を囲むdivクラスwrapper  -->
<div class="wrapper">

<h1><span>マ</span>イページ</h1>

<!-- ユーザマスタから称号を照合 -->
<p>現在の称号「${reward_result.reward}」</p>





<div id="container"></div>

<p>${user.user_name}さん、現在のポイントは${user.point}ポイントです！</p>

<button type="button" onclick="location.href='/osilis/OsiServlet'" class="myPage_button">推し設定</button>
<button type="button" onclick="location.href='/osilis/UserEditServlet'" class="myPage_button">ユーザー設定</button>

<br><br>

			<img src="imgs/${img.favorite_good_img}" width = "50%" height = "50%" alt="推しの写真" title="私の推し">

			<img src="imgs/${img.favorite_bad_img}" width = "50%" height = "50%" alt="推しの写真" title="私の推し">

			<img src="imgs/${img.favorite_other_img}" width = "50%" height = "50%" alt="推しの写真" title="私の推し">



</div>

    <!-- フロートメニュー -->
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
	<script src="${pageContext.request.contextPath}/js/myPage.js"></script>
</body>
</html>