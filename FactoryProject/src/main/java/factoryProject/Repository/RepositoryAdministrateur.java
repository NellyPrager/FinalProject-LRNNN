package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Administrateur;
import factoryProject.Model.RessourceHumaine;


@Repository
public interface RepositoryAdministrateur extends JpaRepository<RessourceHumaine, Long> {

	@Query("select h from RessourceHumaine h where h.class='administrateur'")
	List<Administrateur> findAllAdministrateur();
}
