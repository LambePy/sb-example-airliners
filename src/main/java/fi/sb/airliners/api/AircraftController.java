package fi.sb.airliners.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fi.sb.airliners.model.AircraftApi;
import fi.sb.airliners.model.AircraftDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
class AircraftController implements AircraftApi {
	private static final Logger logger = LoggerFactory.getLogger(AircraftController.class);
	private final AircraftService service;

	@Autowired
	AircraftController(AircraftService service) {
		this.service = service;
	}

	@Override
	public AircraftDto getAircraft(@NotNull String aircraftId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AircraftDto createAircraft(@Valid AircraftDto aircraftDto) {
		logger.info("Adding new aircraft");
		return service.addAircraft(aircraftDto);
	}

	@Override
	public AircraftDto updateAircraft(@NotNull String aircraftId, @Valid AircraftDto aircraftDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AircraftDto> getAllAircrafts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAircraft(@NotNull String aircraftId) {
		// TODO Auto-generated method stub
	}
}
