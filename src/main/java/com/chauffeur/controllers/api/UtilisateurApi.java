package com.chauffeur.controllers.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chauffeur.dto.UtilisateurDto;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface UtilisateurApi {
	
	@PostMapping(value = APP_ROOT + "/utilisateurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<UtilisateurDto> save(@RequestBody UtilisateurDto utilisateurDto);
	
	@PutMapping(value = APP_ROOT + "/utilisateurs/update/{idUtilisateur}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<UtilisateurDto> update(@PathVariable("idUtilisateur") Long id, @RequestBody UtilisateurDto utilisateurDto);


	@GetMapping(value = APP_ROOT + "/utilisateurs/findById/{idUtilisateur}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<UtilisateurDto> findById(@PathVariable("idUtilisateur") Long id);

	@GetMapping(value = APP_ROOT + "/utilisateurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<UtilisateurDto> findAll();

	@DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{idUtilisateur}")
	void delete(@PathVariable("idUtilisateur") Long id);

}
