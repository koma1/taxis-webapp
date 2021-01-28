<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="service" scope="page" class="pw.komarov.taxi.persistence.services.CountryService"/>
		    <tr>
		        <th>ID</th>
		        <th>Name</th>
		        <th>Actions</th>
		    </tr>
		    <c:forEach items="${service.allEntities}" var="country">
		        <tr>
		            <td>${country.id}</td>
		            <td>${country.name}</td>
		            <td>
						<a href="<s:url action='edit'><s:param name="id">${country.id}</s:param></s:url>">Edit</a>
							|
						<a href="<s:url action='remove'><s:param name="id">${country.id}</s:param></s:url>">Remove</a>
		            </td>
		        </tr>
		    </c:forEach>