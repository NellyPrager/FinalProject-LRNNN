package factoryProject.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("technicien")
public class Technicien extends RessourceHumaine {

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String motDePasse;

	public Technicien(String username, String motDePasse) {
		super();
		this.username = username;
		this.motDePasse = motDePasse;
	}

	public Technicien() {
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
