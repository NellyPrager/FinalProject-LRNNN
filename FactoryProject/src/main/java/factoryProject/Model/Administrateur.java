package factoryProject.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("administrateur")
public class Administrateur extends RessourceHumaine {
	@Column(name = "password")
	private String motDePasse;
	@Column(name = "username")
	private String username;

	public Administrateur() {
		super();
	}

	public Administrateur(String motDePasse, String username) {
		super();
		this.motDePasse = motDePasse;
		this.username = username;
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

}
