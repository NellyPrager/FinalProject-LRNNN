package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Formateur;
import factoryProject.Model.Gestionnaire;
import factoryProject.Model.RessourceHumaine;
import factoryProject.Model.Stagiaire;
import factoryProject.Model.Technicien;

@Repository
public interface RepositoryRessourceHumaine extends JpaRepository<RessourceHumaine, Long>{
//	List<Formateur> findAllFormateur() ;
//	List<Stagiaire> findAllStagiaire() ;
//	List<Technicien> findAllTechnicien() ;
//	List<Gestionnaire> findAllGestionnaire() ;
	
}
