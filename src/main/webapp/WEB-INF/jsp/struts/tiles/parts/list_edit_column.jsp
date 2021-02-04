<%@ taglib prefix="s" uri="/struts-tags" %>

					<form action="<s:url action='remove'/>" id="rmv_${modelId}" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="hidden" name="id" value="${modelId}"/>
					</form>
					
		            <td>
						<a href="<s:url action='edit'><s:param name="id">${modelId}</s:param></s:url>">Edit</a>
							|
						<a href="#remove_${modelId}" onclick="document.getElementById('rmv_${modelId}').submit();">Remove</a>
		            </td>