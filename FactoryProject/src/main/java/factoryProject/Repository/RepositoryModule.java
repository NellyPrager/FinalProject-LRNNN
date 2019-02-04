package factoryProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import factoryProject.Model.ModuleFormation;

@Repository
public interface RepositoryModule extends JpaRepository<ModuleFormation, Long> {

}
