package fi.sb.airliners.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * 
 * Configuration class to create OpenAPI definition
 * {@link https://www.baeldung.com/spring-rest-openapi-documentation}
 * 
 * @author Pyry
 *
 */
@Configuration
@OpenAPIDefinition(
	info = @Info(title = "Airliners API", version = "v1", description = "Airliners API")
)
public class OpenAPIConfiguration {
}
