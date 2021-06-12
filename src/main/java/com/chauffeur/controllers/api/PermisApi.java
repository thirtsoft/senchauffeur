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

import com.chauffeur.dto.PermisDto;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface PermisApi {
	
	@PostMapping(value = APP_ROOT + "/permis/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<PermisDto> save(@RequestBody PermisDto permisDto);
	
	@PutMapping(value = APP_ROOT + "/permis/update/{idPermis}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<PermisDto> update(@PathVariable("idPermis") Long id, @RequestBody PermisDto permisDto);

	@GetMapping(value = APP_ROOT + "/permis/{idPermis}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<PermisDto> findById(@PathVariable("idPermis") Long id);

	@GetMapping(value = APP_ROOT + "/permis/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<PermisDto> findAll();

	@DeleteMapping(value = APP_ROOT + "/permis/delete/{idPermis}")
	void delete(@PathVariable("idPermis") Long id);

}
