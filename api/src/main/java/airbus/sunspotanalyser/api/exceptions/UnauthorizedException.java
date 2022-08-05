package airbus.sunspotanalyser.api.exceptions;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 7298974035004256424L;

	public UnauthorizedException(String message) {
		super(message);
	}

}
