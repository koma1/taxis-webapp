<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Entity not found exception</title>
	</head>
	
	<body>
		<h1>Entity not found by ID!</h1>
		Entity class: <b>${model.entityClass}</b>
			<br>
		Message: <b>${model.message}</b>
	</body>
</html>