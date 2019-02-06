package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import factoryProject.Model.Materiel;

import factoryProject.Model.VideoProjecteur;

public interface RepositoryVideoProjecteur extends JpaRepository<Materiel, String> {
	@Query("select m from Materiel m where m.class='video_projector'")
	List<VideoProjecteur> findAllVideoProjector();

}
