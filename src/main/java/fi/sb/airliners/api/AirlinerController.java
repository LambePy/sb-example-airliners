package fi.sb.airliners.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fi.sb.airliners.model.AircraftDto;
import fi.sb.airliners.model.AirlinerApi;
import fi.sb.airliners.model.AirlinerDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
class AirlinerController implements AirlinerApi {

	private final AirlinerService service;
	private static final Logger logger = LoggerFactory.getLogger(AirlinerController.class);

	@Autowired
	public AirlinerController(AirlinerService service) {
		this.service = service;
	}

	@Override
	public AirlinerDto getAirliner(@NotNull String airlinerId) {
		logger.debug("Fetching airliner...");
		return service.getAirliner(airlinerId);

	}

	@Override
	public List<AirlinerDto> getAllAirliners() {
		logger.debug("Fetching all airliners..");
		return service.getAllAirliners();
	}

	@Override
	public AirlinerDto createAirliner(@Valid AirlinerDto airlinerDto) {
		logger.debug("Creating a new airliner...");
		return service.createAirliner(airlinerDto);

	}

	@Override
	public AirlinerDto updateAirliner(@NotNull String airlinerId, @Valid AirlinerDto airlinerDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteairliner(@NotNull String aircraftId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AircraftDto> getAircrafts(@NotNull String airlinerId) {
		return service.getAircrafts(airlinerId);

	}

}
