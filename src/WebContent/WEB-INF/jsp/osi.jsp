<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>推し登録 | 推リス</title>
    <link rel ="stylesheet" href="${pageContext.request.contextPath}/css/common.css" >
	<link rel ="stylesheet" href="${pageContext.request.contextPath}/css/osi.css" >
    <link rel="icon" href="${pageContext.request.contextPath}/imgs/推リスicon.png">
</head>

<body>

<header>
	<img alt="推しリス" src="/osilis/imgs/osirisu.png" class="osirisukun">
	<a href="/osilis/LogoutServlet">ログアウト</a>
</header>

<div class="wrapper">
<h1>オシリス</h1>

<h2>推し設定</h2>
<fieldset style="border:1px solid #000000; padding: 15px;" class="ositable">
    <legend class="osititle">推し写真</legend>
	<form action="/osilis/OsiServlet" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>
					褒め写真
				</td>
				<td>
					<input type="file" name="Good_Image" accept="image/*">
				</td>
			</tr>

			<tr>
				<td>
					叱り写真
				</td>
				<td>
					<input type="file" name="Bad_Image" accept="image/*">
				</td>
			</tr>

			<tr>
				<td>
					その他写真
				</td>
				<td>
					<input type="file" name="Other_Image" accept="image/*">
				</td>
			</tr>

			<tr>
				<td>

				</td>
				<td>
					<input type="submit" name="Image_Regist" value="OK">
				</td>
			</tr>
		</table>
	</form>
</fieldset>

  <fieldset style="border:1px solid #000000; padding: 15px;" class="ositable">
	<legend class="osititle">推しボイス</legend>
	<form action="/osilis/OsiServlet" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>
					褒めボイス
				</td>
				<td>
					<input type="file" name="Good_Voice" accept="audio/*">
				</td>
			</tr>

			<tr>
				<td>
					叱りボイス
				</td>
				<td>
					<input type="file" name="Bad_Voice" accept="audio/*">
				</td>
			</tr>

			<tr>
				<td>
					BGM
				</td>
				<td>
					<input type="file" name="Other_Voice" accept="audio/*">
				</td>
			</tr>

			<tr>
				<td>

				</td>
				<td>
					<input type="submit" name="Voice_Regist" value="OK">
				</td>
			</tr>
		</table>
	</form>
  </fieldset>

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

<script src="${pageContext.request.contextPath}/js/osi.js">
</script>

</body>
</html>