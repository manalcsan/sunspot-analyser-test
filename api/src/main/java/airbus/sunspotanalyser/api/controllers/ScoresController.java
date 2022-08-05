package airbus.sunspotanalyser.api.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import airbus.sunspotanalyser.api.controllers.dtos.ScoresDto;
import airbus.sunspotanalyser.api.entities.Scores;
import airbus.sunspotanalyser.api.services.ScoresService;

@RestController
public class ScoresController {

	@Resource
	private ModelMapper mapper;

	@Resource
	private ScoresService scoresService;

    @Operation(summary = "", description = "Gets an Grid scores entry by its id", 
		tags={ "scores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The requested Grid scores entry", content = @Content(mediaType = "application/json; charset&#x3D;UTF-8", schema = @Schema(implementation = Scores.class))),
        @ApiResponse(responseCode = "400", description = "There is an error in your request"),
        @ApiResponse(responseCode = "404", description = "Entry not found") })
    @RequestMapping(value = "/sun-spot-analyser-api/scores/{id}",
        produces = { "application/json; charset=UTF-8" }, 
        method = RequestMethod.GET)
    public List<ScoresDto> get(@Parameter(in = ParameterIn.PATH, description = "An id of an Grid entry", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        List<Scores> scores = scoresService.get(id);

        return Arrays.asList(mapper.map(scores, ScoresDto[].class));
	}
}

