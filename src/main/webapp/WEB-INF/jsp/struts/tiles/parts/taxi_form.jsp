<%@ taglib prefix="s" uri="/struts-tags"%>

<s:text name="label.name"/>:&nbsp;
	<s:textfield name="model.name" size="40" theme="simple" oninput="clearTimeout(window.timerName); window.timerName = setTimeout(doValidateField, 1500, 'name', this.value, '#name-valid-img');"/>&nbsp;
		<img id="name-valid-img"/><br>&nbsp;<s:fielderror fieldName="model.name"/>
<s:text name="label.phone"/>:&nbsp;
	<s:textfield name="model.phone" size="20" theme="simple" oninput="clearTimeout(window.timerPhone); window.timerPhone = setTimeout(doValidateField, 1500, 'phone', this.value, '#phone-valid-img');"/>&nbsp;
		<img id="phone-valid-img"/><s:fielderror fieldName="model.phone"/>
	<s:checkboxlist name="model.citiesId" key="label.cities" list="allCities" 
		value="model.citiesId"/>