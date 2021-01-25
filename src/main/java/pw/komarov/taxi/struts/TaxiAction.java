package pw.komarov.taxi.struts;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import pw.komarov.taxi.persistence.entity.TaxiEntity;
import pw.komarov.taxi.persistence.services.TaxiService;

public class TaxiAction extends AbstractCrudAction<TaxiEntity, TaxiService> {
	private static final long serialVersionUID = 1L;
	
	private static final TaxiService taxiService = new TaxiService();
	
	public TaxiAction() {
		super(TaxiEntity.class, taxiService);
	}
	
	public Map<Integer, String> getAllCities() {	
		Map<Integer, String> result = new HashMap<>();
		taxiService.getAllCities().forEach(city -> result.put(city.getId(), city.getName()));
		
		return result;
	}
	
	public List<TaxiEntity> getAllTaxis() {	
		return taxiService.getAllEntities();
	}

	@Override
	public void validateModel() {
		if(getModel().getName().isEmpty())
			addFieldError("model.name", getText("field.empty"));
		if(getModel().getPhone().isEmpty())
			addFieldError("model.phone", getText("field.empty"));
		if(getModel().getCitiesIdObject() == null || getModel().getCitiesIdObject().isEmpty())
			addFieldError("model.citiesId", getText("field.empty"));
	}
	
	public String ajaxValidateFields() throws Exception {
		String name = ServletActionContext.getRequest().getParameter("name");
		String value = ServletActionContext.getRequest().getParameter("value");
		
		Integer id = getParameterId();
		
		if(name.equalsIgnoreCase("name"))
			setAjaxResult(taxiService.checkNameDuplicated(value, id));
		else if(name.equalsIgnoreCase("phone"))
			setAjaxResult(taxiService.checkPhoneDuplicated(value, id));
		else
			throw new HandledException("Unknown ajax validation request type");
		
		return AJAX_SUCCESS;
	}
	
}
