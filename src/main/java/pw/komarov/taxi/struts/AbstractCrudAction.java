package pw.komarov.taxi.struts;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import lombok.Getter;
import lombok.Setter;
import pw.komarov.taxi.persistence.entity.EntityIntf;
import pw.komarov.taxi.persistence.exceptions.EntityObjectException;
import pw.komarov.taxi.persistence.exceptions.EntityObjectHeldException;
import pw.komarov.taxi.persistence.exceptions.EntityObjectNotFoundException;
import pw.komarov.taxi.persistence.exceptions.EntityObjectPersistException;
import pw.komarov.taxi.persistence.services.EntityService;

public abstract class AbstractCrudAction<E extends EntityIntf, S extends EntityService<E>> extends ActionSupport implements ModelDriven<E> {
	private static final long serialVersionUID = 1L;
	
	public static final String AJAX_SUCCESS = "ajax_success";
	public static final String OBJECT_HELD = "object_held";
	public static final String POSTED = "posted";
	
	public enum CrudType {ADD, EDIT, REMOVE, LIST};
	
	private final Class<E> entityClass;
	private final S service;
	
	protected AbstractCrudAction(Class<E> entityClass, S service) {
		this.entityClass = entityClass;
		this.service = service;
	}
	
	@Getter
	private CrudType crud;
	
	@Getter
	@Setter
	private E model;
	
	protected Integer getParameterId() {
		String id = ServletActionContext.getRequest().getParameter("id");
		try {
			return (id != null) && (!id.isEmpty()) ? Integer.valueOf(id) : null;
		} catch (NumberFormatException e) {
			throw new HandledException(String.format("Bad parameter value: id=%s", id), e);
		}
	}
	
	protected Integer getParameterIdMandatory() {
		Integer id = getParameterId();
		if(id == null)
			throw new HandledException("Mandatory parameter 'id' is not specified");
		
		return id;
	}
	
	//Action
	public String list() {
		crud = CrudType.LIST;
		
		return crud.toString().toLowerCase(); //"list"
	}
	
	//Action
	public String add() {
		crud = CrudType.ADD;
		
		return Action.INPUT;
	}
	
	//Action
	public String edit() throws EntityObjectNotFoundException {	
		crud = CrudType.EDIT;
		
		Integer id = getParameterIdMandatory();
		
		model = service.getEntity(id);
		if(model == null)
			throw new EntityObjectNotFoundException(entityClass, id);
		
		return Action.INPUT;
	}
	
	//Action
	public String save() throws EntityObjectException {
		crud = getModel().isNew() ? CrudType.ADD : CrudType.EDIT;
		
		validateModel();
		if(!getFieldErrors().isEmpty() || !getActionErrors().isEmpty())
			return Action.ERROR;
		
		try {
			service.save(model);
		} catch(EntityObjectPersistException e) {
			addActionError(e.getLocalizedMessage());
			
			return Action.ERROR;
		}
		
		return POSTED;		
	}
	
	@Getter
	private Object heldBy;
	
	//Action
	public String remove() throws EntityObjectException {
		crud = CrudType.REMOVE;

		try {
			service.delete(getParameterIdMandatory());
		} catch (EntityObjectHeldException held) {
			heldBy = held.getHeldBy();
			
			return OBJECT_HELD;
		}
		
		return POSTED;
	}
	
	protected abstract void validateModel();
	
	//AJAX
	private String ajaxResult;
	
	protected void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	
	protected void setAjaxResult(boolean ajaxResult) {
		setAjaxResult(String.valueOf(ajaxResult));
	}
	
	public InputStream getAjaxInputStream() throws UnsupportedEncodingException {
		return new ByteArrayInputStream(ajaxResult.getBytes(StandardCharsets.UTF_8));
	}
	
	//Namings
	public String getEntitySimpleName() {
		return EntityIntf.getEntitySimpleName(entityClass);
	}
	
	public String getEntitySimplePluralName() {
		return EntityIntf.getEntitySimplePluralName(entityClass);
	}
}