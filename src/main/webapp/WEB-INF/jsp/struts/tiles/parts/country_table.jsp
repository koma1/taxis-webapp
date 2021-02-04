<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="service" scope="page" class="pw.komarov.taxi.persistence.services.CountryService"/>
		    <tr>
		        <th>ID</th>
		        <th>Name</th>
		        <th>Actions</th>
		    </tr>
		    <c:forEach items="${service.allEntities}" var="model">
		        <c:set var="modelId" value="${model.id}" scope="request"/>
		        <tr>
		            <td>${model.id}</td>
		            <td>${model.name}</td>
					
					<jsp:include page="list_edit_column.jsp"/>
		        </tr>
		    </c:forEach>