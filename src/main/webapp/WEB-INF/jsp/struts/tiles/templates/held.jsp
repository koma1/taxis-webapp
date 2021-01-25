<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://tiles.apache.org/tags-tiles" prefix = "tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<s:set var="title">${action.entitySimpleName} held by <tiles:insertAttribute name="heldByEntity"/></s:set>
<title>${title}</title>
</head>
    <body>
      <h1>${title}<s:if test='crud.toString() == "REMOVE"'>&nbsp;(and can not be removed)</s:if>:</h1>
      <s:if test="(heldBy instanceof java.util.List)">
      	<c:forEach items="${heldBy}" var="entity">
      		<li>${entity.name} (id: ${entity.id})
      	</c:forEach>
      </s:if>
      <s:else>
      	${heldBy}
      </s:else>
    </body>
</html>