<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>リストTop | 推しリス</title>
<link rel="icon" href="${pageContext.request.contextPath}/imgs/推リスicon.png">
<link rel="stylesheet" type="text/css" href="/osilis/css/common.css">
<link rel="stylesheet" type="text/css" href="/osilis/css/list.css">
</head>

<body>

<header>
	<img alt="推しリス" src="/osilis/imgs/osirisu.png" class="osirisukun">
	<a href="/osilis/LogoutServlet">ログアウト</a>
</header>

<!--  全体を囲むdivクラスwrapper  -->
<div class="wrapper">



<h2>今日のリスト</h2>
 <!--  <form method="POST" action="/osilis/ListServlet"> -->
  <fieldset style="border:1px solid #000000; padding: 5px;" class="listtable">
	<legend class="house">家事</legend>
		<table class="listtable">
		    <c:forEach var="e" items="${eventsList}"  >
			   <c:if test="${e.type==1}">
						<tr>
							<td>${e.event}</td>
							<td>
								<form method="post" action="/osilis/ListServlet">
									<input type="hidden" name="number" value="${e.list_dataNum}">
								  <c:if test="${e.check_tf==false}">
									<input type="checkbox" name="check_tf" value="true"
										onchange="formSubmit(this.form)" >
									<!-- if構文つかってcheckedをつけたりつけなかったり -->
								  </c:if>
								  <c:if test="${e.check_tf==true}">
									<input type="checkbox" name="check_tf" value="false"
										onchange="formSubmit(this.form)" checked>
								  </c:if>
								</form>
							</td>
						</tr>
				</c:if>
			</c:forEach>
		</table>
   </fieldset>

     <fieldset style="border:1px solid #000000; padding: 5px;" class="listtable">
	 <legend class="job">仕事</legend>
		<table class="listtable">
			<c:forEach var="e" items="${eventsList}">
			   <c:if test="${e.type==2}">
			        <tr>
							<td>${e.event}</td>
							<td>
								<form method="post" action="/osilis/ListServlet">
									<input type="hidden" name="number" value="${e.list_dataNum}">
									<c:if test="${e.check_tf==false}">
									<input type="checkbox" name="check_tf" value="true"
										onchange="formSubmit(this.form)" >
									<!-- if構文つかってcheckedをつけたりつけなかったり -->
								  </c:if>
								  <c:if test="${e.check_tf==true}">
									<input type="checkbox" name="check_tf" value="false"
										onchange="formSubmit(this.form)" checked>
								  </c:if>
								</form>
							</td>

			        </tr>
				</c:if>
			</c:forEach>
		</table>
    </fieldset>

    <fieldset style="border:1px solid #000000; padding: 5px;" class="listtable">
	<legend class="play">インドア・アウトドア</legend>
		<table class="listtable">
			<tr>
			<c:forEach var="e" items="${eventsList}">
				<c:if test="${e.type=='3'}">
			        <tr>
						<td>${e.event}</td>
							<td>
							<form method="post" action="/osilis/ListServlet">
								<input type="hidden" name="number" value="${e.list_dataNum}">
								<c:if test="${e.check_tf==false}">
								<input type="checkbox" name="check_tf" value="true"
									onchange="formSubmit(this.form)" >
								<!-- if構文つかってcheckedをつけたりつけなかったり -->
							    </c:if>
							    <c:if test="${e.check_tf==true}">
								<input type="checkbox" name="check_tf" value="false"
									onchange="formSubmit(this.form)" checked>
							    </c:if>

							</form>
						</td>
			        </tr>
		        </c:if>
			  </c:forEach>
			</tr>
			<tr>
		     <c:forEach var="e" items="${eventsList}">
		      <c:if test="${e.type=='4'}">
		        <tr>
					<td>${e.event}</td>
					<td>
						<form method="post" action="/osilis/ListServlet">
							<input type="hidden" name="number" value="${e.list_dataNum}">
							<c:if test="${e.check_tf==false}">
								<input type="checkbox" name="check_tf" value="true"
									onchange="formSubmit(this.form)" >
								<!-- if構文つかってcheckedをつけたりつけなかったり -->
							</c:if>
							<c:if test="${e.check_tf==true}">
								<input type="checkbox" name="check_tf" value="false"
									onchange="formSubmit(this.form)" checked>
							</c:if>
						</form>
					</td>
		        </tr>
		       </c:if>
			  </c:forEach>
			</tr>
		</table>
    </fieldset>

<!-- </form> -->

	  <button onclick="location.href='/osilis/RewardServlet'">報奨画面へ！</button>


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






</div>

<script src="${pageContext.request.contextPath}/js/list.js"></script>

</body>

</html>