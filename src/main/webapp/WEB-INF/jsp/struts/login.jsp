<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<c:if test="${param.error != null}"><span class="errorMessage">Invalid username/password!</span><br></c:if>
	<c:url var="loginUrl" value="/login"/>
        <form action="${loginUrl}" method="post">
            <input type="text" name="username" placeholder="Username"/><br/>
            <input type="password" name="password"  placeholder="Password"/><br/>
            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
            <button type="submit">Login</button>
        </form>
</body>
</html>