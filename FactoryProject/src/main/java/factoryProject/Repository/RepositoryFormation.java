package factoryProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Formation;

@Repository
public interface RepositoryFormation extends JpaRepository<Formation, Long>{

}
