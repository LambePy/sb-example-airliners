package fi.sb.airliners.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@JsonRootName("errorResponse")
@JsonInclude(Include.NON_NULL)
public class AppError {

	@JsonProperty("message")
	private String message;
	@JsonProperty("code")
	private String code;

	public AppError code(String code) {
		this.code = code;
		return this;
	}

	/**
	 * Error code
	 * 
	 * @return code
	 */
	@NotNull
	@Schema(name = "code", description = "Error code", requiredMode = Schema.RequiredMode.REQUIRED)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public AppError message(String message) {
		this.message = message;
		return this;
	}

	@NotNull
	@Schema(name = "message", description = "Error message", requiredMode = Schema.RequiredMode.REQUIRED)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppError other = (AppError) obj;
		return Objects.equals(code, other.code) && Objects.equals(message, other.message);
	}

}
