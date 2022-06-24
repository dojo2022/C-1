<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>


<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>リスト作成 ｜ 推リス</title>
<link rel ="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel ="stylesheet" href="${pageContext.request.contextPath}/css/createList.css" >
<link rel ="icon" href="${pageContext.request.contextPath}/imgs/推リスicon.png">
</head>


<body>

<header>
	<img alt="推しリス" src="/osilis/imgs/osirisu.png" class="osirisukun">
	<a href="/osilis/LogoutServlet" onclick="return confirm('ログアウトしますか？')">ログアウト</a>
</header>

	<div class="wrapper">

		<h1 id="header">目標立ててがんばろ！</h1>
		<img src="imgs/${img.favorite_other_img}" width = "250px" height = "250px" alt="推しの写真" title="私の推し">
		<audio autoplay src = "imgs/${voice.favorite_other_voice}"></audio>




	<div class="select-week" id ="select-week">

	</div>


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

	<script src="${pageContext.request.contextPath}/js/createList.js" >
	</script>
</body>






</html>