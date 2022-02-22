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

import com.chauffeur.dto.TarifDto;
import com.chauffeur.dto.VilleDto;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface VilleApi {
	
	@PostMapping(value = APP_ROOT + "/villes/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<VilleDto> save(@RequestBody VilleDto villeDto);
	
	@PutMapping(value = APP_ROOT + "/villes/update/{idVille}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<VilleDto> update(@PathVariable("idVille") Long id, @RequestBody VilleDto villeDto);

	@GetMapping(value = APP_ROOT + "/villes/findById/{idVille}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<VilleDto> findById(@PathVariable("idVille") Long id);

	@GetMapping(value = APP_ROOT + "/villes/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<VilleDto> findAll();
	
	@GetMapping(value = APP_ROOT + "/chauffeurs/searchVillesOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<VilleDto>> getdAllVillesOrderByIdDesc();

	@DeleteMapping(value = APP_ROOT + "/villes/delete/{idVille}")
	void delete(@PathVariable("idVille") Long id);

}
