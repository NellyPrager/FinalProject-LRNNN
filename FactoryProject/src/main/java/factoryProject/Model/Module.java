package factoryProject.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "modules")
public class Module {
	@Id
	@GeneratedValue
	@JsonView(JsonViews.Common.class)
	private Long id;
	@Version
	private int version;
	@Column(name = "date_debut")
	@Temporal(TemporalType.DATE)
	@JsonView(JsonViews.Common.class)
	private Date dateDebut;
	@OneToOne
	@JoinColumn(name = "trainer_id")
	@JsonView(JsonViews.Common.class)
	private Formateur trainer;
	@OneToOne
	@JoinColumn(name = "subject_id")
	@JsonView(JsonViews.Common.class)
	private Matiere subject;
	@OneToOne
	@JoinColumn(name = "room_id")
	@JsonView(JsonViews.Common.class)
	private Salle room;
	@ManyToOne
	@JoinColumn(name = "formation_id")
	@JsonView(JsonViews.Common.class)
	private Formation formation;

	public Module() {
		super();
	}

	public Module(Date dateDebut, Formateur trainer, Matiere subject, Salle room, Formation formation) {
		super();
		this.dateDebut = dateDebut;
		this.trainer = trainer;
		this.subject = subject;
		this.room = room;
		this.formation = formation;
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

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Formateur getTrainer() {
		return trainer;
	}

	public void setTrainer(Formateur trainer) {
		this.trainer = trainer;
	}

	public Matiere getSubject() {
		return subject;
	}

	public void setSubject(Matiere subject) {
		this.subject = subject;
	}

	public Salle getRoom() {
		return room;
	}

	public void setRoom(Salle room) {
		this.room = room;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

}
