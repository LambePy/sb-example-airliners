package fi.sb.airliners.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.sb.airliners.exception.NotFoundException;
import fi.sb.airliners.model.AircraftDto;
import fi.sb.airliners.model.AircraftEntity;
import fi.sb.airliners.model.AirlinerDto;
import fi.sb.airliners.model.AirlinerEntity;
import jakarta.validation.constraints.NotNull;

/**
 * Business logic of Airliner Services
 * 
 * @author Pyry
 *
 */
@Service
@Transactional
public class AirlinerService {

	private final AirlinerRepository airlinerRepo;
	private final AircraftRepository aircraftRepo;

	@Autowired
	public AirlinerService(
		final AirlinerRepository airlinerRepo,
		final AircraftRepository aircraftRepo
	)
	{
		this.airlinerRepo = airlinerRepo;
		this.aircraftRepo = aircraftRepo;
	}

	public AirlinerDto getAirliner(String airlinerId) {
		AirlinerEntity airliner = airlinerRepo
			.findById(airlinerId)
			.orElseThrow(() -> new NotFoundException(AirlinerEntity.class, airlinerId));
		return airliner.toAirlinerDto();
	}

	public AirlinerDto createAirliner(final AirlinerDto airlinerdto) {
		AirlinerEntity airliner = airlinerRepo.save(AirlinerEntity.createAirlinerWith(airlinerdto));
		return airliner.toAirlinerDto();
	}

	public List<AircraftDto> getAircrafts(@NotNull String airlinerId) {
		List<AircraftEntity> aircrafts = aircraftRepo.findAllByAirliner(airlinerId);
		return aircrafts.stream().map(AircraftEntity::toAircraftDto).toList();
	}

	public List<AirlinerDto> getAllAirliners() {
		List<AirlinerEntity> airliners = airlinerRepo.findAll();
		return airliners.stream().map(AirlinerEntity::toAirlinerDto).toList();
	}

}