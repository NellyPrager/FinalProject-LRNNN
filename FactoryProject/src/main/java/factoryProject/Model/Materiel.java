package factoryProject.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "material_resources")
@DiscriminatorColumn(name = "material")
public abstract class Materiel {
	@Id
	private String code;
	@Version
	private int version;
	@Column(name = "price")
	private int price;
	@Column(name = "availability")
	private boolean availability;

	public Materiel() {
		super();
	}

	public Materiel(String code, int price, boolean availability) {
		super();
		this.code = code;
		this.price = price;
		this.availability = availability;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

}
