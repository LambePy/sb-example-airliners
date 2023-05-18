package fi.sb.airliners.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.sb.airliners.exception.NotFoundException;
import fi.sb.airliners.model.AircraftDto;
import fi.sb.airliners.model.AircraftEntity;
import fi.sb.airliners.model.AirlinerEntity;
import jakarta.transaction.Transactional;

/**
 * 
 * Business logic of Aircraft Service
 * 
 * @author Pyry
 *
 */
@Service
@Transactional
public class AircraftService {

	private final AircraftRepository aircraftRepo;
	private final AirlinerRepository airlinerRepo;

	@Autowired
	public AircraftService(final AircraftRepository aircraftRepo, final AirlinerRepository airlinerRepo) {
		this.aircraftRepo = aircraftRepo;
		this.airlinerRepo = airlinerRepo;
	}

	public AircraftDto addAircraft(final AircraftDto aircraftDto) {
		Optional<AirlinerEntity> airliner = airlinerRepo.findById(aircraftDto.getAirlinerId());
		if (!airliner.isPresent()) {
			throw new NotFoundException(AirlinerEntity.class, aircraftDto.getAirlinerId());
		}
		AircraftEntity aircraft = AircraftEntity.createAircraftWith(aircraftDto, airliner.get());
		aircraftRepo.save(aircraft);
		return aircraft.toAircraftDto();
	}
}