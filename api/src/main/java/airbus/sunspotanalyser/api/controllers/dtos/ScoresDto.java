package airbus.sunspotanalyser.api.controllers.dtos;

import java.util.UUID;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import airbus.sunspotanalyser.api.controllers.helpers.CreateOperation;
import airbus.sunspotanalyser.api.controllers.helpers.UpdateOperation;
import airbus.sunspotanalyser.api.entities.Scores;

/**
 * This class represents the data transfer object of an {@link "Scores"}.
 */
public class ScoresDto {

	private Long id;
	private Long idGrid;
	private String scores;

	@Null(groups = CreateOperation.class)
	@NotNull(groups = UpdateOperation.class)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdGrid() {
		return idGrid;
	}

	public void setIdGrid(Long idGrid) {
		this.idGrid = idGrid;
	}
	
	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}
	
}

