<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enter captha</title>
<style type="text/css">
	.errorMessage {color:red;}
</style>
</head>
<body>
	<h1>This is captcha servlet!</h1>
	<c:if test="${sessionScope.captchaResult != null}">
		<c:choose>
			<c:when test="${sessionScope.captchaResult == 'BAD_REQUEST'}">
				<span class="errorMessage">Bad request has arrived!</span>
				<br>
			</c:when>
			<c:when test="${sessionScope.captchaResult == 'EXPIRED'}">
				<span class="errorMessage">Captcha has expired!</span>
				<br>
			</c:when>
			<c:when test="${sessionScope.captchaResult == 'VALUE'}">
				<span class="errorMessage">Invalid input!</span>
				<br>
			</c:when>
		</c:choose>
	</c:if>
	<img src="captcha.image"/>
	<form method="post" action="captcha.image">
		<input type="text" name="captcha" autocomplete="off"/>
		<input type="submit"/>
	</form>
</body>
</html>