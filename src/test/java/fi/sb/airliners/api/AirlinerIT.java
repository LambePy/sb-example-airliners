package fi.sb.airliners.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.reactive.server.WebTestClient;

import fi.sb.airliners.model.AirlinerDto;
import fi.sb.airliners.model.AirlinerStatus;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@ExtendWith(MockitoExtension.class)
class AirlinerIT {

	private static final String AIRLINER_PATH = "/api/v1/airliners";

	@Autowired
	WebTestClient webClient;

	@Test
	void get_Airliners() {
		List<AirlinerDto> res = webClient
			.get()
			.uri(AIRLINER_PATH + "/all")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus()
			.isOk()
			.expectHeader()
			.contentType(MediaType.APPLICATION_JSON)
			.returnResult(AirlinerDto.class)
			.getResponseBody()
			.collectList()
			.block();
		assertEquals(4, res.size());
	}

	@Test
	void get_Airliner_by_id() {
		String airlinerId = "f7f4182f-881f-4b05-a12b-1a1ccaa33d1b";
		AirlinerDto response = webClient
			.get()
			.uri(
				uriBuilder -> uriBuilder.path(AIRLINER_PATH).path("/{airlinerId}").build(airlinerId)
			)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus()
			.isOk()
			.expectHeader()
			.contentType(MediaType.APPLICATION_JSON)
			.returnResult(AirlinerDto.class)
			.getResponseBody()
			.blockFirst();
		assertEquals("FIN", response.getCountry());
	}

	@Test
	void create_new_airliner() {
		AirlinerDto dto = new AirlinerDto(null, "AY", "FIN", "Finnair", AirlinerStatus.ACTIVE);
		AirlinerDto create = webClient
			.post()
			.uri(AIRLINER_PATH)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus()
			.isCreated()
			.expectHeader()
			.contentType(MediaType.APPLICATION_JSON)
			.returnResult(AirlinerDto.class)
			.getResponseBody()
			.blockFirst();
		assertNotNull(create.getId());
	}

}
