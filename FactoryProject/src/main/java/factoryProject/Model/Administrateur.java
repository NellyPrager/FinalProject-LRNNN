package factoryProject.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("administrateur")
@Table(name="admins")
public class Administrateur {
	@Column(name="password")
	private String motDePasse;
}
