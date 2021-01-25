package pw.komarov.taxi.persistence.exceptions;

@SuppressWarnings("serial")
public abstract class EntityObjectException extends Exception {
	protected EntityObjectException() {
		super();
	}

	protected EntityObjectException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	protected EntityObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	protected EntityObjectException(String message) {
		super(message);
	}

	protected EntityObjectException(Throwable cause) {
		super(cause);
	}
	
	
}