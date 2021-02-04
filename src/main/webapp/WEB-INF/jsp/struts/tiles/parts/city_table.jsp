<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="service" scope="page" class="pw.komarov.taxi.persistence.services.CityService"/>
		    <tr>
		        <th>ID</th>
		        <th>Name</th>
		        <th>Country</th>
		        <th>Actions</th>
		    </tr>		    
		    <c:forEach items="${service.allEntities}" var="model">
		    	<c:set var="modelId" value="${model.id}" scope="request"/>
		        <tr>
		            <td>${model.id}</td>
		            <td>${model.name}</td>
		            <td>${model.country.name}</td>
					
					<jsp:include page="list_edit_column.jsp"/>
		        </tr>
		    </c:forEach>