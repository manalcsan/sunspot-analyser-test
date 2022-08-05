package airbus.sunspotanalyser.api.services;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;

import airbus.sunspotanalyser.api.entities.Grid;

/**
 * This interface represents the grids service.
 */
public interface GridService {

	/**
	 * Returns the grids using pagination and filtered by search text.
	 * @param page - the page
	 * @param size - the page size
	 * @param sortField - the sorting field
	 * @param sortOrder - the sorting order
	 * @param searchText - the search text
	 * @return the grids
	 */
	Page<Grid> list(Integer page, Integer size, String sortField, Boolean sortOrder, Optional<String> searchText);

	/**
	 * Returns all grids.
	 * @return the grids
	 */
	List<Grid> list();

	/**
	 * Returns a grid.
	 * @param id - the grid id
	 * @return the grid
	 */
	Grid get(Long id);

	/**
	 * Returns the current grid.
	 * @return the current grid
	 */
	//Grid get();

	/**
	 * Creates a grid.
	 * @param grid - the grid to be created
	 * @return the grid
	 */
	Grid create(Grid grid);

	/**
	 * Updates a grid
	 * @param grid - the grid to be updated
	 * @return the grid
	 */
	Grid update(Grid grid);

	/**
	 * Deletes a grid
	 * @param id - the grid id
	 */
	void delete(Long id);

}

