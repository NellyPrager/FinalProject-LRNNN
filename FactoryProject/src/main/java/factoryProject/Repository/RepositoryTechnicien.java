package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import factoryProject.Model.RessourceHumaine;
import factoryProject.Model.Stagiaire;
import factoryProject.Model.Technicien;

@Repository
public interface RepositoryTechnicien extends JpaRepository<RessourceHumaine, Long>{

	@Query("select distinct technicien from technicians")
	List<Technicien>findAllTechnicien();

	
}

