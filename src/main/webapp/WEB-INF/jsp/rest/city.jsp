<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>City #${it.id}</title>
</head>
<body>
	<table>
        <tr>
        	<th>ID</th>
		    <th>Name</th>
		    <th>Country</th>
		</tr>
		<tr>
            <td>${model.id}</td>
            <td>${model.name}</td>
            <td><a href="./../countries/${model.country.id}">${model.country.name}</a></td>
        </tr>
    </table>
</body>
</html>