package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import factoryProject.Model.Materiel;
import factoryProject.Model.Ordinateur;

public interface RepositoryOrdinateur extends JpaRepository<Materiel, String>{
	
	@Query("select m from Materiel m where m.class='computer'")
	List<Ordinateur> findAllComputer() ;
	
	@Query("select m from Materiel m where m.class='computer'")
	List<Ordinateur> findAllComputerWithInterns();
}
