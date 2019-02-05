package factoryProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Matiere;

@Repository(value="subject")
public interface RepositoryMatiere extends JpaRepository<Matiere, Long>{
}
