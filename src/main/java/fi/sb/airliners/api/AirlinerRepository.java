package fi.sb.airliners.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.sb.airliners.model.AirlinerEntity;

/**
 * 
 * JPA Repository for Airliners
 * 
 * Uses JPQL (Java Persistence Query Language)
 * 
 * @author Pyry
 *
 */
@Repository
public interface AirlinerRepository extends JpaRepository<AirlinerEntity, String> {

}
