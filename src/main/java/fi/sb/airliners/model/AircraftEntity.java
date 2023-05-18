package fi.sb.airliners.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * 
 * Database model entity for Aircraft class
 * 
 * @author Pyry
 *
 */
@Entity
@Table(name = "AIRCRAFT")
public class AircraftEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * ID is auto generated UUID4 string
	 */
	@Id
	@GenericGenerator(name = "UUID4", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "UUID4")
	@Column(name = "AIRCRAFT_ID", updatable = false, nullable = false, unique = true)
	private String id;

	@Column(name = "MODEL", length = 255, nullable = false)
	private String model;

	@Column(name = "MANUFACTURER", length = 255, nullable = false)
	private String manufacturer;

	@CreationTimestamp
	@Column(name = "CREATED", nullable = false, updatable = false)
	private ZonedDateTime created;

	@UpdateTimestamp
	@Column(name = "UPDATED")
	private ZonedDateTime updated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AIRLINER_ID_FK", referencedColumnName = "AIRLINER_ID")
	private AirlinerEntity airliner;

	/**
	 * JPA needs this
	 */
	protected AircraftEntity() {
	}

	AircraftEntity(String id, String model, String manufacturer, AirlinerEntity airliner) {
		this.id = id;
		this.model = model;
		this.manufacturer = manufacturer;
		this.airliner = airliner;
	}

	/**
	 * Constructor for Unit testing
	 * 
	 * @param model
	 * @param manufacturer
	 * @param airliner
	 */
	public AircraftEntity(String model, String manufacturer, AirlinerEntity airliner) {
		this(null, model, manufacturer, airliner);
	}

	public void updateAircraftWith(AircraftDto aircraftDTO) {
		this.model = aircraftDTO.getModel();
		this.manufacturer = aircraftDTO.getManufacturer();
	}

	public static AircraftEntity createAircraftWith(AircraftDto aircraftDTO, AirlinerEntity airlinerEntity) {
		return new AircraftEntity(aircraftDTO.getModel(), aircraftDTO.getManufacturer(), airlinerEntity);
	}

	public AircraftDto toAircraftDto() {
		return new AircraftDto(id, model, manufacturer, airliner.getId());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public ZonedDateTime getCreated() {
		return created;
	}

	public void setCreated(ZonedDateTime created) {
		this.created = created;
	}

	public ZonedDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(ZonedDateTime updated) {
		this.updated = updated;
	}

	public AirlinerEntity getAirliner() {
		return airliner;
	}

	public void setAirliner(AirlinerEntity airliner) {
		this.airliner = airliner;
	}

	@Override
	public int hashCode() {
		return Objects.hash(airliner, created, id, manufacturer, model, updated);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AircraftEntity other = (AircraftEntity) obj;
		return Objects.equals(airliner, other.airliner) && Objects.equals(created, other.created)
				&& Objects.equals(id, other.id) && Objects.equals(manufacturer, other.manufacturer)
				&& Objects.equals(model, other.model) && Objects.equals(updated, other.updated);
	}

}