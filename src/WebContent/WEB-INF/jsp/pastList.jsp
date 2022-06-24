<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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

<header>
	<img alt="推しリス" src="/osilis/imgs/osirisu.png" class="osirisukun">
	<a href="/osilis/LogoutServlet" onclick="return confirm('ログアウトしますか？')">ログアウト</a>
</header>



	<input type="hidden" name="test_data2" id="test_data2" value="${param.test_data2}"><br>

<!--  全体を囲むdivクラスwrapper  -->
<div class="wrapper">

	<h2><span>過</span>去のリスト</h2>
	<p>
		<c:out value="${result.message}" />
	</p>





	<div class= "yeah">
		<button id="prev" class="triangle-left"  type="button">◀前月</button>
		<h1 id="header"></h1>
		<button id="next" class="triangle-right" type="button">来月▶</button>
	</div>

		<div id="calendar"></div>

		<div id="easyModal" class="modal">
			<div class="modal-content">
				<div class="modal-header">
					<h1 id="MH-content"></h1>
					<span class="modalClose">×</span>
				</div>
				<div class="modal-body">
					<div id="pastList"></div>

					<!--<c:if test="${}">
					<input type="submit" name="報告" value="完了">
					</c:if>-->

				</div>
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

	</div>
	<script src="${pageContext.request.contextPath}/js/pastList.js">

	</script>
</body>

</html>