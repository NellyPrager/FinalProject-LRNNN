package LRNNN.finalProject.FactoryProject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import factoryProject.Model.Formation;
import factoryProject.Model.Module;
import factoryProject.Model.Stagiaire;
import factoryProject.Repository.RepositoryFormation;
import factoryProject.controller.FormationController;

@SpringBootApplication
public class FactoryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryProjectApplication.class, args);
		//testFormation();
	}

	static void testFormation() {
		RepositoryFormation repo;
		Module m1=new Module();
		List<Module> modules = new ArrayList<>();
		modules.add(m1);
		Stagiaire s1=new Stagiaire();
		List<Stagiaire> stagiaires = new ArrayList<>();
		stagiaires.add(s1);
		
		Formation for1 = new Formation(modules, stagiaires);
		
	}
}

