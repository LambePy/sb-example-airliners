package fi.sb.airliners.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Database model entity for Airliner class
 * 
 * @author Pyry
 *
 */
@Entity
@Table(name = "AIRLINER")
public class AirlinerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * ID is auto generated UUID4 string
	 */
	@Id
	@GeneratedValue(generator = "UUID4")
	@GenericGenerator(name = "UUID4", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "AIRLINER_ID", unique = true, updatable = false, nullable = false)
	private String id;

	@Column(name = "AIRLINER_NAME", length = 50, unique = false, nullable = false)
	private String name;

	@Column(name = "AIRLINER_CODE", length = 10, unique = false, nullable = false)
	private String code;

	@Column(name = "COUNTRY", length = 50, nullable = false)
	private String country;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "ACTIVE", nullable = false, length = 10)
	private AirlinerStatus status = AirlinerStatus.ACTIVE;

	@CreationTimestamp
	@Column(name = "CREATED", nullable = false, updatable = false)
	private ZonedDateTime created;

	@UpdateTimestamp
	@Column(name = "UPDATED")
	private ZonedDateTime updated;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "airliner", cascade = CascadeType.ALL)
	private List<AircraftEntity> aircrafts = new ArrayList<>();

	protected AirlinerEntity() {

	}

	public AirlinerEntity(String id, String name, String code, String country, AirlinerStatus status) {
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
	public AirlinerEntity(String name, String code, String country, AirlinerStatus status) {
		this(null, name, code, country, status);
	}

	/**
	 * Updates an existing airliner with using AirlinerDTO as param
	 * 
	 * @param airlinerDTO
	 */
	public void updateAirlinerWith(AirlinerDto airlinerDTO) {
		this.name = airlinerDTO.getName();
		this.code = airlinerDTO.getCode();
		this.country = airlinerDTO.getCountry();
		this.status = airlinerDTO.getAirlinerStatus();
	}

	/**
	 * Creates a new airliner with AirlinerDTO as a aparam
	 * 
	 * @param airlinerDTO
	 */
	public static AirlinerEntity createAirlinerWith(AirlinerDto airlinerDTO) {
		return new AirlinerEntity(airlinerDTO.getName(), airlinerDTO.getCountry(), airlinerDTO.getCode(),
				airlinerDTO.getAirlinerStatus()

		);
	}

	public AirlinerDto toAirlinerDto() {
		return new AirlinerDto(id, name, code, country, status);
	}

	/**
	 * Get list of aircrafts that one airliner owns
	 * 
	 * @return
	 */
	public List<AircraftEntity> getAircrafts() {
		return List.copyOf(aircrafts);
	}

	/**
	 * Get timestamp when airliner object was created
	 * 
	 * @return
	 */
	public ZonedDateTime getCreated() {
		return created;
	}

	/**
	 * Get timestamp of last update
	 * 
	 * @return
	 */
	public ZonedDateTime getUpdated() {
		return updated;
	}

	public void setCreated(ZonedDateTime created) {
		this.created = created;
	}

	public void setUpdated(ZonedDateTime updated) {
		this.updated = updated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public AirlinerStatus getActive() {
		return status;
	}

	public void setActive(AirlinerStatus active) {
		this.status = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, country, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		AirlinerEntity other = (AirlinerEntity) obj;
		return Objects.equals(code, other.code) && Objects.equals(country, other.country)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

}