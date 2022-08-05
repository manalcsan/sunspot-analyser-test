package airbus.sunspotanalyser.api.exceptions;

public class DuplicateException extends RuntimeException {

	private static final long serialVersionUID = -5155040945855706073L;

	public DuplicateException(String message) {
		super(message);
	}

}
