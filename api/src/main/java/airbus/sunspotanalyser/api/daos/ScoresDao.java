package airbus.sunspotanalyser.api.daos;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airbus.sunspotanalyser.api.entities.Scores;

/**
 * This class represents the data access object of a {@link "Scores"}.
 */
public interface ScoresDao extends JpaRepository<Scores, Long> {

	@Query("select t from Scores t "
		+ "where t.idGrid = ?1")
	List<Scores> findAllByGridId(Long gridId);

}


