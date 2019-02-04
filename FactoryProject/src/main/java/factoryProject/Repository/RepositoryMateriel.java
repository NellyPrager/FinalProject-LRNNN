package factoryProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Materiel;

@Repository
public interface RepositoryMateriel extends JpaRepository<Materiel, String> {
//	List<Ordinateur> findAllFormateur() ;
//	List<Salle> findAllStagiaire() ;
//	List<VideoProjecteur> findAllVideoProjecteur() ;

}
