<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>


<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>リスト作成 ｜ 推リス</title>
<link rel="stylesheet" type = "text/css" href="/${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" type = "text/css" href="/${pageContext.request.contextPath}/css/create.css">
<link rel="icon" href="${pageContext.request.contextPath}/imgs/推リスicon.png">
</head>


<body>
 <div class="wrapper">
 <form method="#" action="#">
	<label><input type="radio" value="平日" name="week">平日</label>
	<label><input type="radio" value="休日" name="week">休日</label><br>
	<input type="submit" value="予定の作成">
 </form>


 </div>
<script src="${pageContext.request.contextPath}/js/createList.js" >
 </script>
</body>






</html>