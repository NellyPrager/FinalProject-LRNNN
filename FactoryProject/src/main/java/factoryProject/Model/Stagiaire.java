package factoryProject.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("stagiaire")
public class Stagiaire extends RessourceHumaine {

	@ManyToOne
	@JoinColumn(name = "trainer_id")
	private Formateur trainer;

	@OneToOne
	@JoinColumn(name = "computer_code")
	private Ordinateur computer;
	
	@ManyToOne
	@JoinColumn(name="formation_id")
	private Formation formation;

	public Stagiaire() {
	}

	public Stagiaire(Formateur trainer, Ordinateur computer, Formation formation) {
		super();
		this.trainer = trainer;
		this.computer = computer;
		this.formation = formation;
	}

	public Formateur getTrainer() {
		return trainer;
	}

	public void setTrainer(Formateur trainer) {
		this.trainer = trainer;
	}

	public Ordinateur getComputer() {
		return computer;
	}

	public void setComputer(Ordinateur computer) {
		this.computer = computer;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	
}
