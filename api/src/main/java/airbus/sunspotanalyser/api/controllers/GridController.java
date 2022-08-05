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

import airbus.sunspotanalyser.api.controllers.dtos.GridDto;
import airbus.sunspotanalyser.api.entities.Grid;
import airbus.sunspotanalyser.api.services.GridService;

@RestController
public class GridController {

	@Resource
	private ModelMapper mapper;

	@Resource
	private GridService gridService;

    @Operation(summary = "", description = "", 
		tags={ "grid" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The list of Grids", content = @Content(mediaType = "application/json; charset&#x3D;UTF-8", array = @ArraySchema(schema = @Schema(implementation = Grid.class)))),
        @ApiResponse(responseCode = "400", description = "There is an error in your request"),
        @ApiResponse(responseCode = "404", description = "No entries found") })
    @RequestMapping(value = "/sun-spot-analyser-api/grid",
        produces = { "application/json; charset=UTF-8" }, 
        method = RequestMethod.GET)
    public List<GridDto> list() {
        List<Grid> grids = gridService.list();

        return Arrays.asList(mapper.map(grids, GridDto[].class));
	}

    @Operation(summary = "", description = "Deletes an Grid entry by its id", 
		tags={ "grid" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Grid entry successfully deleted"),
        @ApiResponse(responseCode = "400", description = "There is an error in your request"),
        @ApiResponse(responseCode = "404", description = "Entry not found") })
    @RequestMapping(value = "/sun-spot-analyser-api/grid/{id}",
        method = RequestMethod.DELETE)
    public void delete(@Parameter(in = ParameterIn.PATH, description = "An id of an GridDto entry", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        gridService.delete(id);
	}

    @Operation(summary = "", description = "Gets an Grid entry by its id", 
		tags={ "grid" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The requested Grid entry", content = @Content(mediaType = "application/json; charset&#x3D;UTF-8", schema = @Schema(implementation = Grid.class))),
        @ApiResponse(responseCode = "400", description = "There is an error in your request"),
        @ApiResponse(responseCode = "404", description = "Entry not found") })
    @RequestMapping(value = "/sun-spot-analyser-api/grid/{id}",
        produces = { "application/json; charset=UTF-8" }, 
        method = RequestMethod.GET)
    public GridDto get(@Parameter(in = ParameterIn.PATH, description = "An id of an GridDto entry", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        Grid grid = gridService.get(id);

        return mapper.map(grid, GridDto.class);
	}

    @Operation(summary = "", description = "Modifies an Grid entry by its id", 
		tags={ "grid" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Grid entry successfully modified", content = @Content(mediaType = "application/json; charset&#x3D;UTF-8", schema = @Schema(implementation = Grid.class))),
        @ApiResponse(responseCode = "201", description = "Grid entry successfully created", content = @Content(mediaType = "application/json; charset&#x3D;UTF-8", schema = @Schema(implementation = Grid.class))),
        @ApiResponse(responseCode = "400", description = "There is an error in your request"),
        @ApiResponse(responseCode = "404", description = "Entry not found") })
    @RequestMapping(value = "/sun-spot-analyser-api/grid/{id}",
        produces = { "application/json; charset=UTF-8" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    public GridDto update(@Parameter(in = ParameterIn.PATH, description = "An id of an GridDto entry", required=true, schema=@Schema()) @PathVariable("id") Long id,
		@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody GridDto body) {
        Grid grid = mapper.map(body, Grid.class);
        grid.setId(id);
        grid = gridService.update(grid);

        return mapper.map(grid, GridDto.class);
	}

    @Operation(summary = "", description = "Creates a new Grid entry", 
		tags={ "grid" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Grid entry successfully created", content = @Content(mediaType = "application/json; charset&#x3D;UTF-8", schema = @Schema(implementation = Grid.class))),
        @ApiResponse(responseCode = "400", description = "There is an error in your request") })
    @RequestMapping(value = "/sun-spot-analyser-api/grid",
        produces = { "application/json; charset=UTF-8" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    public GridDto create(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody GridDto body) {
        Grid grid = mapper.map(body, Grid.class);
        grid = gridService.create(grid);

        return mapper.map(grid, GridDto.class);
	}

}

