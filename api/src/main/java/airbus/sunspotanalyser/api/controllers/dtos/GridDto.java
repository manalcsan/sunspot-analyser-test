package airbus.sunspotanalyser.api.controllers.dtos;

import java.util.UUID;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import airbus.sunspotanalyser.api.controllers.helpers.CreateOperation;
import airbus.sunspotanalyser.api.controllers.helpers.UpdateOperation;
import airbus.sunspotanalyser.api.entities.Grid;

/**
 * This class represents the data transfer object of an {@link "Grid"}.
 */
public class GridDto {

	private Long id;
	private Integer size;
	private String gridvalues;

	@Null(groups = CreateOperation.class)
	@NotNull(groups = UpdateOperation.class)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	public String getGridvalues() {
		return gridvalues;
	}

	public void setGridvalues(String gridvalues) {
		this.gridvalues = gridvalues;
	}
	
}

