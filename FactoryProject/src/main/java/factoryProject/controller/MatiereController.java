package factoryProject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import factoryProject.Model.Matiere;
import factoryProject.Repository.RepositoryMatiere;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class MatiereController {

	@Autowired
	RepositoryMatiere repoMat;

	@GetMapping("/subjects")
	public List<Matiere> getAllSubjects() {
		System.out.println("Get all Subjects...");

		List<Matiere> subjects = new ArrayList<>();
		repoMat.findAll().forEach(subjects::add);

		return subjects;
	}

	@PostMapping("/subjects/create")
	public Matiere postSubject(@RequestBody Matiere matiere) {

		Matiere subject = repoMat.save(matiere);
		return subject;
	}

	@DeleteMapping("/subjects/{id}")
	public ResponseEntity<String> deleteSubject(@PathVariable("id") long id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		repoMat.deleteById(id);

		return new ResponseEntity<>("Subject has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/subjects/delete")
	public ResponseEntity<String> deleteAllSubjects() {
		System.out.println("Delete All Subjects...");

		repoMat.deleteAll();

		return new ResponseEntity<>("All Ssubjects have been deleted!", HttpStatus.OK);
	}

	@PutMapping("/subjects/{id}")
	public ResponseEntity<Matiere> updateCustomer(@PathVariable("id") long id, @RequestBody Matiere matiere) {
		System.out.println("Update Subject with ID = " + id + "...");

		Optional<Matiere> subjectData = repoMat.findById(id);

		if (subjectData.isPresent()) {
			Matiere _matiere= subjectData.get();
			_matiere.setContent(matiere.getContent());
			_matiere.setDuration(matiere.getDuration());
			_matiere.setTitle(matiere.getTitle());
			_matiere.setObjectives(matiere.getObjectives());
			_matiere.setPrerequisites(matiere.getPrerequisites());
			_matiere.setLevel(matiere.getLevel());
			
			return new ResponseEntity<>(repoMat.save(_matiere), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
