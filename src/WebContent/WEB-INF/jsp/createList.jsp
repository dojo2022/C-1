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
	<a href="/osilis/LogoutServlet" onclick="confirmDel(event)">ログアウト</a>
</header>

	<div class="wrapper">

		<h1 id="header">目標立ててがんばろ！</h1>
		<image src="" alt="推しの写真" title="私の推し">




	<div class="select-week">
	 	<form method="post" action="/osilis/CreateListServlet">
			<input type="radio" id="weekday" value="平日" name="week" class="weekday"><label for="weekday">平日</label>
			<input type="radio" id="weekend" value="休日" name="week" class="weekend"><label for="weekend">休日</label><br>

			<input type="submit" id="weeklist" value="予定の作成">
	 	</form>
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