package fi.sb.airliners.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import fi.sb.airliners.model.AppError;

@Component
class ErrorBodyCreator {

	static final String CLIENT_ERROR_MESSAGE = "ERROR IN API CALL";
	static final String INTERNAL_ERROR_MESSAGE = "ERROR IN SERVICE";

	ErrorBodyCreator() {
	}

	static AppError createFixedMessageErrorBody(int statusCode) {
		HttpStatus httpStatus = HttpStatus.resolve(statusCode);

		AppError body = new AppError();
		if (httpStatus != null && httpStatus.is4xxClientError()) {
			body.message(CLIENT_ERROR_MESSAGE);
		} else {
			body.message(INTERNAL_ERROR_MESSAGE);
		}
		return body;
	}

}
