<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Taxies list</title>
    </head>
    <body>
        <h1>All taxies services</h1>
        <table>
		    <tr>
		        <th>ID</th>
		        <th>Name</th>
		    </tr>
		    <c:forEach items="${it.taxis}" var="taxi">
		        <tr>
		            <td><a href="${pageContext.request.contextPath}/rest/taxi/${taxi.id}">${taxi.id}</a></td>
		            <td><a href="${pageContext.request.contextPath}/rest/taxi/${taxi.id}">${taxi.name}</a></td>
		        </tr>
		    </c:forEach>
		</table>
    </body>
</html>