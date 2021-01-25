<%@ taglib prefix="s" uri="/struts-tags"%>

					<s:text name="label.name"/>:&nbsp;
	        	<s:textfield name="model.name" size="40" theme="simple" oninput="clearTimeout(window.timerName); window.timerName = setTimeout(doValidateField, 1500, 'name', this.value, '#name-valid-img');"/>&nbsp;
	        		<img id="name-valid-img"/><br>&nbsp;<s:fielderror fieldName="model.name"/>
	        	
	        	<s:select name="model.countryId" key="label.country" list="allCountries" emptyOption="true"/>