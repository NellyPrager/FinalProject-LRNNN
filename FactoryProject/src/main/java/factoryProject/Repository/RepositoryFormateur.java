package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import factoryProject.Model.Formateur;
import factoryProject.Model.RessourceHumaine;

	@Repository
	public interface RepositoryFormateur extends JpaRepository<RessourceHumaine, Long>{

		@Query("select distinct formateur from human_resources")
		List<Formateur>findAllFormateur();

		
	}

