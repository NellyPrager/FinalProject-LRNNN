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

import factoryProject.Model.Administrateur;
import factoryProject.Model.JsonViews;
import factoryProject.Model.RessourceHumaine;
import factoryProject.Repository.RepositoryAdministrateur;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/administrateur")
@RestController
public class AdministrateurController {
		@Autowired
		RepositoryAdministrateur administrateurRepository;

		@GetMapping(path = { "", "/" })
		@JsonView(JsonViews.Common.class)
		public ResponseEntity<List<Administrateur>> findAll() {
			return new ResponseEntity<>(administrateurRepository.findAllAdministrateur(), HttpStatus.OK);
		}

		@PostMapping(path = { "", "/" })
		public ResponseEntity<Void> createAdministrateur(@Valid @RequestBody Administrateur administrateur, BindingResult br,
				UriComponentsBuilder uCB) {
			ResponseEntity<Void> response = null;
			if (br.hasErrors()) {
				response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				administrateurRepository.save(administrateur);
				HttpHeaders header = new HttpHeaders();
				header.setLocation(uCB.path("/rest/administrateur/{id}").buildAndExpand(administrateur.getId()).toUri());
				response = new ResponseEntity<>(header, HttpStatus.CREATED);
			}
			return response;
		}

		@GetMapping(value = "/{id}")
		@JsonView(JsonViews.Common.class)
		public ResponseEntity<Administrateur> findById(@PathVariable(name = "id") Long id) {
			Optional<RessourceHumaine> opt = administrateurRepository.findById(id);
			ResponseEntity<Administrateur> response = null;
			if (opt.isPresent()) {
				response = new ResponseEntity<Administrateur>((Administrateur) opt.get(), HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return response;
		}

		@JsonView(JsonViews.Common.class)
		@PutMapping(path = { "", "/" })
		public ResponseEntity<Administrateur> update(@Valid @RequestBody Administrateur administrateur, BindingResult br) {
			ResponseEntity<Administrateur> response = null;

			if (br.hasErrors() || 	administrateur.getId() == null) {
				response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			} else {
				Optional<RessourceHumaine> opt = administrateurRepository.findById(administrateur.getId());
				if (opt.isPresent()) {
					Administrateur administrateurEnBase = (Administrateur) opt.get();
					administrateurEnBase.setName(administrateur.getName());
					administrateurEnBase.setSurname(administrateur.getSurname());
					administrateurEnBase.setAdress(administrateur.getAdress());
					administrateurRepository.save(administrateurEnBase);
					response = new ResponseEntity<Administrateur>(administrateurEnBase, HttpStatus.OK);
				} else {
					response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
				}
			}
			return response;
		}

		@DeleteMapping(value = "/{id}")
		public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
			Optional<RessourceHumaine> opt = administrateurRepository.findById(id);
			ResponseEntity<Void> response = null;
			if (opt.isPresent()) {
				administrateurRepository.deleteById(id);
				response = new ResponseEntity<>(HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return response;
		}
	}	
	
	

