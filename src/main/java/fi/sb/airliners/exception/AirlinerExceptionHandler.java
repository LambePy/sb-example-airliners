package fi.sb.airliners.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fi.sb.airliners.model.AppError;
import jakarta.annotation.Nullable;

/**
 * 
 * Handle some common API exceptions and errors
 * 
 * @author Pyry
 *
 */
@RestControllerAdvice
class AirlinerExceptionHandler extends ResponseEntityExceptionHandler {

	private final ExceptionLogger exLogger;

	@Autowired
	AirlinerExceptionHandler(ExceptionLogger exLogger) {
		this.exLogger = exLogger;
	}

	@ExceptionHandler(AirlinerException.class)
	public ResponseEntity<Object> handleAirlinerException(AirlinerException ex, WebRequest req) {
		return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, req);

	}

	@ExceptionHandler(RuntimeException.class)
	ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
		return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(Error.class)
	ResponseEntity<Object> handleError(Error ex, WebRequest request) {
		return handleException(new RuntimeException("API call resulted into error!", ex),
				HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	private ResponseEntity<Object> handleException(Exception ex, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, null, new HttpHeaders(), status, request);
	}

	/**
	 * 
	 * Override handleExceptionInternal to add AppError DTO model into automaticly
	 * handled requests (e.g., invalid json in request)
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object obj, HttpHeaders headers,
			HttpStatusCode status, WebRequest req) {

		exLogger.log(status, ex);
		AppError errBody = ErrorBodyCreator.createFixedMessageErrorBody(status.value());
		return super.handleExceptionInternal(ex, errBody, headers, status, req);

	}

}
