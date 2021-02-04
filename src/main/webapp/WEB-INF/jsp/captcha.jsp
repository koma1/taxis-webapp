<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="pw.komarov.taxi.captcha.CaptchaServlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set scope="application" var="captchaResultAttrName" value="${CaptchaServlet.CAPTCHA_RESULT_SESSION_ATTRIBUTE_NAME}"/>
<c:set var="captchaResult" value="${sessionScope[captchaResultAttrName]}"/>
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
	<c:choose>
		<c:when test="${captchaResult == 'BAD_REQUEST'}">
			<span class="errorMessage">Bad request has arrived!</span>
			<br>
		</c:when>
		<c:when test="${captchaResult == 'EXPIRED'}">
			<span class="errorMessage">Captcha has expired!</span>
			<br>
		</c:when>
		<c:when test="${captchaResult == 'VALUE'}">
			<span class="errorMessage">Invalid input!</span>
			<br>
		</c:when>
	</c:choose>
	<img src="captcha.image"/>
	<form method="post" action="captcha.image">
		<input type="text" name="captcha" autocomplete="off"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit"/>
	</form>
</body>
</html>