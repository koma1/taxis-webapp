package pw.komarov.taxi.struts;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import pw.komarov.taxi.persistence.entity.CityEntity;
import pw.komarov.taxi.persistence.services.CityService;

public class CityAction extends AbstractCrudAction<CityEntity, CityService> {
	private static final long serialVersionUID = 1L;
	
	private static final CityService cityService = new CityService();
	
	public CityAction() {
		super(CityEntity.class, cityService);
	}
	
	public Map<Integer, String> getAllCountries() {	
		Map<Integer, String> result = new HashMap<>();
		cityService.getAllCountries().forEach(country -> result.put(country.getId(), country.getName()));
		
		return result;
	}
	
	@Override
	public void validateModel() {
		if(getModel().getName().isEmpty())
			addFieldError("model.name", getText("field.empty"));
		if(getModel().getCountryIdObject() == null)
			addFieldError("model.countryId", getText("field.empty"));
	}
	
	//AJAX fields validation
	public String ajaxValidateFields() throws Exception {
		String name = ServletActionContext.getRequest().getParameter("name");
		String value = ServletActionContext.getRequest().getParameter("value");
		
		Integer id = getParameterId();
		
		if(name.equalsIgnoreCase("name"))
			setAjaxResult(cityService.checkNameDuplicated(value, id));
		else
			throw new HandledException("Unknown ajax validation request type");
		
		return AJAX_SUCCESS;
	}
	
	//Naming	
	@Override
	public String getEntitySimplePluralName() {
		return "Cities";
	}
	
}