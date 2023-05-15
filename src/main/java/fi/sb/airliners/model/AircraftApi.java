package fi.sb.airliners.model;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Tag(name = "Aircrafts", description = "Aircraft management API")
@RequestMapping(path = "/api/v1/aircrafts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public interface AircraftApi {

	/**
	 * Get aircraft with ID
	 * 
	 * @param aircraftId
	 * @return
	 */
	@GetMapping(value = "/{aircraftId}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Aircraft retrieved succesfully"),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))),
			@ApiResponse(responseCode = "404", description = "Aircraft Not found", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))) })
	AircraftDto getAircraft(@PathVariable(name = "aircraftId", required = true) @NotNull String aircraftId);

	/**
	 * Create new aircraft
	 * 
	 * @param AircraftDto
	 * @param AirlinerID
	 * @return
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create new aircraft")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Aircraft created successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))) })
	@ResponseStatus(code = HttpStatus.CREATED)
	AircraftDto createAircraft(@Valid @RequestBody AircraftDto aircraftDto);

	/**
	 * Update existing aircraft
	 * 
	 * @param AircraftID
	 * @param AircraftDto
	 * @return
	 */
	@PutMapping(value = "/{aircraftID}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update aircraft")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Aircraft updated successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))),
			@ApiResponse(responseCode = "404", description = "Existing aircraft Not found", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))) })
	AircraftDto updateAircraft(@PathVariable(name = "aircraftId", required = true) @NotNull String aircraftId,
			@Valid @RequestBody AircraftDto aircraftDto);

	/**
	 * Get all aircrafts
	 * 
	 * @return
	 */
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	@Operation(summary = "Get all saved aircrafts")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Aircrafts retrieved succesfully"),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))),
			@ApiResponse(responseCode = "404", description = "Aircraft Not found", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))) })
	List<AircraftDto> getAllAircrafts();

	@DeleteMapping(value = "/{aircraftId}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Deletes aircraft")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Deleted aircraft"),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))),
			@ApiResponse(responseCode = "404", description = "Aircraft Not found", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = AppError.class))) })
	void deleteAircraft(@PathVariable(name = "aircraftId", required = true) @NotNull String aircraftId);
}
