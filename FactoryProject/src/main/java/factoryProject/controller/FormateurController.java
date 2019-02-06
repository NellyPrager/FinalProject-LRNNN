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

import factoryProject.Model.Formateur;
import factoryProject.Model.JsonViews;
import factoryProject.Model.RessourceHumaine;
import factoryProject.Repository.RepositoryFormateur;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/formateur")
@RestController
public class FormateurController {

	@Autowired
	RepositoryFormateur formateurRepository;

	@GetMapping(path = { "", "/" })
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Formateur>> findAll() {
		return new ResponseEntity<>(formateurRepository.findAllFormateur(), HttpStatus.OK);
	}

	@PostMapping(path = { "", "/" })
	public ResponseEntity<Void> createFormateur(@Valid @RequestBody Formateur formateur, BindingResult br,
			UriComponentsBuilder uCB) {
		ResponseEntity<Void> response = null;
		if (br.hasErrors()) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			formateurRepository.save(formateur);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/formateur/{id}").buildAndExpand(formateur.getId()).toUri());
			response = new ResponseEntity<>(header, HttpStatus.CREATED);
		}
		return response;
	}

	@GetMapping(value = "/{id}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Formateur> findById(@PathVariable(name = "id") Long id) {
		Optional<RessourceHumaine> opt = formateurRepository.findById(id);
		ResponseEntity<Formateur> response = null;
		if (opt.isPresent()) {
			response = new ResponseEntity<Formateur>((Formateur) opt.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping(path = { "", "/" })
	public ResponseEntity<Formateur> update(@Valid @RequestBody Formateur formateur, BindingResult br) {
		ResponseEntity<Formateur> response = null;

		if (br.hasErrors() || formateur.getId() == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} else {
			Optional<RessourceHumaine> opt = formateurRepository.findById(formateur.getId());
			if (opt.isPresent()) {
				Formateur formateurEnBase = (Formateur) opt.get();
				formateurEnBase.setName(formateur.getName());
				formateurEnBase.setSurname(formateur.getSurname());
				formateurEnBase.setAdress(formateur.getAdress());
				formateurRepository.save(formateurEnBase);
				response = new ResponseEntity<Formateur>(formateurEnBase, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			}
		}
		return response;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
		Optional<RessourceHumaine> opt = formateurRepository.findById(id);
		ResponseEntity<Void> response = null;
		if (opt.isPresent()) {
			formateurRepository.deleteById(id);
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}
