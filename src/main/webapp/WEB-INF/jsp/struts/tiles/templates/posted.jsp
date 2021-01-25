<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri = "http://tiles.apache.org/tags-tiles" prefix = "tiles"%>
<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>Successful!</title>
  </head>
  <body>
    <h3>Thank you for your helping extends us our services!</h3>
	
    <s:if test="crud.toString() != 'REMOVE'">
    	<p>${action.entitySimpleName} information: <s:property value="model"/></p>
    </s:if>

    <a href="${pageContext.request.contextPath}">Return to home page</a>
    	|
    <a href="<s:url action='list'/>">List of ${action.entitySimplePluralName.toLowerCase()}</a>
    	|
    <a href="<s:url action='add'/>">Add ${action.entitySimpleName.toLowerCase()}</a>
  </body>
</html>