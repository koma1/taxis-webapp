package pw.komarov.taxi.struts;

public class HandledException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public HandledException() {
		super();
	}

	public HandledException(String message) {
		super(message);
	}

	public HandledException(Throwable cause) {
		super(cause);
	}

	public HandledException(String message, Throwable cause) {
		super(message, cause);
	}

	public HandledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
