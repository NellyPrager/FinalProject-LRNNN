package factoryProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import factoryProject.Model.Module;

@Repository
public interface RepositoryModule extends JpaRepository<Module, Long> {

}
