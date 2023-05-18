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
@Tag(name = "Airliners", description = "Airliner management API")
@RequestMapping(path = "/api/v1/airliners", produces =
{ MediaType.APPLICATION_JSON_VALUE })
public interface AirlinerApi {

	/**
	 * Get all airliners
	 * 
	 * @return
	 */
	@GetMapping(
		value = "/all",
		consumes = MediaType.ALL_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(summary = "Search for all airliners")
	@ApiResponses(value =
	{
		@ApiResponse(responseCode = "200", description = "Airliners retrieved succesfully"),
		@ApiResponse(
			responseCode = "400",
			description = "Bad request",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		),
		@ApiResponse(
			responseCode = "404",
			description = "Airliner Not found",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		),
		@ApiResponse(
			responseCode = "500",
			description = "Internal server error",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		) })
	List<AirlinerDto> getAllAirliners();

	/**
	 * Get airliner with ID
	 * 
	 * @param airlinerId
	 * @return
	 */
	@GetMapping(
		value = "/{airlinerId}",
		consumes = MediaType.ALL_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@ApiResponses(value =
	{
		@ApiResponse(responseCode = "200", description = "Airliner retrieved succesfully"),
		@ApiResponse(
			responseCode = "400",
			description = "Bad request",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		),
		@ApiResponse(
			responseCode = "404",
			description = "Airliner Not found",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		),
		@ApiResponse(
			responseCode = "500",
			description = "Internal server error",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		) })
	AirlinerDto getAirliner(
		@PathVariable(name = "airlinerId", required = true) @NotNull String airlinerId
	);

	/**
	 * Create new Airliner
	 * 
	 * @param airlinerDto
	 * @return
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create new Airliner")
	@ApiResponses(value =
	{
		@ApiResponse(responseCode = "201", description = "Airliner created successfully"),
		@ApiResponse(
			responseCode = "400",
			description = "Bad request",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		),
		@ApiResponse(
			responseCode = "500",
			description = "Internal server error",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		) })
	@ResponseStatus(code = HttpStatus.CREATED)
	AirlinerDto createAirliner(@Valid @RequestBody AirlinerDto airlinerDto);

	/**
	 * Update existing Airliner
	 * 
	 * @param AirlinerId
	 * @param AirlinerDto
	 * @return
	 */
	@PutMapping(value = "/{airlinerID}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update Airliner")
	@ApiResponses(value =
	{
		@ApiResponse(responseCode = "200", description = "Airliner updated successfully"),
		@ApiResponse(
			responseCode = "400",
			description = "Bad request",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		),
		@ApiResponse(
			responseCode = "404",
			description = "Existing Airliner Not found",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		),
		@ApiResponse(
			responseCode = "500",
			description = "Internal server error",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		) })
	AirlinerDto updateAirliner(
		@PathVariable(name = "airlinerId", required = true) @NotNull String airlinerId,
		@Valid @RequestBody AirlinerDto airlinerDto
	);

	/*
	 * Delete airliner
	 */
	@DeleteMapping(
		value = "/{airlinerId}",
		consumes = MediaType.ALL_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(summary = "Deletes airliner")
	@ApiResponses(value =
	{
		@ApiResponse(responseCode = "204", description = "Deleted airliner"),
		@ApiResponse(
			responseCode = "400",
			description = "Bad request",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		),
		@ApiResponse(
			responseCode = "404",
			description = "airliner Not found",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		),
		@ApiResponse(
			responseCode = "500",
			description = "Internal server error",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		) })
	void deleteairliner(
		@PathVariable(name = "airlinerId", required = true) @NotNull String aircraftId
	);

	/**
	 * Get Aircrafts of a specific airliner
	 * 
	 * @param airlinerId
	 * @return
	 */
	@GetMapping(
		value = "/{airlinerId}/aircrafts",
		consumes = MediaType.ALL_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(summary = "Get all aircrafts of an airliner")
	@ApiResponses(value =
	{
		@ApiResponse(
			responseCode = "200",
			description = "Successfully retrieved aircrafts of an airlner"
		),
		@ApiResponse(
			responseCode = "400",
			description = "Bad request",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		),
		@ApiResponse(
			responseCode = "404",
			description = "Existing Airliner Not found",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		),
		@ApiResponse(
			responseCode = "500",
			description = "Internal server error",
			content = @Content(
				mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
				schema = @Schema(implementation = AppError.class)
			)
		) })
	List<AircraftDto> getAircrafts(
		@PathVariable(name = "airlinerId", required = true) @NotNull String airlinerId
	);

}