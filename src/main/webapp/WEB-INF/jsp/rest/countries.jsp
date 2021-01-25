<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Countries list</title>
    </head>
    <body>
        <h1>All countries</h1>
        <table>
		    <tr>
		        <th>ID</th>
		        <th>Name</th>
		    </tr>
		    <c:forEach items="${it.countries}" var="country">
		        <tr>
		            <td><a href="${pageContext.request.contextPath}/rest/countries/${country.id}">${country.id}</a></td>
		            <td><a href="${pageContext.request.contextPath}/rest/countries/${country.id}">${country.name}</a></td>
		        </tr>
		    </c:forEach>
		</table>
    </body>
</html>