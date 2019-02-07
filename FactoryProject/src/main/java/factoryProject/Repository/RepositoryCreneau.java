package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Creneau;

@Repository
public interface RepositoryCreneau extends JpaRepository<Creneau, Long>{
	
	@Query("select c from Creneau c where c.trainer.id=:id")
	List<Creneau> findAllCreneauByFormateur(@Param("id") Long id);
	
//	@Query("select c from Creneau c where c.trainer='2'")
//	List<Creneau> findAllCreneauByFormateurTest();
	
	//@Query("select c from Creneau c, RessourceHumaine h where h.class='formateur' and c.trainer=:id and h.id=:id")
}
