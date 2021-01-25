package pw.komarov.taxi.persistence.exceptions.wrappers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pw.komarov.taxi.persistence.exceptions.EntityObjectHeldException;

@XmlRootElement(name="EntityObjectHeldException")
@XmlType(propOrder = {"heldObject", "heldBy"})
@XmlAccessorType(XmlAccessType.PROPERTY)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EntityObjectHeldExceptionWrapper implements ExceptionWrapper<EntityObjectHeldException> {
	private static final long serialVersionUID = 1L;
	
	private EntityObjectHeldException e;
	
	public String getHeldObject() {
		return e.getEntity().toString();
	}
	
	public void setHeldObject(String s) {}
	
	public String getHeldBy() {
		return e.getHeldBy().toString();
	}
	
	public void setHeldBy(String s) {}
}