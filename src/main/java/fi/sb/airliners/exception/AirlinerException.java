package fi.sb.airliners.exception;

/**
 * 
 * Exception class for exceptions thrown by the app
 * 
 * @author Pyry
 *
 */
public abstract class AirlinerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected AirlinerException(String msg) {
		super(msg);
	}

	protected AirlinerException(Throwable cause) {
		super(cause);
	}

	protected AirlinerException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
