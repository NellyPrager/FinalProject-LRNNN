package factoryProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import factoryProject.Model.MatiereFormateur;

@Repository
public interface RepositoryMatiereFormateur extends JpaRepository<MatiereFormateur, Long>{

}
