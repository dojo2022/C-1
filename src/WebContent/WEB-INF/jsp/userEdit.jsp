<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>ユーザ設定 | 推リス</title>
    <link rel ="stylesheet" href="${pageContext.request.contextPath}/css/common.css" >
	<link rel ="stylesheet" href="${pageContext.request.contextPath}/css/userEdit.css" >
    <link rel="icon" href="${pageContext.request.contextPath}/imgs/推リスicon.png">
</head>


<body>
<div class = "wrapper">
	<h1>オシリス</h1>
	<h2>ユーザ設定</h2>

	<p><c:out value="${result.message}" /></p>

	<!-- アイコン設定作るよ -->
	<!-- すでに設定されてる画像は表示できてないよ -->
	アイコン<br>
	<form action="/osilis/UserEditServlet" method="post" enctype="multipart/form-data">
		<input type="file" name="icon" accept="image/*" onchange="previewImage(this);"><br>
		<canvas id="preview" style="max-width:200px;"></canvas><br>


		<label>
			ユーザ名<br>
			<input type="text" name="UserName">
		</label>

	<br>
	<input type="submit" name="User_Regist" value="OK">
	</form>

	<h2>Password</h2>
	<form method="post" action="/osilis/UserEditServlet">
		<table>
			<tr>
				<td>
					<label>
						現在のパスワード<br>
						<input type="password" name="Password">
					</label>
				</td>
			</tr>

			<tr>
				<td>
					<label>
						新しいパスワード<br>
						<input type="password" name="Change_Password">
					</label>
				</td>
			</tr>

			<tr>
				<td>
					<label>
						もう一度入力してください<br>
						<input type="password" name="Check_Password">
					</label>
				</td>
			</tr>

			<tr>
				<td>
					<input type="submit" name="Password_Regist" value="OK">
				</td>
			</tr>

		</table>
	</form>
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

<script src="${pageContext.request.contextPath}/js/userEdit.js">
</script>

</div>
</body>

</html>