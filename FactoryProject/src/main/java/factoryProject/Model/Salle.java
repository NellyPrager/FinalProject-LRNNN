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
public class Salle extends Materiel {
	@Column
	@JsonView(JsonViews.Common.class)
	private int capacity;
	@OneToOne
	@JoinColumn(name = "videoprojector_code")
	@JsonView(JsonViews.Common.class)
	private VideoProjecteur videoProjector;
	@OneToOne(mappedBy = "room")
	@JsonView(JsonViews.Common.class)
	private Module module;

	public Salle() {
		super();
	}

	public Salle(int capacity, VideoProjecteur videoProjector) {
		super();
		this.capacity = capacity;
		this.videoProjector = videoProjector;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public VideoProjecteur getVideoProjector() {
		return videoProjector;
	}

	public void setVideoProjector(VideoProjecteur videoProjector) {
		this.videoProjector = videoProjector;
	}

}
