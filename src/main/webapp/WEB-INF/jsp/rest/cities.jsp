<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Cities list</title>
    </head>
    <body>
        <h1>All cities</h1>
        <table>
		    <tr>
		        <th>ID</th>
		        <th>Name</th>
		        <th>Country</th>
		    </tr>
		    <c:forEach items="${it.cities}" var="city">
		        <tr>
		            <td><a href="${pageContext.request.contextPath}/rest/cities/${city.id}">${city.id}</a></td>
		            <td><a href="${pageContext.request.contextPath}/rest/cities/${city.id}">${city.name}</a></td>
		            <td><a href="${pageContext.request.contextPath}/rest/countries/${city.country.id}">${city.country.name}</a></td>
		        </tr>
		    </c:forEach>
		</table>
    </body>
</html>