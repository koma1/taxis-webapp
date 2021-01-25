package pw.komarov.taxi.persistence.exceptions.wrappers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pw.komarov.taxi.persistence.exceptions.EntityObjectPersistException;

@XmlRootElement(name="EntityObjectPersistException")
@XmlAccessorType(XmlAccessType.PROPERTY)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EntityObjectPersistExceptionWrapper implements ExceptionWrapper<EntityObjectPersistException> {
	private static final long serialVersionUID = 1L;
	
	@Setter
	private EntityObjectPersistException e;
	
	public String getMessage() {
		return e.getMessage();
	}
	
	public void setMessage(String message) {}
	
	public String getCause() {
		return e.getCause().getClass().getName();
	}
	
	public void setCause(String s) {}
}
