package factoryProject.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("video_projector")
public class VideoProjecteur extends Materiel{
	@Column
	@JsonView(JsonViews.Common.class)
	@OneToOne
	private Salle room;
	

	public VideoProjecteur() {
		super();
	}

	public Salle getRoom() {
		return room;
	}

	public void setRoom(Salle room) {
		this.room = room;
	}

	public VideoProjecteur(Salle room) {
		super();
		this.room = room;
	}

	
}
