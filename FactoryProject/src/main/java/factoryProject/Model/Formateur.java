package factoryProject.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("formateur")
public class Formateur extends RessourceHumaine {
	@Column(name = "password")
	@JsonView(JsonViews.Common.class)
	private String motDePasse;

	@Column(name = "username")
	@JsonView(JsonViews.Common.class)
	private String username;

	@OneToMany(mappedBy = "formateur")
	private List<MatiereFormateur> matieres = new ArrayList<>();

	@OneToMany(mappedBy = "trainer")
	private List<Creneau> creneau;

	@OneToMany(mappedBy = "trainer")
	private List<Stagiaire> intern;

	@OneToOne(mappedBy = "trainer")
	private Module module;

	@Column(name = "availability")
	@JsonView(JsonViews.Common.class)
	private boolean availability;

	@Enumerated(EnumType.STRING)
	@JsonView(JsonViews.FormateurWithSubject.class)
	private Competences skills;

	public Formateur() {
	}

	public Formateur(String motDePasse, String username, List<MatiereFormateur> matieres, List<Creneau> creneau,
			List<Stagiaire> intern, Module module, boolean availability, Competences skills) {
		super();
		this.motDePasse = motDePasse;
		this.username = username;
		this.matieres = matieres;
		this.creneau = creneau;
		this.intern = intern;
		this.module = module;
		this.availability = availability;
		this.skills = skills;
	}

	public boolean getAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Competences getSkills() {
		return skills;
	}

	public void setSkills(Competences skills) {
		this.skills = skills;
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
