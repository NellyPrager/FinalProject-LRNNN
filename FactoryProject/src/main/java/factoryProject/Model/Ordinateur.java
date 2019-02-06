package factoryProject.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("computer")
public class Ordinateur extends Materiel {

	@JsonView(JsonViews.Common.class)
	@Column
	private String processor;
	@Column(name = "ram")
	@JsonView(JsonViews.Common.class)
	private int ram;
	@Column(name = "hard_disk")
	@JsonView(JsonViews.Common.class)
	private int hardDisk;

	@Column(name = "year_of_purchase")
	@Temporal(TemporalType.DATE)
	@JsonView(JsonViews.Common.class)
	private Date year;

	@OneToOne
	@JoinColumn(name = "intern_id")
	@JsonView(JsonViews.ComputerWithIntern.class)
	private Stagiaire intern;

	public Ordinateur() {
		super();
	}

	public Ordinateur(String processor, int ram, int hardDisk, Date year, Stagiaire intern) {
		super();
		this.processor = processor;
		this.ram = ram;
		this.hardDisk = hardDisk;
		this.year = year;
		this.intern = intern;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}


	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getHardDisk() {
		return hardDisk;
	}

	public void setHardDisk(int hardDisk) {
		this.hardDisk = hardDisk;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public Stagiaire getIntern() {
		return intern;
	}

	public void setIntern(Stagiaire intern) {
		this.intern = intern;
	}

}
