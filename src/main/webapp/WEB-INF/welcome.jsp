<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome!</title>
</head>
<body>
	<h1>Welcome to ${pageContext.servletContext.servletContextName}!</h1>
	<h5>Rest services:</h5>
	<a href="rest/taxi">Taxis</a>
	<br>
	<a href="rest/cities">Cities</a>
	<br>
	<a href="rest/countries">Countries</a>
	<br>
	<h5>Web services (struts2):</h5>
	<a href="taxis/">Taxis</a>
	<br>
	<a href="cities/">Cities</a>
	<br>
	<a href="countries/">Countries</a>
	<br>
</body>
</html>