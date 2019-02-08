package factoryProject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import factoryProject.Model.Administrateur;
import factoryProject.Model.Formateur;
import factoryProject.Model.Gestionnaire;
import factoryProject.Model.RessourceHumaine;
import factoryProject.Model.Technicien;
import factoryProject.Model.TypeUtilisateur;
import factoryProject.Repository.RepositoryLogin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	RepositoryLogin repoLogin;

	@PostMapping("/")
	public ResponseEntity<Technicien> loginTechnicien(@RequestBody Technicien tech) {
		Optional<Technicien> techni = findTechByUsername(tech.getUsername());
		ResponseEntity<Technicien> response = null;
		if (techni.isPresent()) {
			if (tech.getPassword().equals(techni.get().getPassword())) {
				techni.get().setPassword(null);
				response = new ResponseEntity<>(techni.get(), HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

//	@PostMapping("/")
//	public ResponseEntity<RessourceHumaine> loginGeneral(@RequestBody RessourceHumaine user, @RequestParam(name="type") String type) {
//
//		ResponseEntity<RessourceHumaine> response = null;
//
//		if (type.equals(TypeUtilisateur.technicien)) {
//			Optional<Technicien> techni = findTechByUsername(((Technicien) user).getUsername());
//			if (techni.isPresent()) {
//				if (((Technicien) user).getPassword().equals(techni.get().getPassword())) {
//					techni.get().setPassword(null);
//					response = new ResponseEntity<>(techni.get(), HttpStatus.OK);
//				} else {
//					response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//				}
//			} else {
//				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//		}
//		return response;
//
//	}

	@GetMapping(value = "/{usernametech}")
	public Optional<Technicien> findTechByUsername(@PathVariable(name = "usernametech") String username) {
		Optional<Technicien> opt = repoLogin.findTechnicienByUsername(username);
		if (opt.isPresent()) {
			return opt;
		} else {
			return null;
		}
	}

	@GetMapping(value = "/{usernameadmin}")
	public Optional<Administrateur> findAdminByUsername(@PathVariable(name = "usernameadmin") String username) {
		Optional<Administrateur> opt = repoLogin.findAdministrateurByUsername(username);
		if (opt.isPresent()) {
			return opt;
		} else {
			return null;
		}
	}

	@GetMapping(value = "/{usernameform}")
	public Optional<Formateur> findFormByUsername(@PathVariable(name = "usernameform") String username) {
		Optional<Formateur> opt = repoLogin.findFormateurByUsername(username);
		if (opt.isPresent()) {
			return opt;
		} else {
			return null;
		}
	}

	@GetMapping(value = "/{usernamegest}")
	public Optional<Gestionnaire> findGestByUsername(@PathVariable(name = "usernamegest") String username) {
		Optional<Gestionnaire> opt = repoLogin.findGestionnaireByUsername(username);
		if (opt.isPresent()) {
			return opt;
		} else {
			return null;
		}
	}

	// @GetMapping(value = "/{username}")
	// public Optional<RessourceHumaine> findByUsername(@PathVariable(name =
	// "username") String username) {
	// Optional<RessourceHumaine> opt = repoLogin.findByUsername(username);
	// if (opt.isPresent()) {
	// return opt;
	// } else {
	// return null;
	// }
	// }

}
