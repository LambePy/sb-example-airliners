package fi.sb.airliners.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO-class for aircrafts
 * 
 * @author Pyry
 *
 */
@JsonRootName("aircraft")
public class AircraftDto {

	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private final String id;

	@Size(min = 2, max = 255)
	private final String model;

	@Size(min = 2, max = 255)
	private final String manufacturer;

	@NotNull
	private final String airlinerId;

	@JsonCreator
	public AircraftDto(@JsonProperty(value = "id", required = false) final String id,
			@JsonProperty("model") final String model, @JsonProperty("manufacturer") final String manufacturer,
			@JsonProperty("airlinerId") String airlinerId) {
		this.id = id;
		this.model = model;
		this.manufacturer = manufacturer;
		this.airlinerId = airlinerId;
	}

	/**
	 * 
	 * Constructor for Unit testing
	 * 
	 * @param model
	 * @param manufacturer
	 * @param airlinerId
	 */
	public AircraftDto(String model, String manufacturer, String airlinerId) {
		this(null, model, manufacturer, airlinerId);
	}

	public final String getId() {
		return id;
	}

	public final String getModel() {
		return model;
	}

	public final String getManufacturer() {
		return manufacturer;
	}

	public String getAirlinerId() {
		return airlinerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(airlinerId, id, manufacturer, model);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AircraftDto other = (AircraftDto) obj;
		return Objects.equals(airlinerId, other.airlinerId) && Objects.equals(id, other.id)
				&& Objects.equals(manufacturer, other.manufacturer) && Objects.equals(model, other.model);
	}

}