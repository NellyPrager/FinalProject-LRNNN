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
	private String password;

	public Technicien(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Technicien() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
