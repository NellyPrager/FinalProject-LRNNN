package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import factoryProject.Model.Materiel;
import factoryProject.Model.Ordinateur;

public interface RepositoryOrdinateur extends JpaRepository<Materiel, String>{
	List<Ordinateur> findAllComputerWithInterns();
	List<Ordinateur> findAllComputer() ;
	

}