package fi.sb.airliners.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

/**
 * 
 * Exception logger
 * 
 * @author Pyry
 *
 */
@Component
public class ExceptionLogger {

	private final Logger logger = LoggerFactory.getLogger(ExceptionLogger.class);

	void log(HttpStatusCode status, Exception ex) {
		if (ex instanceof AirlinerException || !status.is4xxClientError()) {
			logger.error("Error occoured", ex);
		} else {
			if (logger.isErrorEnabled()) {
				logger.error(toStringWithoutMessage(ex));
			}
		}
	}

	/**
	 * 
	 * Error details may contain sensitive data. Filter out all messages from
	 * stacktrace before logging it
	 * 
	 * @param t
	 * @return
	 */
	private String toStringWithoutMessage(Throwable t) {

		StringBuilder sb = new StringBuilder();

		while (t != null) {
			sb.append(t.getClass().getName());
			sb.append(System.lineSeparator());
			// Stacktrace should not contain sensitive data
			for (StackTraceElement element : t.getStackTrace()) {
				sb.append(element);
				sb.append(System.lineSeparator());
			}
			t = t.getCause();
		}
		return sb.toString();

	}

}
