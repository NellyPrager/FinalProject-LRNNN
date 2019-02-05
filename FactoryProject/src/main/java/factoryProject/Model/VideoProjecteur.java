package factoryProject.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("video_projector")
public class VideoProjecteur {
	@Column
	@JsonView(JsonViews.Common.class)
	@OneToOne
	private Salle room;

}
