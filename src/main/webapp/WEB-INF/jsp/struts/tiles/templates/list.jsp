<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri = "http://tiles.apache.org/tags-tiles" prefix = "tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<s:set var="title">${action.entitySimplePluralName} list</s:set>
<title>${title}</title>
</head>
    <body>
      <h1>${title}</h1>
		<table>
			<tiles:insertAttribute name="table"/>
		</table>
		<br>
		<a href="<s:url action='add'/>">Add new...</a>
    </body>
</html>