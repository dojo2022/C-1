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
	<div class="wrapper">
		<!-- xxxx年xx月を表示 -->
    	<h1 id="header"></h1>

    	<!-- ボタンクリックで月移動 -->
    	<div id="next-prev-button">
        	<button id="prev" onclick="prev()">‹</button>
        	<button id="next" onclick="next()">›</button>
    	</div>

	    <!-- カレンダー -->
	    <div id="calendar"></div>



		<div id="footer">
			<p>&copy;Copyright C1 GE★RA osilis. All rights reserved.</p>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/pastList.js">

	</script>
</body>

</html>