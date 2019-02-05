package LRNNN.finalProject.FactoryProject;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import factoryProject.Model.Matiere;
import factoryProject.Model.Niveau;
import factoryProject.Repository.RepositoryMatiere;

@SpringBootApplication
public class FactoryProjectApplication implements CommandLineRunner {

	@Autowired
	private RepositoryMatiere repositoryMatiere;

	public static void main(String[] args) {
		SpringApplication.run(FactoryProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ArrayList<String> obj1 = new ArrayList<String>();
		obj1.add("osef");
		obj1.add("bla");
		obj1.add("Vert");

		ArrayList<String> prereq1 = new ArrayList<String>();
		prereq1.add("lala");
		prereq1.add("mimi");
		prereq1.add("fafa");

		Matiere java = new Matiere("Java", 5, obj1, prereq1, "on s'en bat les couilles", Niveau.avance);
	}
}
