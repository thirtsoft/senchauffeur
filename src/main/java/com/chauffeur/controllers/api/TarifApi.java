package com.chauffeur.controllers.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.chauffeur.dto.TarifDto;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface TarifApi {
	
	@PostMapping(value = APP_ROOT + "/tarifs/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<TarifDto> save(@RequestBody TarifDto tarifDto);
	
	@PutMapping(value = APP_ROOT + "/tarifs/update/{idTarif}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<TarifDto> update(@PathVariable("idTarif") Long idTarif, 
			@RequestBody TarifDto tarifDto);

	@GetMapping(value = APP_ROOT + "/tarifs/{idTarif}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<TarifDto> findById(@PathVariable("idTarif") Long idTarif);
	
	@GetMapping(value = APP_ROOT + "/tarifs/searchbyReference/{reference}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TarifDto> findByReference(@PathVariable("reference") String reference);

	@GetMapping(value = APP_ROOT + "/tarifs/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<TarifDto> findAll();

	@DeleteMapping(value = APP_ROOT + "/tarifs/delete/{idTarif}")
	void delete(@PathVariable("idTarif") Long idTarif);
	
	@GetMapping(value = APP_ROOT + "/tarifs/searchTarifByKeyword", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	List<TarifDto> getListTarifByKeyword(@RequestParam(name = "reference") String reference);
	
	@GetMapping(value = APP_ROOT + "/tarifs/searchTarifsByAnnonce/{pId}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	List<TarifDto> getListTarifByAnnonce(@PathVariable("pId") Long pId);
	 
	@GetMapping(value = APP_ROOT + "/tarifs/searchTarifByPageables", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	Page<TarifDto> getListTarifByPageable(@RequestParam(name = "page") int page, 
			@RequestParam(name = "size") int size);
	
	
	@GetMapping(value = APP_ROOT + "/tarifs/searchTarifByAnnoncePageables", 
	    		produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<TarifDto> getTarifByAnnoncePageables(
	    		@RequestParam("id") Long annonceId, 
	    		@RequestParam(name = "page") int page,
	    		@RequestParam(name = "size") int size);

}
