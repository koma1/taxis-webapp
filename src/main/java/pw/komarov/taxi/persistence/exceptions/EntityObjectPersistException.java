package pw.komarov.taxi.persistence.exceptions;

public class EntityObjectPersistException extends EntityObjectException {
	private static final long serialVersionUID = 1L;

	public EntityObjectPersistException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityObjectPersistException(Throwable cause) {
		super(cause.getMessage(), cause);
	}
	
}