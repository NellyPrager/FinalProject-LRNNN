package factoryProject.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "human_resources")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class RessourceHumaine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(JsonViews.Common.class)
	private Long id;

	@Column(name = "surname")
	@JsonView(JsonViews.Common.class)
	private String surname;

	@Column(name = "name")
	@JsonView(JsonViews.Common.class)
	private String name;

	@Embedded
	@JsonView(JsonViews.Common.class)
	private Adresse adress;
	@Embedded
	@JsonView(JsonViews.Common.class)
	private Coordonnee contact;

	@Enumerated(EnumType.STRING)
	@JsonView(JsonViews.Common.class)
	private TypeUtilisateur userType;

	@Version
	private int version;

	public RessourceHumaine() {
	}

	public RessourceHumaine(String surname, String name, Adresse adress, Coordonnee contact, TypeUtilisateur userType) {
		super();
		this.surname = surname;
		this.name = name;
		this.adress = adress;
		this.contact = contact;
		this.userType = userType;
	}

	public TypeUtilisateur getUserType() {
		return userType;
	}

	public void setUserType(TypeUtilisateur userType) {
		this.userType = userType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Adresse getAdress() {
		return adress;
	}

	public void setAdress(Adresse adress) {
		this.adress = adress;
	}

	public Coordonnee getContact() {
		return contact;
	}

	public void setContact(Coordonnee contact) {
		this.contact = contact;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "RessourceHumaine [id=" + id + ", surname=" + surname + ", name=" + name + ", adress=" + adress
				+ ", contact=" + contact + ", version=" + version + "]";
	}

}
