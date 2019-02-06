package factoryProject.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trainer_subject")
public class MatiereFormateur {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "trainer_id")
	private Formateur formateur;

	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Matiere matiere;

	public MatiereFormateur() {
		super();
	}

	public MatiereFormateur(Formateur formateur, Matiere matiere) {
		super();
		this.formateur = formateur;
		this.matiere = matiere;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
