package factoryProject.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class Coordonnee {

	@Column(name = "email")
	@JsonView(JsonViews.Common.class)
	private String email;

	@Column(name = "phone")
	@JsonView(JsonViews.Common.class)
	private Long phone;

	public Coordonnee() {
		super();
	}

	public Coordonnee(String email, Long phone) {
		super();
		this.email = email;
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

}
