package factoryProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Formation;

@Repository
public interface RepositoryFormation extends JpaRepository<Formation, Long> {
	@Query("select distinct f from formation f")
	List<Formation> findAllFormationWithInterns();
}
