package fi.sb.airliners.configuration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.net.HttpHeaders;

import fi.sb.airliners.api.AirlinerService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
class SecurityIT {

	private static final String API_DOCS_ENDPOINT = "/v3/api-docs";
	private static final String API_ENDPOINT = "/api/v1/airliners";
	private static final String FORBIDDEN_ENDPOINT = "/api/v2/";
	private static final String AIRLINER = "{" + "  \"name\": \"Finnair\","
		+ "  \"code\": \"AY\", \"country\": \"FIN\"," + "  \"airlinerStatus\": \"ACTIVE\"" + "}";
	private static final String CACHE_CONTROL_VALUE = "no-cache, no-store, max-age=0, must-revalidate";
	private static final String PRAGMA_VALUE = "no-cache";
	@Autowired
	private WebApplicationContext context;
	@MockBean
	private AirlinerService airlinerService;
	private MockMvc mvc;

	@BeforeEach
	void init() {
		mvc = MockMvcBuilders
			.webAppContextSetup(context)
			.apply(SecurityMockMvcConfigurers.springSecurity())
			.build();
	}

	@Test
	void testCallApiDocs() throws Exception {
		mvc.perform(get(API_DOCS_ENDPOINT)).andExpect(status().isOk());
	}

	@Test
	void testForbiddenEndpoiIsForbidden() throws Exception {
		mvc
			.perform(get(FORBIDDEN_ENDPOINT))
			.andExpect(status().isForbidden())
			.andExpect(header().string(HttpHeaders.CACHE_CONTROL, CACHE_CONTROL_VALUE))
			.andExpect(header().string(HttpHeaders.PRAGMA, PRAGMA_VALUE));
	}

	@Test
	void testApiRouteIsAllowed() throws Exception {
		mvc
			.perform(post(API_ENDPOINT).content(AIRLINER).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
	}

}
