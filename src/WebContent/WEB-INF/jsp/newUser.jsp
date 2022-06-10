<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>新規登録 | 推リス</title>
    <link rel ="stylesheet" href="${pageContext.request.contextPath}/css/common.css" >
	<link rel ="stylesheet" href="${pageContext.request.contextPath}/css/newUser.css" >
    <link rel="icon" href="${pageContext.request.contextPath}/imgs/推リスicon.png">
</head>

<body>
	<h1>オシリス</h1>
	<h2>新規登録</h2>
	<p><a href="/osilis/LoginSerblet">戻る</a></p>

	<div class="regist">
		<form method="POST" action="/osilis/NewUserServlet" id="form">
		<table>
			<tr>
				<td>
					<label>
						ユーザ名<br>
						<input type="text" name="User">
					</label>
				</td>
			</tr>

			<tr>
				<td>
					<label>
						ID<br>
						<input type="text" name="New_ID">
					</label>
				</td>
			</tr>

			<tr>
				<td>
					<label>
						PW<br>
						<input type="password" name="New_Password" id="pass">
					</label>
				</td>
			</tr>

			<tr>
				<td>
					<label>
					<input type="checkbox" id="password-check">
					パスワードを表示
					</label>
				</td>
			</tr>

			<tr>
				<td>
					<input type="submit" name="New_Regist" value="登録">
					<input type="reset" name="reset" value="リセット">
				</td>
			</tr>
		</table>

	</form>
	</div>

	<div id="footer">
	      <p>&copy;Copyright C1 GE★RA osilis. All rights reserved.</p>
	</div>

<script src="${pageContext.request.contextPath}/js/newUser.js">
</script>

</body>
</html>