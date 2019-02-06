package factoryProject.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("formateur")
public class Formateur extends RessourceHumaine {
	@Column(name = "password")
	private String motDePasse;

	@Column(name = "username")
	private String username;

	@OneToMany(mappedBy = "formateur")
	private List<MatiereFormateur> matieres = new ArrayList<>();

	@OneToMany(mappedBy = "trainer")
	private List<Creneau> creneau;

	@OneToMany(mappedBy = "trainer")
	private List<Stagiaire> intern;

	@OneToOne(mappedBy = "trainer")
	private Module module;

	public Formateur() {
	}

	public Formateur(String motDePasse, String username, List<MatiereFormateur> matieres, List<Creneau> creneau,
			List<Stagiaire> intern, Module module) {
		super();
		this.motDePasse = motDePasse;
		this.username = username;
		this.matieres = matieres;
		this.creneau = creneau;
		this.intern = intern;
		this.module = module;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<MatiereFormateur> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<MatiereFormateur> matieres) {
		this.matieres = matieres;
	}

	public List<Creneau> getCreneau() {
		return creneau;
	}

	public void setCreneau(List<Creneau> creneau) {
		this.creneau = creneau;
	}

	public List<Stagiaire> getIntern() {
		return intern;
	}

	public void setIntern(List<Stagiaire> intern) {
		this.intern = intern;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
