<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Entity not found!</title>
</head>
<body>
	<h1>Entity not found!</h1>
	<h2>Entity: ${exception.entitySimpleName}</h2>
	<h3>ID: <s:property value="exception.id"/></h3>
	<h4>Operation: <s:property value="crud"/></h4>
</body>
</html>