package com.chauffeur.controllers.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chauffeur.dto.ParticulierDto;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface ParticulierApi {
	
	@PostMapping(value = APP_ROOT + "/particuliers/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ParticulierDto> save(@RequestBody ParticulierDto particulierDto);

	@GetMapping(value = APP_ROOT + "/particuliers/{idParticulier}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ParticulierDto> findById(@PathVariable("idParticulier") Long id);

	@GetMapping(value = APP_ROOT + "/particuliers/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<ParticulierDto> findAll();

	@DeleteMapping(value = APP_ROOT + "/particuliers/delete/{idParticulier}")
	void delete(@PathVariable("idParticulier") Long id);

}
