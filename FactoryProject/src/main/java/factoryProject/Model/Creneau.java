package factoryProject.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "creneau")
public class Creneau {
	@Id
	@GeneratedValue
	@JsonView(JsonViews.Common.class)
	private Long id;
	@Version
	private int version;
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	@JsonView(JsonViews.Common.class)
	private Date date;
	@Column(name = "size")
	@JsonView(JsonViews.Common.class)
	private int sizeCreneau;
	@Column(name = "availability")
	@JsonView(JsonViews.Common.class)
	private boolean availability;
	@ManyToOne
	@JoinColumn(name = "trainer_id")
	@JsonView(JsonViews.CreneauWithFormateur.class)
	private Formateur trainer;

	public Creneau() {
		super();
	}

	public Creneau(Date date, int sizeCreneau, boolean dispo, Formateur trainer) {
		super();
		this.date = date;
		this.sizeCreneau = sizeCreneau;
		this.availability = dispo;
		this.trainer = trainer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getSizeCreneau() {
		return sizeCreneau;
	}

	public void setSizeCreneau(int sizeCreneau) {
		this.sizeCreneau = sizeCreneau;
	}

	public boolean getAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Formateur getTrainer() {
		return trainer;
	}

	public void setTrainer(Formateur trainer) {
		this.trainer = trainer;
	}

}
