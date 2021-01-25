package pw.komarov.taxi.persistence.exceptions.wrappers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.NoArgsConstructor;
import lombok.ToString;
import pw.komarov.taxi.persistence.exceptions.EntityObjectNotFoundException;

@XmlRootElement(name="EntityObjectNotFoundException")
@XmlType(propOrder={"message","id","entityClass"})
@XmlAccessorType(XmlAccessType.PROPERTY)
@NoArgsConstructor
@ToString
public class EntityObjectNotFoundExceptionWrapper implements ExceptionWrapper<EntityObjectNotFoundException> {
	private static final long serialVersionUID = 1L;
	
	private EntityObjectNotFoundException e;
	
	public EntityObjectNotFoundExceptionWrapper(EntityObjectNotFoundException e) {
		this.e = e;
	}
	
	public String getMessage() {
		return e.getMessage();
	}
	
	public void setMessage(String message) {}
	
	public Integer getId() {
		return e.getId();
	}
	
	public void setId(Integer id) {
	}
	
	public String getEntityClass() {
		return e.getEntityClass().getSimpleName();
	}
	
	public void setEntityClass(String clazz) {
	}
}