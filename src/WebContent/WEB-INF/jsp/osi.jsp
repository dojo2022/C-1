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
	<a href="/osilis/LogoutServlet" onclick="return confirm('ログアウトしますか？')">ログアウト</a>
</header>

<div class="wrapper">


<h1><span>推</span>し設定</h1>
<p><c:out value="${result.message}" /></p>

<fieldset style="border:1px solid #000000; padding: 15px;" class="ositable">
    <legend class="osititle">推し写真</legend>
	<form action="/osilis/OsiServlet" method="post" enctype="multipart/form-data">
		<table class = table_osi_pic>
			<tr>
				<td class = "good">
					<strong>褒め写真</strong>
				<!-- </td>
				<td> -->
					<label>
						<input type="hidden" name = "good_file" id = "good_file" value="imgs/${img.favorite_good_img}">
						<input type="file" name="Good_Image" accept="image/*" onchange="previewImage(this);"  value="${img.favorite_good_img}" id = "goodFile" style = >
						<canvas id="preview"></canvas>
					</label>
				</td>
			</tr>

			<tr>
				<td>
					<strong>叱り写真</strong>
				<!-- </td>
				<td> -->
					<label>
						<input type="hidden" name = "bad_file" id = "bad_file" value="imgs/${img.favorite_bad_img}">
						<input type="file" name="Bad_Image" accept="image/*" onchange="previewImage_2(this);"  value="${img.favorite_good_img}" id = "badFile">
						<canvas id="preview_2"></canvas>
					</label>
				</td>
			</tr>

			<tr>
				<td>
					<strong>その他写真</strong>
				<!-- </td>
				<td> -->
					<label>
						<input type="hidden" name = "other_file" id = "other_file" value="imgs/${img.favorite_other_img}">
						<input type="file" name="Other_Image" accept="image/*" onchange="previewImage_3(this);"  value="${img.favorite_other_img}" id = "otherFile">
						<canvas id="preview_3"></canvas>
					</label>
				</td>
			</tr>
				</table>


               <div id = osi_pic>
					<input type="submit" name="Image_Regist" value="OK">
			   </div>

	</form>
</fieldset>

  <fieldset style="border:1px solid #000000; padding: 15px;" class="ositable">
	<legend class="osititle">推しボイス</legend>
	<form action="/osilis/OsiServlet" method="post" enctype="multipart/form-data">
		<table class="table_osi_voice">
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
			</table>

			<div id = osi_voice>
					<input type="submit" name="Voice_Regist" value="OK">
			</div>

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