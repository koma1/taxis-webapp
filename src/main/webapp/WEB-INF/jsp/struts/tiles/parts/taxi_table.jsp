<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="service" scope="page" class="pw.komarov.taxi.persistence.services.TaxiService"/>
		    <tr>
		        <th>ID</th>
		        <th>Name</th>
		        <th>Phone</th>
		        <th>Cities</th>
		        <th>Actions</th>
		    </tr>
		    <c:forEach items="${service.allEntities}" var="taxi">
		        <tr>
		            <td>${taxi.id}</td>
		            <td>${taxi.name}</td>
		            <td>${taxi.phone}</td>
		            <td>${taxi.citiesText}</td>
		            <td>
						<a href="<s:url action='edit'><s:param name="id">${taxi.id}</s:param></s:url>">Edit</a>
							|
						<a href="<s:url action='remove'><s:param name="id">${taxi.id}</s:param></s:url>">Remove</a>
		            </td>
		        </tr>
		    </c:forEach>