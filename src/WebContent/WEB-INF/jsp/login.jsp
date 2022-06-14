<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>ログイン | 推リス</title>
    <link rel ="stylesheet" href="${pageContext.request.contextPath}/css/common.css" >
	<link rel ="stylesheet" href="${pageContext.request.contextPath}/css/login.css" >
    <link rel="icon" href="${pageContext.request.contextPath}/imgs/推リスicon.png">
</head>

<body>
<div class="wrapper">
	<h1>オシリス</h1>
	<h2>ログイン</h2>

	<div class="logform">
		<form method="POST" action="/osilis/LoginServlet" id="form">
		<table>
			<tr>
				<td>
					<label>
						ID<br>
						<input type="text" name="ID">
					</label>
				</td>
			</tr>

			<tr>
				<td>
					<label>
						PW<br>
						<input type="password" name="Password" id="pass">
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
					<input type="submit" name="login" value="ログイン">
					<input type="reset" name="reset" value="リセット">
				</td>
			</tr>
		</table>

	</form>
	</div>

	<button type = "button" onclick="location.href='/osilis/NewUserServlet'">新規登録</button>

	<div id="footer">
	      <p>&copy;Copyright C1 GE★RA osilis. All rights reserved.</p>
	</div>

<script src="${pageContext.request.contextPath}/js/login.js">
</script>
</div>
</body>

</html>