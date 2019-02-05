package factoryProject.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;


import factoryProject.Model.JsonViews;
import factoryProject.Model.Ordinateur;
import factoryProject.Repository.RepositoryOrdinateur;




@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/material")
public class RessourceOrdinateurController {
	
	@Autowired
	RepositoryOrdinateur repositoryComputer;
	
	@GetMapping(path = { "", "/" })
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Ordinateur>> findAll() {
		return new ResponseEntity<>(repositoryComputer.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = { "/intern" })
	@JsonView(JsonViews.ComputerWithIntern.class)
	public ResponseEntity<List<Ordinateur>> findAllWithInterns() {
		return new ResponseEntity<>(repositoryComputer.findAllComputerWithInterns(), HttpStatus.OK);
	}

	@PostMapping(path = { "", "/" })
	public ResponseEntity<Void> createComputer(@Valid @RequestBody Ordinateur computer, BindingResult br,
			UriComponentsBuilder uCB) {
		ResponseEntity<Void> response = null;
		if (br.hasErrors()) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			repositoryComputer.save(computer);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/computer/{code}").buildAndExpand(computer.getCode()).toUri());
			response = new ResponseEntity<>(header, HttpStatus.CREATED);
		}
		return response;
	}

	@GetMapping(value = "/{id}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Ordinateur> findById(@PathVariable(name = "code") String code) {
		Optional<Ordinateur> opt = repositoryComputer.findById(code);
		ResponseEntity<Ordinateur> response = null;
		if (opt.isPresent()) {
			response = new ResponseEntity<Salle>(opt.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping(path = { "", "/" })
	public ResponseEntity<Salle> update(@Valid @RequestBody Salle salle, BindingResult br) {
		ResponseEntity<Salle> response = null;

		if (br.hasErrors() || salle.getNumero() == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} else {
			Optional<Salle> opt = salleRepository.findById(salle.getNumero());
			if (opt.isPresent()) {
				Salle salleEnBase = opt.get();
				salleEnBase.setNom(salle.getNom());
				salleRepository.save(salleEnBase);
				response = new ResponseEntity<Salle>(salleEnBase, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			}
		}
		return response;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		Optional<Salle> opt = salleRepository.findById(id);
		ResponseEntity<Void> response = null;
		if (opt.isPresent()) {
			salleRepository.deleteById(id);
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

}
