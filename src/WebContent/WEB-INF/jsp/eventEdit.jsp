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
	<img alt="推しリス" src="/osilis/imgs/osirisu.png" class="osirisukun">
	<a href="/osilis/LogoutServlet">ログアウト</a>
</header>

<!--  全体を囲むdivクラスwrapper  -->
<div class="wrapper">

<h1>予定の管理</h1>

<h2>予定の登録</h2>





	<form method="POST" action="/osilis/EventEditServlet" onsubmit="return check();">
	  <table>
		 <tr>
		   <td>
		     <label><strong>イベント</strong><br>
				<input type="text" name="Event" placeholder="必須項目" class="eventText" style="width:20em; height:2em; margin:0 auto 0 auto;">
			 </label>
		   </td>
		 </tr>
		 <tr class="down">
		   <td>
		     <label><strong>タイプ</strong><br>
				 <select name="Type">
					 <option value="2">仕事</option>
					 <option value="1">家事</option>
					 <option value="3">インドア</option>
					 <option value="4">アウトドア</option>
				 </select>
			 </label>
		   </td>
		   <td>
			  	<label><strong>難易度</strong><br>
			  	<select name="Level">
					 <option value="1">簡単</option>
					 <option value="2">普通</option>
					 <option value="3">難しい</option>
				 </select>
		      	</label>
			</td>
			<td>
			 	<input type="submit" name="Event_Regist" value="登録">
			</td>
	   </tr>
	  </table>
	</form>


<div id="">
    <table id="">
      <tr id="">
         <th>タイプ</th><th>イベント</th><th>難易度</th>
      </tr>
      <tr>
	      <c:forEach var="e" items="${eventsList}">
	        <tr class="">
		      <td>
		         <select name="Type_Edit">
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
				 </select>
			  </td>
		      <td>
		      	<input type="text" name="Event_Edit" value ="${e.event} ">
		      </td>
		      <td>
		      	<select name="Level_Edit">
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