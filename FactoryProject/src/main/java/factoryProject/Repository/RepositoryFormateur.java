package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Competences;
import factoryProject.Model.Formateur;
import factoryProject.Model.RessourceHumaine;

@Repository
public interface RepositoryFormateur extends JpaRepository<RessourceHumaine, Long> {

	@Query("select h from RessourceHumaine h where h.class='formateur'")
	List<Formateur> findAllFormateur();
	
	@Query("select h from RessourceHumaine h where h.class='formateur' and h.skills=:skill")
	List<Formateur> findAllFormateurBySubject(@Param("skill") Competences skill);

}
