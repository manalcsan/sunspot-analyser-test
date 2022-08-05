package airbus.sunspotanalyser.api.daos;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import airbus.sunspotanalyser.api.entities.Grid;

/**
 * This class represents the data access object of a {@link "Grid"}.
 */
public interface GridDao extends JpaRepository<Grid, Long> {

}


