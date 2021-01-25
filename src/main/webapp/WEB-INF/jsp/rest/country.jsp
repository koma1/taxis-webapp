<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Country #${it.id}</title>
</head>
<body>
	<table>
        <tr>
        	<th>ID</th>
		    <th>Name</th>
		</tr>
		<tr>
            <td>${model.id}</td>
            <td>${model.name}</td>
        </tr>
    </table>
</body>
</html>