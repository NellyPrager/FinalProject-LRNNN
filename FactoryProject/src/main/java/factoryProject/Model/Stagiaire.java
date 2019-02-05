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
@Table(name="interns")
public class Stagiaire extends RessourceHumaine{

@Column(name="password")
private String motDePasse;

@Column(name="username")
private String username;

@ManyToOne
@JoinColumn(name="trainer")
private Formateur trainer;

@OneToOne
@JoinColumn(name="computer")
private Ordinateur ordinateur;

public String getMotDePasse() {
	return motDePasse;
}

public void setMotDePasse(String motDePasse) {
	this.motDePasse = motDePasse;
}

public Stagiaire(String motDePasse) {
	super();
	this.motDePasse = motDePasse;
}

public Stagiaire() {}
}
