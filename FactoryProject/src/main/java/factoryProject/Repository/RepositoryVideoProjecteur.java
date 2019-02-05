package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import factoryProject.Model.Matiere;
import factoryProject.Model.VideoProjecteur;

public interface RepositoryVideoProjecteur extends JpaRepository<Matiere, String>{
	List<VideoProjecteur> findAllVideoProjector() ;

}
