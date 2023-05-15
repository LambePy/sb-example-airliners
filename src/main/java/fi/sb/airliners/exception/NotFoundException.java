package fi.sb.airliners.exception;

public class NotFoundException extends AirlinerException {
	private static final long serialVersionUID = 1L;

	public NotFoundException(String msg, String id) {
		super(String.format("%sid=%s not found", msg, id));
	}

	public NotFoundException(Class<?> className, Object id) {
		this(className.getName(), id.toString());

	}
}
