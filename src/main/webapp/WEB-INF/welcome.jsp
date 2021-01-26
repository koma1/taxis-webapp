<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.UserDetails"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" 	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c"   	uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if(principal instanceof UserDetails)
		request.setAttribute("username", ((UserDetails)principal).getUsername());
%>
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
	<br>
	<sec:authorize access="hasRole('ROLE_AUTHORIZED')">
		User: ${username}
		<c:url var="logoutUrl" value="/logout" />
			<form action="${logoutUrl}" id="logout" method="post">
			    <input type="hidden" name="${_csrf.parameterName}"
			           value="${_csrf.token}" />
			</form>
		<a href="#" onclick="document.getElementById('logout').submit();">Logout</a>
	</sec:authorize>
	<sec:authorize access="!hasRole('ROLE_AUTHORIZED')">
		<a href="login">Login</a>
	</sec:authorize>
</body>
</html>