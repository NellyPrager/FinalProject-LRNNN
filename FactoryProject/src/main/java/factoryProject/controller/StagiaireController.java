package factoryProject.controller;


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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import factoryProject.Model.JsonViews;
import factoryProject.Model.RessourceHumaine;
import factoryProject.Model.Stagiaire;
import factoryProject.Repository.RepositoryStagiaire;

@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/rest/personne/stagiaire")
@RestController
public class StagiaireController {

	@Autowired
	RepositoryStagiaire stagiaireRepository;

	@GetMapping(path = { "", "/" })
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Stagiaire>> findAll() {
		return new ResponseEntity<>(stagiaireRepository.findAllStagiaire(), HttpStatus.OK);
	}

	@PostMapping(path = { "", "/" })
	public ResponseEntity<Void> createStagiaire(@Valid @RequestBody Stagiaire stagiaire, BindingResult br,
			UriComponentsBuilder uCB) {
		ResponseEntity<Void> response = null;
		if (br.hasErrors()) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			stagiaireRepository.save(stagiaire);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/stagiaire/{id}").buildAndExpand(stagiaire.getId()).toUri());
			response = new ResponseEntity<>(header, HttpStatus.CREATED);
		}
		return response;
	}

	@GetMapping(value = "/{id}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Stagiaire> findById(@PathVariable(name = "id") Long id) {
		Optional<RessourceHumaine> opt = stagiaireRepository.findById(id);
		ResponseEntity<Stagiaire> response = null;
		if (opt.isPresent()) {
			response = new ResponseEntity<Stagiaire>((Stagiaire)opt.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping(path = { "", "/" })
	public ResponseEntity<Stagiaire> update(@Valid @RequestBody Stagiaire stagiaire, BindingResult br) {
		ResponseEntity<Stagiaire> response = null;

		if (br.hasErrors() || stagiaire.getId() == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} else {
			Optional<RessourceHumaine> opt = stagiaireRepository.findById(stagiaire.getId());
			if (opt.isPresent()) {
				Stagiaire stagiaireEnBase = (Stagiaire)opt.get();
				stagiaireEnBase.setName(stagiaire.getName());
				stagiaireEnBase.setSurname(stagiaire.getSurname());
				stagiaireEnBase.setAdress(stagiaire.getAdress());
				stagiaireRepository.save(stagiaireEnBase);
				response = new ResponseEntity<Stagiaire>(stagiaireEnBase, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			}
		}
		return response;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
		Optional<RessourceHumaine> opt = stagiaireRepository.findById(id);
		ResponseEntity<Void> response = null;
		if (opt.isPresent()) {
			stagiaireRepository.deleteById(id);
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}