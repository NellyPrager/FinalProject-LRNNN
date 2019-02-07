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

import factoryProject.Model.Formation;
import factoryProject.Model.JsonViews;
import factoryProject.Model.Module;
import factoryProject.Repository.RepositoryModule;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/module")
public class ModuleController {

	@Autowired
	private RepositoryModule repoModule;

	@GetMapping(path = { "", "/" })
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Module>> findAll() {
		return new ResponseEntity<>(repoModule.findAll(), HttpStatus.OK);
	}

	// @GetMapping(path = { "interns", "/interns" })
	// @JsonView(JsonViews.FormationWithInterns.class)
	// public ResponseEntity<List<Formation>> findAllFormationWithInterns() {
	// return new ResponseEntity<>(repoModule.findAllFormationWithInterns(),
	// HttpStatus.OK);
	// }

	@PostMapping(path = { "", "/" })
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Void> createModule(@Valid @RequestBody Module module, BindingResult br,
			UriComponentsBuilder uCB) {
		ResponseEntity<Void> response = null;
		if (br.hasErrors()) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {

			if (module.getRoom().getAvailability() == false) {
				response = new ResponseEntity<>(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
			} else if (module.getTrainer().getAvailability() == false) {
				response = new ResponseEntity<>(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
			} else {
				repoModule.save(module);
				HttpHeaders header = new HttpHeaders();
				header.setLocation(uCB.path("/module/{id}").buildAndExpand(module.getId()).toUri());
				response = new ResponseEntity<>(header, HttpStatus.CREATED);
			}
		}
		return response;
	}

	@GetMapping(value = "/{id}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Module> findById(@PathVariable(name = "id") Long id) {
		Optional<Module> opt = repoModule.findById(id);
		ResponseEntity<Module> response = null;
		if (opt.isPresent()) {
			response = new ResponseEntity<Module>((Module) opt.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping(path = { "", "/" })
	public ResponseEntity<Module> update(@Valid @RequestBody Module module, BindingResult br) {
		ResponseEntity<Module> response = null;

		if (br.hasErrors() || module.getId() == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} else {
			Optional<Module> opt = repoModule.findById(module.getId());
			if (opt.isPresent()) {
				Module moduleInBase = (Module) opt.get();
				moduleInBase.setDateDebut(module.getDateDebut());
				moduleInBase.setTrainer(module.getTrainer());
				moduleInBase.setSubject(module.getSubject());
				;
				moduleInBase.setRoom(module.getRoom());
				moduleInBase.setFormation(module.getFormation());
				repoModule.save(moduleInBase);
				response = new ResponseEntity<Module>(moduleInBase, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			}
		}
		return response;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
		Optional<Module> opt = repoModule.findById(id);
		ResponseEntity<Void> response = null;
		if (opt.isPresent()) {
			repoModule.deleteById(id);
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}
