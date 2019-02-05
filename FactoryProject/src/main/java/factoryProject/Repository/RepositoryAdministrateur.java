package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Administrateur;


@Repository
public interface RepositoryAdministrateur extends JpaRepository<Administrateur, Long> {

	@Query("select distinct administrateur from human_resources")
	List<Administrateur>findAllAdministrateur();
}
