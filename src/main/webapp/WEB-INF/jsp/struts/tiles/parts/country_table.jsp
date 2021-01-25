<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%request.setAttribute("countries", new pw.komarov.taxi.persistence.services.CountryService().getAllEntities());%>
		    <tr>
		        <th>ID</th>
		        <th>Name</th>
		        <th>Actions</th>
		    </tr>
		    <c:forEach items="${countries}" var="country">
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