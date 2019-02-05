package factoryProject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import factoryProject.Model.Formation;
import factoryProject.Model.Module;
import factoryProject.Model.Stagiaire;
import factoryProject.Repository.RepositoryFormation;

@SpringBootApplication
public class FactoryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryProjectApplication.class, args);
		//testFormation();
	}


}

