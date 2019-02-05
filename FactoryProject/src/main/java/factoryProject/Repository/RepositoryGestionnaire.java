package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Gestionnaire;
import factoryProject.Model.RessourceHumaine;

@Repository
public interface RepositoryGestionnaire extends JpaRepository<RessourceHumaine, Long>{

	@Query("select distinct gestionnaire from human_resources")
	List<Gestionnaire>findAllGestionnaire();

	
}


