<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix = "tiles" uri = "http://tiles.apache.org/tags-tiles"%>
<s:if test="crud.toString() == 'ADD'"><s:set var="adding" value="true"/></s:if>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	function doValidateField(name, value, imgId) {
		let entityId = <s:if test='adding'>null</s:if><s:else>${model.id}</s:else>;
		if(!!value)
			$.ajax({
				url     : 'ajaxValidate.action',
				method  : 'POST',
				data    : {name : name, value : value, id: entityId},
				success : function(resultText){
					var correctResponse = ((resultText === 'true') || (resultText === 'false'))
					if(correctResponse) {
						var duplicated = (resultText === 'true');
						if(duplicated)
							$(imgId).attr('src', '${pageContext.request.contextPath}/images/invalid16x16.png')
						else
							$(imgId).attr('src', '${pageContext.request.contextPath}/images/valid16x16.png')
					} else
						console.log('Wrong response! ' + resultText);
				},
				error   : function(jqXHR, exception) {
					$(imgId).attr('src', '${pageContext.request.contextPath}/images/blank_pixel.png')
					
					console.log('Error occured!');
				}
			});
		else
			$(imgId).attr('src', '${pageContext.request.contextPath}/images/blank_pixel.png')
	}
</script>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
<title><s:if test="adding">Add</s:if><s:else>Edit</s:else>&nbsp;${action.entitySimpleName.toLowerCase()}</title>
</head>
<body>
    <h2>Struts2 - ${action.entitySimpleName}&nbsp;<s:if test="adding">add</s:if><s:else>edit #${model.id}</s:else></h2>
    <s:actionerror/>
	    <s:form action="save">
	       		<s:hidden name="model.id"/>&nbsp;
	        
	        <tiles:insertAttribute name="formContent"/>
	        
	        <s:if test="adding">
	        	<s:submit method="execute" key="label.add" align="center" />
	        </s:if>
	        <s:else>
	        	<s:submit method="execute" key="label.save" align="center" />
	        </s:else>
	    </s:form>
</body>
</html>