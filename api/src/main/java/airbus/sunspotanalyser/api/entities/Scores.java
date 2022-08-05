package airbus.sunspotanalyser.api.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonType;

/**
 * This class represents a scores.
 */
@Entity
@Table(name = "scores")
@TypeDef(name = "json", typeClass = JsonType.class)
public class Scores {

	private Long id;
	private Long idGrid;
	private String scores;

	public Scores() {
		super();
	}

	@PrePersist
	public void onCreate() {
	}

	@PreUpdate
	public void onUpdate() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "idgrid")
	public Long getIdGrid() {
		return idGrid;
	}

	public void setIdGrid(Long idGrid) {
		this.idGrid = idGrid;
	}

	@Column(name = "scores")
	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}


}

