package fi.sb.airliners.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * DTO-class for Airliners
 * 
 * @author Pyry
 *
 */
@JsonRootName("airliner")
public class AirlinerDto {

	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private final String id;

	@NotNull
	private final String name;

	@NotNull
	private final String code;

	@NotNull
	private final String country;

	private final AirlinerStatus status;

	@JsonCreator
	public AirlinerDto(@JsonProperty(value = "id", required = false) String id, @JsonProperty("name") String name,
			@JsonProperty("code") String code, @JsonProperty("country") String country,
			@JsonProperty("airlinerStatus") AirlinerStatus status) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.country = country;
		this.status = status;
	}

	/**
	 * Constructor for Unit testing
	 * 
	 * @param name
	 * @param code
	 * @param country
	 * @param status
	 */
	public AirlinerDto(String name, String code, String country, AirlinerStatus status) {
		this(null, name, code, country, status);

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getCountry() {
		return country;
	}

	public AirlinerStatus getAirlinerStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, country, id, name, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirlinerDto other = (AirlinerDto) obj;
		return Objects.equals(code, other.code) && Objects.equals(country, other.country)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name) && status == other.status;
	}

}