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
	<h1>オシリス</h1>
	<h2>ユーザ設定</h2>

	<!-- アイコン設定作るよ -->
	<!-- すでに設定されてる画像は表示できてないよ -->
	<form action="/osilis/UserEditServlet" method="post" enctype="multipart/form-data">
		<input type="file" name="icon" accept="image/*" onchange="previewImage(this);"><br>
		<canvas id="preview" style="max-width:200px;"></canvas><br>
		<input type="submit" name="Icon_Regist" value="OK">
	</form>

	<form method="post" action="/osilis/UserEditServlet">

		<label>
			ユーザ名<br>
			<input type="text" name="UserName">
		</label>

	</form><br>
	<input type="submit" name="User_Regist" value="OK">

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
				<td>
					<label>
						新しいパスワード<br>
						<input type="password" name="Change_Password">
					</label>
				</td>
			</tr>

			<tr>
				<td>

				</td>
				<td>
					<label>
						もう一度入力してください<br>
						<input type="password" name="Check_Password">
					</label>
				</td>
			</tr>

			<tr>
				<td>

				</td>
				<td>
					<input type="submit" name="Password_Regist" value="OK">
				</td>
			</tr>

		</table>
	</form>

	<!-- 作りかけです！ -->
	<div class="menu">
		 <ul id="nav">
		 	<li><a href="/osilis/TopServlet">Top</a></li>
			<li><a href="/osilis/EventEditServlet">予定の追加</a></li>
		    <li><a href="/osilis/MypageServlet">MyPage</a></li>
		    <li><a href="/osilis/PastListServlet">履歴</a></li>
		 </ul>
	</div>

	<div id="footer">
	      <p>&copy;Copyright C1 GE★RA osilis. All rights reserved.</p>
	</div>

<script src="${pageContext.request.contextPath}/js/userEdit.js">
</script>

</body>

</html>