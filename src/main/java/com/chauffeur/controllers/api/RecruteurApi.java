package com.chauffeur.controllers.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chauffeur.dto.RecruteurDto;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface RecruteurApi {
	
	@PostMapping(value = APP_ROOT + "/recruteurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<RecruteurDto> save(@RequestBody RecruteurDto recruteurDto);

	@GetMapping(value = APP_ROOT + "/recruteurs/{idRecruteur}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<RecruteurDto> findById(@PathVariable("idRecruteur") Long id);

	@GetMapping(value = APP_ROOT + "/recruteurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<RecruteurDto> findAll();

	@DeleteMapping(value = APP_ROOT + "/recruteurs/delete/{idRecruteur}")
	void delete(@PathVariable("idRecruteur") Long id);

}
