package factoryProject.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "formation")
public class Formation {

	@Id
	@GeneratedValue
	@JsonView(JsonViews.Common.class)
	private Long id;
	@Version
	private int version;
	@OneToMany(mappedBy = "formation")
	@JsonView(JsonViews.Common.class)
	private List<Module> modules = new ArrayList<>();
	@OneToMany(mappedBy = "formation")
	@JsonView(JsonViews.FormationWithInterns.class)
	private List<Stagiaire> interns = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	private TypeFormation formationType;

	public Formation() {
		super();
	}

	public Formation(List<Module> modules, List<Stagiaire> interns) {
		super();
		this.modules = modules;
		this.interns = interns;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Stagiaire> getInterns() {
		return interns;
	}

	public void setInterns(List<Stagiaire> interns) {
		this.interns = interns;
	}

}
