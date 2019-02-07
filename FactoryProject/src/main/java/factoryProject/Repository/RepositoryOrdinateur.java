package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import factoryProject.Model.Materiel;
import factoryProject.Model.Ordinateur;

public interface RepositoryOrdinateur extends JpaRepository<Materiel, String>{
	
	@Query("select m from Materiel m where m.class='computer'")
	List<Ordinateur> findAllComputer() ;
	
	//@Query("select m, h from Materiel m, RessourceHumaine h where m.class='computer' and m.intern=:id")
	//@Query("select m, h.name, h.surname from Materiel m, RessourceHumaine h where m.class='computer' and m.intern is not null and h.class='stagiaire'")
	@Query("select m from Materiel m, RessourceHumaine h where m.class='computer' and m.intern is not null and h.class='stagiaire'")
	List<Ordinateur> findAllComputerWithInterns();
}
