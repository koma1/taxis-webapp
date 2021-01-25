package pw.komarov.taxi.struts;

import org.apache.struts2.ServletActionContext;

import pw.komarov.taxi.persistence.entity.CountryEntity;
import pw.komarov.taxi.persistence.services.CountryService;

public class CountryAction extends AbstractCrudAction<CountryEntity, CountryService> {
	private static final long serialVersionUID = 1L;

	private static final CountryService countryService = new CountryService();
	
	public CountryAction() {
		super(CountryEntity.class, countryService);
	}
	
	@Override
	public void validateModel() {
		if(getModel().getName().isEmpty())
			addFieldError("model.name", getText("field.empty"));
	}
	
	//AJAX fields validation
	public String ajaxValidateFields() throws Exception {
		String name = ServletActionContext.getRequest().getParameter("name");
		String value = ServletActionContext.getRequest().getParameter("value");
		
		Integer id = getParameterId();
		
		if(name.equalsIgnoreCase("name"))
			setAjaxResult(countryService.checkNameDuplicated(value, id));
		else
			throw new HandledException("Unknown ajax validation request type");
		
		return AJAX_SUCCESS;
	}
	
	//Naming	
	@Override
	public String getEntitySimplePluralName() {
		return "Countries";
	}
}