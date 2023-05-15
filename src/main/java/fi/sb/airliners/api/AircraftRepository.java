package fi.sb.airliners.api;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fi.sb.airliners.model.AircraftEntity;

/**
 * 
 * JPA Repository for Aircrafts
 * 
 * Uses JPQL (Java Persistence Query Language)
 * 
 * @author Pyry
 *
 */
@Repository
public interface AircraftRepository extends JpaRepository<AircraftEntity, String> {

	/**
	 * Find Aircraft by aircraftID
	 * 
	 * @param aircraftID
	 * @return
	 */
	Optional<AircraftEntity> findById(final String aircraftId);

	/**
	 * Find all aircrafts by airlinerID
	 * 
	 * @param airlinerID
	 * @return
	 */
	@Query("SELECT a FROM AircraftEntity a WHERE a.airliner.id = :airlinerId")
	List<AircraftEntity> findAllByAirliner(@Param("airlinerId") String airlinerId);

}