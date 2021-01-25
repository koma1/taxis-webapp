<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Taxi #${it.id}</title>
</head>
<body>
<% 
	/*TaxiEntity taxi = (TaxiEntity)pageContext.getVariableResolver().resolveVariable("model");
	
	//ELContext ctx = pageContext.getELContext();
	//TaxiEntity taxi = (TaxiEntity)ctx.getVariableMapper().resolveVariable("model").getValue(ctx);
	
	StringBuilder sb = new StringBuilder();
	//ToDo: refact it with templates usage
	taxi.getCities().forEach((city) -> sb.append("<a href=\"./../cities/").append(city.getId()).append("\">").append(city.getName()).append("</a>, "));
	if(sb.length() > 1)
		sb.delete(sb.length() - 2, sb.length());*/
%>
	<table>
        <tr>
        	<th>ID</th>
		    <th>Name</th>
		    <th>Phone</th>
		    <th>Cities</th>
		</tr>
		<tr>
            <td>${it.id}</td>
            <td>${it.name}</td>
            <td>${it.phone}</td>
            <td>
				<c:forEach items="${it.cities}" var="city">
					<li><a href="./../cities/${city.id}">${city.name}</a>&nbsp;
				</c:forEach>
           	</td>
        </tr>
    </table>
</body>
</html>