<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>リストTop | 推リス</title>
<link rel="icon" href="${pageContext.request.contextPath}/imgs/推リスicon.png">
<link rel="stylesheet" type="text/css" href="/osilis/css/common.css">
<link rel="stylesheet" type="text/css" href="/osilis/css/list.css">
</head>

<body>
<!--  全体を囲むdivクラスwrapper  -->
<div class="wrapper">



<h1>今日のリスト</h1>

	<p class="job">仕事</p>
	<table>
		<tr>
	     <%--  <c:forEach var="e" items="#">
	        <tr>
		      <td>${e.event}<input type="hidden" name="number" value="${e.event}"></td>
		      <td>
		      	<form method="POST" action="/osilis/ListServlet">
		      		<input type="hidden" name="number" value="${e.event}">
		      		<input type="checkbox" value="達成">
		      	</form>
		      <td>
	        </tr>

	        <tr class="data_row">
		      <td>${e.event}<input type="hidden" name="number" value="${e.event}"></td>
		      <td>
		      	<form method="POST" action="/osilis/ListServlet">
		      		<input type="hidden" name="number" value="${e.event}">
		      		<input type="checkbox" name="List1" value="達成">
		      	</form>
		      <td>
	        </tr>
		  </c:forEach> --%>
		</tr>
	</table>

	<p class="house">家事</p>
	<table>
		<tr>
	   <%--    <c:forEach var="e" items="#">
	        <tr class="data_row">
		      <td>${e.event}<input type="hidden" name="number" value="${e.event}"></td>
		      <td>
		      	<form method="POST" action="/osilis/ListServlet">
		      		<input type="hidden" name="number" value="${e.event}">
		      		<input type="checkbox" value="達成">
		      	</form>
		      <td>
	        </tr>

	         <tr class="data_row">
		      <td>${e.event}<input type="hidden" name="number" value="${e.event}"></td>
		      <td>
		      	<form method="POST" action="/osilis/ListServlet">
		      		<input type="hidden" name="number" value="${e.event}">
		      		<input type="checkbox" name="List1" value="達成">
		      	</form>
		      <td>
	        </tr>
		  </c:forEach> --%>
		</tr>
	</table>

		<p class="play">インドア・アウトドア</p>
	<table>
		<tr>
	    <%--   <c:forEach var="e" items="#">
	        <tr class="data_row">
		      <td>${e.event}<input type="hidden" name="number" value="${e.event}"></td>
		      <td>
		      	<form method="POST" action="/osilis/ListServlet">
		      		<input type="hidden" name="number" value="${e.event}">
		      		<input type="checkbox" value="達成">
		      	</form>
		      <td>
	        </tr>
		  </c:forEach> --%>
		</tr>
	</table>

		<form method="POST" action="/osilis/ListServlet">
			<!-- フォームの送信先 -->
			<input type="submit" name="Result" value="達成">
		</form>



		<div class="menu">
		 <ul id="nav">
		 	<li><a href="/simpleBC/MenuServlet">Top</a></li>
			<li><a href="/simpleBC/ListServlet">予定の追加</a></li>
		    <li><a href="/simpleBC/RegistServlet">MyPage</a></li>
		    <li><a href="/simpleBC/SearchServlet">履歴</a></li>
		 </ul>
	</div>


	<div id="footer">
	      <p>&copy;Copyright C1 GE★RA osilis. All rights reserved.</p>
	</div>


</div>

<script src="${pageContext.request.contextPath}/js/list.js"></script>

</body>

</html>