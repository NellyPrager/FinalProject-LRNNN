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
import factoryProject.Model.Materiel;
import factoryProject.Model.Ordinateur;
import factoryProject.Model.Salle;
import factoryProject.Repository.RepositoryOrdinateur;
import factoryProject.Repository.RepositorySalle;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/salle")
public class RessourceSalleController {
	@Autowired
	RepositorySalle repositoryRoom;
	
	@GetMapping(path = { "", "/" })
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Salle>> findAll() {
		return new ResponseEntity<>(repositoryRoom.findAllRoom(), HttpStatus.OK);
	}


	@PostMapping(path = { "", "/" })
	public ResponseEntity<Void> createComputer(@Valid @RequestBody Salle room, BindingResult br,
			UriComponentsBuilder uCB) {
		ResponseEntity<Void> response = null;
		if (br.hasErrors()) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			repositoryRoom.save(room);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/room/{code}").buildAndExpand(room.getCode()).toUri());
			response = new ResponseEntity<>(header, HttpStatus.CREATED);
		}
		return response;
	}

	@GetMapping(value = "/{code}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Salle> findById(@PathVariable(name = "code") String code) {
		Optional<Materiel> opt = repositoryRoom.findById(code);
		ResponseEntity<Salle> response = null;
		if (opt.isPresent()) {
			response = new ResponseEntity<Salle>((Salle)opt.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping(path = { "", "/" })
	public ResponseEntity<Salle> update(@Valid @RequestBody Salle room, BindingResult br) {
		ResponseEntity<Salle> response = null;

		if (br.hasErrors() || room.getCode() == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} else {
			Optional<Materiel> opt = repositoryRoom.findById(room.getCode());
			if (opt.isPresent()) {
				Salle salleEnBase = (Salle)opt.get();
				salleEnBase.setCode(room.getCode());
				salleEnBase.setCapacity(room.getCapacity());
				salleEnBase.setPrice(room.getPrice());
				repositoryRoom.save(salleEnBase);
				response = new ResponseEntity<Salle>(salleEnBase, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			}
		}
		return response;
	}

	@DeleteMapping(value = "/{code}")
	public ResponseEntity<Void> delete(@PathVariable(name = "code") String code) {
		Optional<Materiel> opt = repositoryRoom.findById(code);
		ResponseEntity<Void> response = null;
		if (opt.isPresent()) {
			repositoryRoom.deleteById(code);
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}


}
