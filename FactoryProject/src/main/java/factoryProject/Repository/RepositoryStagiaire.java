package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Formateur;
import factoryProject.Model.Gestionnaire;
import factoryProject.Model.RessourceHumaine;
import factoryProject.Model.Stagiaire;
import factoryProject.Model.Technicien;

@Repository
public interface RepositoryStagiaire extends JpaRepository<RessourceHumaine, Long>{

	@Query("select h from RessourceHumaine h where h.class='stagiaire'")
	List<Stagiaire>findAllStagiaire();

	
}
