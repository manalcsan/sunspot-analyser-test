package airbus.sunspotanalyser.api.exceptions;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8461551593931421584L;

	public NotFoundException(String message) {
		super(message);
	}

}
