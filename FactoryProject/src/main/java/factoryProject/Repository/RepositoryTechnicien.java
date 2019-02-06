package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import factoryProject.Model.RessourceHumaine;
import factoryProject.Model.Technicien;

@Repository
public interface RepositoryTechnicien extends JpaRepository<RessourceHumaine, Long>{

	@Query("select h from RessourceHumaine h where h.class='technicien'")
	List<Technicien>findAllTechnicien();

	
}

