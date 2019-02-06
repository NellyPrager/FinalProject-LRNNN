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
import factoryProject.Model.Materiel;
import factoryProject.Model.Ordinateur;
import factoryProject.Repository.RepositoryOrdinateur;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ordinateur")
public class RessourceOrdinateurController {

	@Autowired
	RepositoryOrdinateur repositoryComputer;

	@GetMapping(path = { "", "/" })
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<Ordinateur>> findAll() {
//		List<Ordinateur> ordinateurs = new ArrayList<>();
//		repositoryComputer.findAllComputer().forEach(ordinateurs::add);
		return new ResponseEntity<>(repositoryComputer.findAllComputer(), HttpStatus.OK);
	}

	@GetMapping(path = { "/intern" })
	@JsonView(JsonViews.ComputerWithIntern.class)
	public ResponseEntity<List<Ordinateur>> findAllWithInterns() {
		return new ResponseEntity<>(repositoryComputer.findAllComputerWithInterns(), HttpStatus.OK);
	}

	@PostMapping(path = { "", "/" })
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Void> createComputer(@Valid @RequestBody Ordinateur computer, BindingResult br,
			UriComponentsBuilder uCB) {
		ResponseEntity<Void> response = null;
		if (br.hasErrors()) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			repositoryComputer.save(computer);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/ordinateur/{code}").buildAndExpand(computer.getCode()).toUri());
			response = new ResponseEntity<>(header, HttpStatus.CREATED);
		}
		return response;
	}

	@GetMapping(value = "/{code}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Ordinateur> findById(@PathVariable(name = "code") String code) {
		Optional<Materiel> opt = repositoryComputer.findById(code);
		ResponseEntity<Ordinateur> response = null;
		if (opt.isPresent()) {
			response = new ResponseEntity<Ordinateur>((Ordinateur) opt.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping(path = { "", "/" })
	public ResponseEntity<Ordinateur> update(@Valid @RequestBody Ordinateur computer, BindingResult br) {
		ResponseEntity<Ordinateur> response = null;

		if (br.hasErrors() || computer.getCode() == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} else {
			Optional<Materiel> opt = repositoryComputer.findById(computer.getCode());
			if (opt.isPresent()) {
				Ordinateur ordinateurEnBase = (Ordinateur) opt.get();
				ordinateurEnBase.setCode(computer.getCode());
				ordinateurEnBase.setHardDisk(computer.getHardDisk());
				ordinateurEnBase.setPrice(computer.getPrice());
				ordinateurEnBase.setProcessor(computer.getProcessor());
				ordinateurEnBase.setRam(computer.getRam());
				ordinateurEnBase.setYear(computer.getYear());
				repositoryComputer.save(ordinateurEnBase);
				response = new ResponseEntity<Ordinateur>(ordinateurEnBase, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			}
		}
		return response;
	}

	@DeleteMapping(value = "/{code}")
	public ResponseEntity<Void> delete(@PathVariable(name = "code") String code) {
		Optional<Materiel> opt = repositoryComputer.findById(code);
		ResponseEntity<Void> response = null;
		if (opt.isPresent()) {
			repositoryComputer.deleteById(code);
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

}
