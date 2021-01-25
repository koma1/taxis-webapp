package pw.komarov.taxi.rest.exceptions;

public class UnsupportedMediaException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public UnsupportedMediaException(String message) {
		super(message);
	}
}