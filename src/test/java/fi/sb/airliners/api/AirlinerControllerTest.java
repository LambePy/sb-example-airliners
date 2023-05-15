package fi.sb.airliners.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fi.sb.airliners.model.AirlinerDto;
import fi.sb.airliners.model.AirlinerEntity;
import fi.sb.airliners.model.AirlinerStatus;

@ExtendWith(MockitoExtension.class)
class AirlinerControllerTest {

	@Mock
	AirlinerRepository airlinerRepo;

	@Mock
	AircraftRepository aircraftRepo;

	AirlinerController controller;

	@BeforeEach
	void init() {
		AirlinerService service = new AirlinerService(airlinerRepo, aircraftRepo);
		controller = new AirlinerController(service);
	}

	@Test
	void createAirliner_saves_correct_data() {

		AirlinerDto airlinerDto = new AirlinerDto("airbus", "AY", "FIN", AirlinerStatus.ACTIVE);
		AirlinerEntity entity = new AirlinerEntity("airbus", "AY", "FIN", AirlinerStatus.ACTIVE);

		when(airlinerRepo.save(any())).thenReturn(entity);

		AirlinerEntity expectedResult = new AirlinerEntity("airbus", "AY", "FIN", AirlinerStatus.ACTIVE);
		AirlinerDto res = controller.createAirliner(airlinerDto);

		assertThat(res).isNotNull();
		assertThat(res.getCode()).isEqualTo(expectedResult.getCode());
		assertThat(res.getCountry()).isEqualTo(expectedResult.getCountry());
		assertThat(res).isEqualTo(expectedResult.toAirlinerDto());
	}

	@Test
	void getAllAirliners_returns_correct_airliners() {

		List<AirlinerEntity> entityList = new ArrayList<>();
		entityList.add(new AirlinerEntity("airbus", "AY", "FIN", AirlinerStatus.ACTIVE));
		entityList.add(new AirlinerEntity("boeing", "US", "123", AirlinerStatus.DEACTIVE));

		when(airlinerRepo.findAll()).thenReturn(entityList);

		List<AirlinerDto> expectedList = new ArrayList<>();
		expectedList.add(new AirlinerDto("airbus", "AY", "FIN", AirlinerStatus.ACTIVE));
		expectedList.add(new AirlinerDto("boeing", "US", "123", AirlinerStatus.DEACTIVE));

		List<AirlinerDto> actualList = controller.getAllAirliners();

		assertEquals(expectedList.size(), actualList.size());
		for (int i = 0; i < expectedList.size(); i++) {
			assertEquals(expectedList.get(i), actualList.get(i));
		}
		assertEquals(expectedList, actualList);
	}

	@Test
	void getAirliner_returns_correct_airliner() {
		String AirlinerId = "f3f00f67-4222-44a1-8bde-1c8c84755c46";

		when(airlinerRepo.findById(AirlinerId))
				.thenReturn(Optional.of(new AirlinerEntity("airbus", "AY", "FIN", AirlinerStatus.ACTIVE)));
		AirlinerDto dto = controller.getAirliner(AirlinerId);
		AirlinerDto expected = new AirlinerDto("airbus", "AY", "FIN", AirlinerStatus.ACTIVE);

		assertEquals(expected, dto);
	}

	@Test
	void createAirliner_returns_correct_data() {
		AirlinerDto airlinerDto = new AirlinerDto("airbus", "AY", "FIN", AirlinerStatus.ACTIVE);
		AirlinerEntity entity = new AirlinerEntity("airbus", "AY", "FIN", AirlinerStatus.ACTIVE);
		entity.setId("f3f00f67-4222-44a1-8bde-1c8c84755c46");
		when(airlinerRepo.save(any())).thenReturn(entity);

		AirlinerDto res = controller.createAirliner(airlinerDto);
		AirlinerDto expected = new AirlinerDto(entity.getId(), entity.getName(), entity.getCode(), entity.getCountry(),
				entity.getActive());

		assertEquals(expected, res);

	}
}