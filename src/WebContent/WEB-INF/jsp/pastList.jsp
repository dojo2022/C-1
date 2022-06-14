<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>過去のリスト | 推リス</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/pastList.css">
<link rel="icon"
	href="${pageContext.request.contextPath}/imgs/推リスicon.png">
</head>

<body>
	<h1 id="header"></h1>
	<button id="prev" type="button"><</button>
	<button id="next" type="button">></button>
	<div id="calendar"></div>

	<div id="easyModal" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<h1 id="MH-content"></h1>
				<span class="modalClose">×</span>
			</div>
			<div class="modal-body">
				<tr>
					<th>イベント名</th>
					<th>有効/無効</th>
				</tr>
			</div>
		</div>
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
	<div id="footer">
		<p>&copy;Copyright C1 GE★RA osilis. All rights reserved.</p>
	</div>

	<script src="${pageContext.request.contextPath}/js/pastList.js">

	</script>
</body>

</html>