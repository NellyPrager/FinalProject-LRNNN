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
public class Formateur extends RessourceHumaine{
	@Column(name="password")
	private String motDePasse;
	
	@Column(name="username")
	private String username;
	
	@OneToMany(mappedBy = "formateur")
	private List<MatiereFormateur> matieres = new ArrayList<>();
	
	@OneToMany(mappedBy = "trainer")
	private List <Creneau> creneau;
	
	@OneToMany(mappedBy="intern")
	private List <Stagiaire> intern;
	
	@OneToOne(mappedBy= "trainer")
	private Module module;

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
public Formateur() {}
}
