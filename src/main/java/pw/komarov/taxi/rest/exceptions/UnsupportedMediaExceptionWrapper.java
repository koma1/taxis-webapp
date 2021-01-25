package pw.komarov.taxi.rest.exceptions;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.NoArgsConstructor;
import lombok.ToString;

@XmlRootElement(name="UnsupportedMediaException")
@XmlAccessorType(XmlAccessType.PROPERTY)
@NoArgsConstructor
@ToString
public class UnsupportedMediaExceptionWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UnsupportedMediaException e;
	
	public UnsupportedMediaExceptionWrapper(UnsupportedMediaException e) {
		this.e = e;
	}
	
	public String getMessage() {
		return e.getMessage();
	}
	
	public void setMessage(String message) {
	}
}