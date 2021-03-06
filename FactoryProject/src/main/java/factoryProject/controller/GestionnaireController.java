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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import factoryProject.Model.Gestionnaire;
import factoryProject.Model.JsonViews;
import factoryProject.Model.RessourceHumaine;
import factoryProject.Repository.RepositoryGestionnaire;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/gestionnaire")
@RestController
public class GestionnaireController {
		@Autowired
		RepositoryGestionnaire gestionnaireRepository;

		@GetMapping(path = { "", "/" })
		@JsonView(JsonViews.Common.class)
		public ResponseEntity<List<Gestionnaire>> findAll() {
			return new ResponseEntity<>(gestionnaireRepository.findAllGestionnaire(), HttpStatus.OK);
		}

		@PostMapping(path = {"/create" })
		public ResponseEntity<Void> createGestionnaire(@Valid @RequestBody Gestionnaire gestionnaire, BindingResult br,
				UriComponentsBuilder uCB) {
			ResponseEntity<Void> response = null;
			if (br.hasErrors()) {
				response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				gestionnaireRepository.save(gestionnaire);
				HttpHeaders header = new HttpHeaders();
				header.setLocation(uCB.path("/rest/formateur/{id}").buildAndExpand(gestionnaire.getId()).toUri());
				response = new ResponseEntity<>(header, HttpStatus.CREATED);
			}
			return response;
		}

		@GetMapping(value = "/{id}")
		@JsonView(JsonViews.Common.class)
		public ResponseEntity<Gestionnaire> findById(@PathVariable(name = "id") Long id) {
			Optional<RessourceHumaine> opt = gestionnaireRepository.findById(id);
			ResponseEntity<Gestionnaire> response = null;
			if (opt.isPresent()) {
				response = new ResponseEntity<Gestionnaire>((Gestionnaire) opt.get(), HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return response;
		}

		@JsonView(JsonViews.Common.class)
		@PutMapping(path = { "", "/" })
		public ResponseEntity<Gestionnaire> update(@Valid @RequestBody Gestionnaire gestionnaire, BindingResult br) {
			ResponseEntity<Gestionnaire> response = null;

			if (br.hasErrors() || gestionnaire.getId() == null) {
				response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			} else {
				Optional<RessourceHumaine> opt = gestionnaireRepository.findById(gestionnaire.getId());
				if (opt.isPresent()) {
					Gestionnaire gestionnaireEnBase = (Gestionnaire) opt.get();
					gestionnaireEnBase.setName(gestionnaire.getName());
					gestionnaireEnBase.setSurname(gestionnaire.getSurname());
					gestionnaireEnBase.setAdress(gestionnaire.getAdress());
					gestionnaireRepository.save(gestionnaireEnBase);
					response = new ResponseEntity<Gestionnaire>(gestionnaireEnBase, HttpStatus.OK);
				} else {
					response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
				}
			}
			return response;
		}

		@DeleteMapping(value = "/{id}")
		public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
			Optional<RessourceHumaine> opt = gestionnaireRepository.findById(id);
			ResponseEntity<Void> response = null;
			if (opt.isPresent()) {
				gestionnaireRepository.deleteById(id);
				response = new ResponseEntity<>(HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return response;
		}
	}


