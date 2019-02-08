package factoryProject.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Administrateur;
import factoryProject.Model.Formateur;
import factoryProject.Model.Gestionnaire;
import factoryProject.Model.RessourceHumaine;
import factoryProject.Model.Technicien;

@Repository
public interface RepositoryLogin extends JpaRepository<RessourceHumaine, Long>{

	@Query("select h from RessourceHumaine h where h.class='technicien' and h.username=:username")
	Optional<Technicien> findTechnicienByUsername(@Param("username") String username);
	
	@Query("select h from RessourceHumaine h where h.class='administrateur' and h.username=:username")
	Optional<Administrateur> findAdministrateurByUsername(@Param("username") String username);
	
	@Query("select h from RessourceHumaine h where h.class='formateur' and h.username=:username")
	Optional<Formateur> findFormateurByUsername(@Param("username") String username);
	
	@Query("select h from RessourceHumaine h where h.class='gestionnaire' and h.username=:username")
	Optional<Gestionnaire> findGestionnaireByUsername(@Param("username") String username);
	
//	@Query("select h from RessourceHumaine h where h.username=:username")
//	Optional<RessourceHumaine> findByUsername(@Param("username") String username);
}
