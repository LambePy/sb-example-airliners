package fi.sb.airliners.configuration;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

	/**
	 * 
	 * Security configuration Secures all endpoints that are not whitelisted. Production use you
	 * will need e.g., oAuth2Resourceserver to serve roles / authentication levels
	 * 
	 * NOT INTENDED FOR PRODUCTION USE AS IT IS
	 * 
	 * @param httpSecurity
	 * @return
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf(CsrfConfigurer::disable)
			.sessionManagement(
				(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			)
			.authorizeHttpRequests(
				req -> req
					.requestMatchers(
						"/v3/api-docs",
						"/v3/api-docs/*",
						"/swagger-ui/**",
						"/swagger-ui.html",
						"api/v1/**"
					)
					.permitAll()
			);
		httpSecurity.authorizeHttpRequests().requestMatchers(toH2Console()).permitAll();
		httpSecurity.headers().frameOptions().sameOrigin();
		return httpSecurity.build();
	}

}