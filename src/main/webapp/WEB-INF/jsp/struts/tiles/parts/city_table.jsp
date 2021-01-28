<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="service" scope="page" class="pw.komarov.taxi.persistence.services.CityService"/>
		    <tr>
		        <th>ID</th>
		        <th>Name</th>
		        <th>Country</th>
		        <th>Actions</th>
		    </tr>		    
		    <c:forEach items="${service.allEntities}" var="city">
		        <tr>
		            <td>${city.id}</td>
		            <td>${city.name}</td>
		            <td>${city.country.name}</td>
		            <td>
						<a href="<s:url action='edit'><s:param name="id">${city.id}</s:param></s:url>">Edit</a>
							|
						<a href="<s:url action='remove'><s:param name="id">${city.id}</s:param></s:url>">Remove</a>
		            </td>
		        </tr>
		    </c:forEach>