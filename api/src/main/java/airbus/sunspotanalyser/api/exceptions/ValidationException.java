package airbus.sunspotanalyser.api.exceptions;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -3461496659334877719L;

	private String objectName;
	private String field;
	private String code;
	private String rejectedValue;

	public ValidationException(String message, String objectName, String field, String code, String rejectedValue) {
		super(message);
		this.objectName = objectName;
		this.field = field;
		this.rejectedValue = rejectedValue;
		this.code = code;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(String rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

}
