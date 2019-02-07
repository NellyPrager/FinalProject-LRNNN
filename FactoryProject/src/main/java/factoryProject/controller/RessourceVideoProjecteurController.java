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
import factoryProject.Model.VideoProjecteur;
import factoryProject.Repository.RepositoryVideoProjecteur;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/videoprojecteur")
public class RessourceVideoProjecteurController {

	@Autowired
	RepositoryVideoProjecteur repositoryVideoProjector;

	@GetMapping(path = { "", "/" })
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<List<VideoProjecteur>> findAll() {
		return new ResponseEntity<>(repositoryVideoProjector.findAllVideoProjector(), HttpStatus.OK);
	}

	@PostMapping(path = { "", "/" })
	public ResponseEntity<Void> createProjector(@Valid @RequestBody Materiel videoProjector, BindingResult br,
			UriComponentsBuilder uCB) {
		ResponseEntity<Void> response = null;
		if (br.hasErrors()) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			repositoryVideoProjector.save(videoProjector);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(
					uCB.path("/videoprojecteur/{code}").buildAndExpand(videoProjector.getCode()).toUri());
			response = new ResponseEntity<>(header, HttpStatus.CREATED);
		}
		return response;
	}

	@GetMapping(value = "/{code}")
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<VideoProjecteur> findById(@PathVariable(name = "code") String code) {
		Optional<Materiel> opt = repositoryVideoProjector.findById(code);
		ResponseEntity<VideoProjecteur> response = null;
		if (opt.isPresent()) {
			response = new ResponseEntity<VideoProjecteur>((VideoProjecteur) opt.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping(path = { "", "/" })
	public ResponseEntity<VideoProjecteur> update(@Valid @RequestBody VideoProjecteur videoProjector,
			BindingResult br) {
		ResponseEntity<VideoProjecteur> response = null;

		if (br.hasErrors() || videoProjector.getCode() == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} else {
			Optional<Materiel> opt = repositoryVideoProjector.findById(videoProjector.getCode());
			if (opt.isPresent()) {
				VideoProjecteur videoProjectorEnBase = (VideoProjecteur) opt.get();
				videoProjectorEnBase.setRoom(videoProjector.getRoom());

				repositoryVideoProjector.save(videoProjectorEnBase);
				response = new ResponseEntity<VideoProjecteur>(videoProjectorEnBase, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			}
		}
		return response;
	}

	@DeleteMapping(value = "/{code}")
	public ResponseEntity<Void> delete(@PathVariable(name = "code") String code) {
		Optional<Materiel> opt = repositoryVideoProjector.findById(code);
		ResponseEntity<Void> response = null;
		if (opt.isPresent()) {
			repositoryVideoProjector.deleteById(code);
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

}
