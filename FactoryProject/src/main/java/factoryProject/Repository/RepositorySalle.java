package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Materiel;
import factoryProject.Model.Salle;

@Repository

public interface RepositorySalle extends JpaRepository<Materiel, String> {
	@Query("select m from Materiel m where m.class='room'")
	List<Salle> findAllRoom() ;
//	List<VideoProjecteur> findAllVideoProjecteur() ;

}
