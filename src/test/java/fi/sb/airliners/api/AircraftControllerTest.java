package fi.sb.airliners.api;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fi.sb.airliners.exception.NotFoundException;
import fi.sb.airliners.model.AircraftDto;
import fi.sb.airliners.model.AircraftEntity;
import fi.sb.airliners.model.AirlinerEntity;
import fi.sb.airliners.model.AirlinerStatus;

@ExtendWith(MockitoExtension.class)
class AircraftControllerTest {

	@Mock
	AirlinerRepository airlinerRepo;

	@Mock
	AircraftRepository repo;

	AircraftController controller;

	@BeforeEach
	void init() {
		AircraftService service = new AircraftService(repo, airlinerRepo);
		controller = new AircraftController(service);
	}

	@Test
	void createAircraft_saves_correct_data() {

		String airlinerId = "f3f00f67-4222-44a1-8bde-1c8c84755c46";
		AirlinerEntity airliner = new AirlinerEntity(airlinerId, "Finnair", "AY", "FIN", AirlinerStatus.ACTIVE);
		when(airlinerRepo.findById(airlinerId)).thenReturn(Optional.of(airliner));

		controller.createAircraft(new AircraftDto("A320NEO", "AIRBUS", airlinerId));

		AircraftEntity expectedResult = new AircraftEntity("A320NEO", "AIRBUS", airliner);

		verify(repo).save(expectedResult);

	}

	@Test
	void createAircraft_throws_correct_exception_when_airliner_is_not_found() {

		String airlinerId = "f3f00f67-4222-44a1-8bde-1c8c84755c46";
		when(airlinerRepo.findById(airlinerId)).thenReturn(Optional.empty());

		AircraftDto aircraftdto = new AircraftDto("A320NEO", "AIRBUS", airlinerId);

		assertThrows(NotFoundException.class, () -> {
			controller.createAircraft(aircraftdto);
		});

	}

}
