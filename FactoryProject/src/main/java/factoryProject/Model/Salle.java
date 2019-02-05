package factoryProject.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("room")
public class Salle extends Materiel{
	@Version
	private int version;
	@Column
	@JsonView(JsonViews.Common.class)
	private int capacity;
	@OneToOne
	@JoinColumn(name = "videoProjector_code")
	@JsonView(JsonViews.Common.class)
	private VideoProjecteur videoProjector;
	
	public Salle() {
		super();
	}

	public Salle(int version, int capacity) {
		super();
		this.version = version;
		this.capacity = capacity;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	

}
