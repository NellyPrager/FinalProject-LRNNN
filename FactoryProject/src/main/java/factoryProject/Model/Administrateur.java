package factoryProject.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("administrateur")
public class Administrateur extends RessourceHumaine  {
	@Column(name="password")
	private String motDePasse;
}
