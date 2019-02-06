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

import factoryProject.Model.JsonViews;
import factoryProject.Model.RessourceHumaine;
import factoryProject.Model.Technicien;
import factoryProject.Repository.RepositoryTechnicien;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/technicien")
@RestController
public class TechnicienController {

			@Autowired
			RepositoryTechnicien technicienRepository;

			@GetMapping(path = { "", "/" })
			@JsonView(JsonViews.Common.class)
			public ResponseEntity<List<Technicien>> findAll() {
				return new ResponseEntity<>(technicienRepository.findAllTechnicien(), HttpStatus.OK);
			}

			@PostMapping(path = { "", "/" })
			public ResponseEntity<Void> createTechnicien(@Valid @RequestBody Technicien technicien, BindingResult br,
					UriComponentsBuilder uCB) {
				ResponseEntity<Void> response = null;
				if (br.hasErrors()) {
					response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				} else {
					technicienRepository.save(technicien);
					HttpHeaders header = new HttpHeaders();
					header.setLocation(uCB.path("/rest/formateur/{id}").buildAndExpand(technicien.getId()).toUri());
					response = new ResponseEntity<>(header, HttpStatus.CREATED);
				}
				return response;
			}

			@GetMapping(value = "/{id}")
			@JsonView(JsonViews.Common.class)
			public ResponseEntity<Technicien> findById(@PathVariable(name = "id") Long id) {
				Optional<RessourceHumaine> opt = technicienRepository.findById(id);
				ResponseEntity<Technicien> response = null;
				if (opt.isPresent()) {
					response = new ResponseEntity<Technicien>((Technicien) opt.get(), HttpStatus.OK);
				} else {
					response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return response;
			}

			@JsonView(JsonViews.Common.class)
			@PutMapping(path = { "", "/" })
			public ResponseEntity<Technicien> update(@Valid @RequestBody Technicien technicien, BindingResult br) {
				ResponseEntity<Technicien> response = null;

				if (br.hasErrors() || technicien.getId() == null) {
					response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
				} else {
					Optional<RessourceHumaine> opt = technicienRepository.findById(technicien.getId());
					if (opt.isPresent()) {
						Technicien technicienEnBase = (Technicien) opt.get();
						technicienEnBase.setName(technicien.getName());
						technicienEnBase.setSurname(technicien.getSurname());
						technicienEnBase.setAdress(technicien.getAdress());
						technicienRepository.save(technicienEnBase);
						response = new ResponseEntity<Technicien>(technicienEnBase, HttpStatus.OK);
					} else {
						response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
					}
				}
				return response;
			}

			@DeleteMapping(value = "/{id}")
			public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
				Optional<RessourceHumaine> opt = technicienRepository.findById(id);
				ResponseEntity<Void> response = null;
				if (opt.isPresent()) {
					technicienRepository.deleteById(id);
					response = new ResponseEntity<>(HttpStatus.OK);
				} else {
					response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return response;
			}
		}
	
	

