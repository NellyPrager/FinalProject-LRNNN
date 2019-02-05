package factoryProject.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "subjects")
public class Matiere {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Version
	private int version;

	@Column(name = "title")
	private String title;

	@Column(name = "duration")
	private int duration;

	@Column(name = "objectives")
	private ArrayList<String> objectives;

	@Column(name = "prerequisites")
	private ArrayList<String> prerequisites;

	@Column(name = "content")
	private String content;

	@Column(name = "level")
	@Enumerated(EnumType.STRING)
	private Niveau level;

	@OneToMany(mappedBy = "matiere")
	private List<MatiereFormateur> formateurs = new ArrayList<>();
	
	public Matiere() {
		super();
	}

	public Matiere(int version, String title, int duration, ArrayList<String> objectives,
			ArrayList<String> prerequisites, String content, Niveau level) {
		super();
		this.version = version;
		this.title = title;
		this.duration = duration;
		this.objectives = objectives;
		this.prerequisites = prerequisites;
		this.content = content;
		this.level = level;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ArrayList<String> getObjectives() {
		return objectives;
	}

	public void setObjectives(ArrayList<String> objectives) {
		this.objectives = objectives;
	}

	public ArrayList<String> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(ArrayList<String> prerequisites) {
		this.prerequisites = prerequisites;
	}

	public String getContent() {
		return content;
	}

	public void Objectives(String content) {
		this.content = content;
	}

	public Niveau getLevel() {
		return level;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setLevel(Niveau level) {
		this.level = level;
	}

}
