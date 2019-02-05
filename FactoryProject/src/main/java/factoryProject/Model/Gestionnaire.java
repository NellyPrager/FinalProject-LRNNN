package factoryProject.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("gestionnaire")
public class Gestionnaire extends RessourceHumaine{
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String motDePasse;

}
