package pw.komarov.taxi.persistence.exceptions.wrappers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.NoArgsConstructor;
import lombok.ToString;
import pw.komarov.taxi.persistence.exceptions.EntityObjectIncompletedException;

@XmlRootElement(name="EntityObjectIncompletedException")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder={"id","entityClass","property"})
@NoArgsConstructor
@ToString
public class EntityObjectIncompletedExceptionWrapper implements ExceptionWrapper<EntityObjectIncompletedException> {
	private static final long serialVersionUID = 1L;
	
	private EntityObjectIncompletedException e;
	
	public EntityObjectIncompletedExceptionWrapper(EntityObjectIncompletedException e) {
		this.e = e;
	}
	
	public String getEntityClass() {
		return e.getEntity().getClass().getName();
	}
	public void setEntityClass(String s) {}
	
	public Integer getId() {
		return e.getEntity().getId();
	}
	public void setId(Integer id) {}
	
	public String getProperty() {
		return e.getProperty();
	}
	public void setProperty(String s) {}
}
