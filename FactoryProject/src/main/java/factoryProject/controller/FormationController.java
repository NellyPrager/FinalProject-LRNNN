package factoryProject.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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

import factoryProject.Model.Formation;
import factoryProject.Model.JsonViews;
import factoryProject.Repository.RepositoryFormation;

@CrossOrigin(origins = "{*}")
@RestController
@ComponentScan
@RequestMapping("/formation")
//module de gestion des planning les mecs, siiiii sii
public class FormationController {

	@Autowired
	private RepositoryFormation repoFormation;
	
	@GetMapping(path = { "", "/" })
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Formation>> findAll() {
		return new ResponseEntity<>(repoFormation.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = { "", "/interns" })
	@JsonView(JsonViews.FormationWithInterns.class)
	public ResponseEntity<List<Formation>> findAllFormationWithInterns() {
		return new ResponseEntity<>(repoFormation.findAllFormationWithInterns(), HttpStatus.OK);
	}

	@PostMapping(path = { "", "/" })
	public ResponseEntity<Void> createFormation(@Valid @RequestBody Formation formation, BindingResult br,
			UriComponentsBuilder uCB) {
		ResponseEntity<Void> response = null;
		if (br.hasErrors()) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			repoFormation.save(formation);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/formation/{id}").buildAndExpand(formation.getId()).toUri());
			response = new ResponseEntity<>(header, HttpStatus.CREATED);
		}
		return response;
	}

	@GetMapping(value = "/{id}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Formation> findById(@PathVariable(name = "id") Long id) {
		Optional<Formation> opt = repoFormation.findById(id);
		ResponseEntity<Formation> response = null;
		if (opt.isPresent()) {
			response = new ResponseEntity<Formation>((Formation)opt.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping(path = { "", "/" })
	public ResponseEntity<Formation> update(@Valid @RequestBody Formation formation, BindingResult br) {
		ResponseEntity<Formation> response = null;

		if (br.hasErrors() || formation.getId() == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} else {
			Optional<Formation> opt = repoFormation.findById(formation.getId());
			if (opt.isPresent()) {
				Formation formationInBase = (Formation)opt.get();
				formationInBase.setId(formation.getId());
				formationInBase.setInterns(formation.getInterns());;
				formationInBase.setModules(formation.getModules());;
				repoFormation.save(formationInBase);
				response = new ResponseEntity<Formation>(formationInBase, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			}
		}
		return response;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
		Optional<Formation> opt = repoFormation.findById(id);
		ResponseEntity<Void> response = null;
		if (opt.isPresent()) {
			repoFormation.deleteById(id);
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}
