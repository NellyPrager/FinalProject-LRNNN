package factoryProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Administrateur;

@Repository
public interface RepositoryAdministrateur extends JpaRepository<Administrateur, Long> {

}
