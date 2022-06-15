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
<link rel="stylesheet" type="text/css" href="/osilis/css/common.css">
<link rel="stylesheet" type="text/css" href="/osilis/css/eventEdit.css">
</head>
<body>
<!--  全体を囲むdivクラスwrapper  -->
<div class="wrapper">

<h1>予定の管理</h1>

<h2>予定の登録</h2>





	<form method="POST" action="/osilis/EventEditServlet" onsubmit="return check();">
	  <table>
		 <tr>
		   <td>
		     <label><strong>イベントタイプ</strong><br>
				 <select name="Type">
					 <option value="2">仕事</option>
					 <option value="1">家事</option>
					 <option value="3">インドア</option>
					 <option value="4">アウトドア</option>
				 </select>
			 </label>
		   </td>
		   <td>
		     <label><strong>イベント</strong><br>
				<input type="text" name="Event" placeholder="必須項目" >
			 </label>
		   </td>
		 </tr>
	     <tr>
			<td>
			  	<label><strong>イベント難易度</strong><br>
			  	<select name="Level">
					 <option value="1">簡単</option>
					 <option value="2">普通</option>
					 <option value="3">難しい</option>
				 </select>
		      	</label>
			 </td>
			 <td>
			 	<input type="submit" name="Event_Regist" value="登録" class="EventEdit_Regist">
			 </td>
	   </tr>
	  </table>
	</form>


<div id="">
    <table id="">
      <tr id="">
         <th>イベントタイプ</th><th>イベント</th><th>イベント難易度</th>
      </tr>
      <tr>
	      <c:forEach var="e" items="">
	        <tr class="">
		      <td>${e.type}
		         <select name="Type_Edit">
					 <option>仕事</option>
					 <option>家事</option>
					 <option>インドア</option>
					 <option>アウトドア</option>
				 </select>
			  </td>
		      <td>${e.event}
		      	<input type="text" name="Event_Edit">
		      </td>
		      <td>${e.level}
		      	<select name="Level_Edit">
					 <option>簡単</option>
					 <option>普通</option>
					 <option>難しい</option>
				 </select>
		      </td>
		      <td>
		      	<form method="POST" action="#">
		      		<input type="radio" name="" value="">
		      		<input type="submit" value="詳細" class="tou">
		      	</form>
		      <td>
	        </tr>
		  </c:forEach>
	  </tr>
	</table>
</div>


	<!-- フロートメニュー -->
	<div class="menu">
		 <ul id="nav">
		 	<li><a href="/osilis/TopServlet">Top</a></li>
			<li><a href="/osilis/EventEditServlet">予定の追加</a></li>
		    <li><a href="/osilis/MyPageServlet">MyPage</a></li>
		    <li><a href="/osilis/PastListServlet">履歴</a></li>
		 </ul>
	</div>





	<!-- フッター -->
	<div id="footer">
	      <p>&copy;Copyright C1 GE★RA osilis. All rights reserved.</p>
	</div>


</div>
	<script src="${pageContext.request.contextPath}/js/eventEdit.js"></script>
</body>
</html>