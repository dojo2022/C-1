<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>イベント編集画面 | 推リス</title>
<link rel="icon" href="${pageContext.request.contextPath}/imgs/推リスicon.png">
<link rel="stylesheet" type="text/css" href="/osilis/css/eventEdit.css">
</head>
<body>

<header>
  <div class="header_back">
	<img alt="推しリス" src="/osilis/imgs/osirisu.png" class="osirisukun">
	<a href="/osilis/LogoutServlet" onclick="return confirm('ログアウトしますか？')">ログアウト</a>
  </div>
</header>

<!--  全体を囲むdivクラスwrapper  -->
	<div class="wrapper">

		<h1>予定の管理</h1>

		<h2>予定の登録</h2>
		<p><c:out value="${result.message}" /></p>

		<form method="POST" action="/osilis/EventEditServlet">
			<table>
				<tr>
					<td><label><strong>イベント</strong><br> <input
							type="text" name="Event" placeholder="必須項目" class="eventText"
							style="width: 20em; height: 2em;"> </label></td>
				</tr>
				<tr class="down">
					<td><label><strong>タイプ</strong><br> <select
							name="Type">
								<option value="1">家事</option>
								<option value="2">仕事</option>
								<option value="3">インドア</option>
								<option value="4">アウトドア</option>
						</select> </label></td>
					<td><label><strong>難易度</strong><br> <select
							name="Level">
								<option value="1">簡単</option>
								<option value="2">普通</option>
								<option value="3">難しい</option>
						</select> </label></td>
					<td><input type="submit" name="Event_Regist" value="登録">
					</td>
				</tr>
			</table>
		</form>

		<h2>予定の編集</h2>

		<form method="POST" action="/osilis/EventEditServlet">

			<div id="table">
				<table id="t">
					<c:forEach var="e" items="${eventsList}" varStatus="s">
					<input type="hidden" name="number" value="${e.number}">
					<c:if test="${e.available!=2}">
						<tr>
							<td><label><strong>イベント</strong><br> <input
									type="text" name="Event_Edit" value="${e.event}"
									style="width: 20em; height: 2em; padding: 5px;"> </label></td>
						</tr>
						<tr class="block">
							<td><label><strong>タイプ</strong><br> <select
									name="Type_Edit">
										<c:if test="${e.type==1}">
											<option value="1" selected>家事</option>
											<option value="2">仕事</option>
											<option value="3">インドア</option>
											<option value="4">アウトドア</option>
										</c:if>
										<c:if test="${e.type==2}">
											<option value="1">家事</option>
											<option value="2" selected>仕事</option>
											<option value="3">インドア</option>
											<option value="4">アウトドア</option>
										</c:if>
										<c:if test="${e.type==3}">
											<option value="1">家事</option>
											<option value="2">仕事</option>
											<option value="3" selected>インドア</option>
											<option value="4">アウトドア</option>
										</c:if>
										<c:if test="${e.type==4}">
											<option value="1">家事</option>
											<option value="2">仕事</option>
											<option value="3">インドア</option>
											<option value="4" selected>アウトドア</option>
										</c:if>
								</select> </label></td>
							<td><label><strong>難易度</strong><br> <select
									name="Level_Edit">
										<c:if test="${e.level==1}">
											<option value="1" selected>簡単</option>
											<option value="2">普通</option>
											<option value="3">難しい</option>

										</c:if>
										<c:if test="${e.level==2}">
											<option value="1">簡単</option>
											<option value="2" selected>普通</option>
											<option value="3">難しい</option>
										</c:if>
										<c:if test="${e.level==3}">
											<option value="1">簡単</option>
											<option value="2">普通</option>
											<option value="3" selected>難しい</option>
										</c:if>
								</select> </label></td>
								<td><input type="hidden" name="Switch" id="Switch_${s.index}" value="${e.available}"></td>
							<c:if test="${e.available==0}">
								<td><strong>有効</strong><br>
									<input type="radio" name="Switch_${s.index}" value="0" onchange="changeSwitch(this)" checked></td>
								<td><strong>無効</strong><br>
									<input type="radio" name="Switch_${s.index}" value="1" onchange="changeSwitch(this)"></td>
								<td><strong>非表示</strong><br>
									<input type="radio" name="Switch_${s.index}" value="2" onchange="changeSwitch(this)">
								</td>

							</c:if>
							<c:if test="${e.available==1}">
								<td><strong>有効</strong><br> <input type="radio"
									name="Switch_${s.index}" value="0" onchange="changeSwitch(this)"></td>
								<td><strong>無効</strong><br> <input type="radio"
									name="Switch_${s.index}" value="1" onchange="changeSwitch(this)" checked></td>
								<td><strong>非表示</strong><br> <input type="radio"
									name="Switch_${s.index}" value="2" onchange="changeSwitch(this)"></td>
							</c:if>
						</tr>
					  </c:if>
					</c:forEach>
				</table>
			</div>

			<input type="submit" name="Event_Update" value="更新">
		</form>
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



	<script src="${pageContext.request.contextPath}/js/eventEdit.js"></script>
</body>
</html>