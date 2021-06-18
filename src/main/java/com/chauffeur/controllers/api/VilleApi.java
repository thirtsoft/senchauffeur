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

import com.chauffeur.dto.VilleDto;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface VilleApi {
	
	@PostMapping(value = APP_ROOT + "/ville/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<VilleDto> save(@RequestBody VilleDto villeDto);
	
	@PutMapping(value = APP_ROOT + "/ville/update/{idVille}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<VilleDto> update(@PathVariable("idVille") Long id, @RequestBody VilleDto villeDto);

	@GetMapping(value = APP_ROOT + "/ville/{idVille}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<VilleDto> findById(@PathVariable("idVille") Long id);

	@GetMapping(value = APP_ROOT + "/ville/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<VilleDto> findAll();

	@DeleteMapping(value = APP_ROOT + "/ville/delete/{idVille}")
	void delete(@PathVariable("idVille") Long id);

}
