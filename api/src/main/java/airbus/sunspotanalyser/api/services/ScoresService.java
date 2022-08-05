package airbus.sunspotanalyser.api.services;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;

import airbus.sunspotanalyser.api.entities.Scores;

/**
 * This interface represents the scoress service.
 */
public interface ScoresService {

	/**
	 * Returns the scoress using pagination and filtered by search text.
	 * @param page - the page
	 * @param size - the page size
	 * @param sortField - the sorting field
	 * @param sortOrder - the sorting order
	 * @param searchText - the search text
	 * @return the scoress
	 */
	Page<Scores> list(Integer page, Integer size, String sortField, Boolean sortOrder, Optional<String> searchText);

	/**
	 * Returns all scoress.
	 * @return the scoress
	 */
	List<Scores> list();

	/**
	 * Returns a scores.
	 * @param id - the scores id
	 * @return the scores
	 */
	List<Scores> get(Long id);

	/**
	 * Returns the current scores.
	 * @return the current scores
	 */
	//Scores get();

	/**
	 * Creates a scores.
	 * @param scores - the scores to be created
	 * @return the scores
	 */
	Scores create(Scores scores);

	/**
	 * Updates a scores
	 * @param scores - the scores to be updated
	 * @return the scores
	 */
	Scores update(Scores scores);

	/**
	 * Deletes a scores
	 * @param id - the scores id
	 */
	void delete(Long id);

}

