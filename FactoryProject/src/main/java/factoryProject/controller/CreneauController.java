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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import factoryProject.Model.Creneau;
import factoryProject.Model.JsonViews;
import factoryProject.Repository.RepositoryCreneau;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/creneau")
@RestController
public class CreneauController {

	@Autowired
	RepositoryCreneau repoCreneau;
	
	@GetMapping(path = { "", "/" })
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Creneau>> findAll() {
		return new ResponseEntity<>(repoCreneau.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = { "formateur", "/formateur" })
	@JsonView(JsonViews.CreneauWithFormateur.class)
	public ResponseEntity<List<Creneau>> findAllCreneauByFormateur(@RequestParam(name = "id") Long id) {
		return new ResponseEntity<>(repoCreneau.findAllCreneauByFormateur(id), HttpStatus.OK);
	}

	@PostMapping(path = { "", "/" })
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Void> createCreneau(@Valid @RequestBody Creneau creneau, BindingResult br,
			UriComponentsBuilder uCB) {
		ResponseEntity<Void> response = null;
		if (br.hasErrors()) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			repoCreneau.save(creneau);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/creneau/{id}").buildAndExpand(creneau.getId()).toUri());
			response = new ResponseEntity<>(header, HttpStatus.CREATED);
		}
		return response;
	}

	@GetMapping(value = "/{id}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Creneau> findById(@PathVariable(name = "id") Long id) {
		Optional<Creneau> opt = repoCreneau.findById(id);
		ResponseEntity<Creneau> response = null;
		if (opt.isPresent()) {
			response = new ResponseEntity<Creneau>((Creneau) opt.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping(path = { "", "/" })
	public ResponseEntity<Creneau> update(@Valid @RequestBody Creneau creneau, BindingResult br) {
		ResponseEntity<Creneau> response = null;

		if (br.hasErrors() || creneau.getId() == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} else {
			Optional<Creneau> opt = repoCreneau.findById(creneau.getId());
			if (opt.isPresent()) {
				Creneau creneauEnBase = (Creneau) opt.get();
				creneauEnBase.setDate(creneau.getDate());
				creneauEnBase.setAvailability(creneau.getAvailability());
				creneauEnBase.setTrainer(creneau.getTrainer());
				repoCreneau.save(creneauEnBase);
				response = new ResponseEntity<Creneau>(creneauEnBase, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			}
		}
		return response;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
		Optional<Creneau> opt = repoCreneau.findById(id);
		ResponseEntity<Void> response = null;
		if (opt.isPresent()) {
			repoCreneau.deleteById(id);
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}
