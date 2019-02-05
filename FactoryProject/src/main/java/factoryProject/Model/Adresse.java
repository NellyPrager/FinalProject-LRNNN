package factoryProject.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Adresse {

	@Column(name = "number")
	private int number;

	@Column(name = "street")
	private String street;

	@Column(name = "zipCode")
	private String zipCode;

	@Column(name = "city")
	private String city;

	@Column(name = "country")
	private String country;

	public Adresse() {
		super();
	}

	public Adresse(int number, String street, String zipCode, String city, String country) {
		super();
		this.number = number;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
