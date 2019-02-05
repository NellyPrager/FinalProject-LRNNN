package factoryProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Creneau;

@Repository
public interface RepositoryCreneau extends JpaRepository<Creneau, Long>{

}
