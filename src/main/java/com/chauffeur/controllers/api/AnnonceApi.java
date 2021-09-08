package com.chauffeur.controllers.api;

import java.math.BigDecimal;
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

import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.dto.ChauffeurDto;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface AnnonceApi {
	
	@PostMapping(value = APP_ROOT + "/annonces/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<AnnonceDto> save(@RequestBody AnnonceDto annonceDto);
	
	@PutMapping(value = APP_ROOT + "/annonces/update/{idAnnonce}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<AnnonceDto> update(@PathVariable("idAnnonce") Long idAnnonce, 
			@RequestBody AnnonceDto annonceDto);

	@GetMapping(value = APP_ROOT + "/annonces/{idAnnonce}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<AnnonceDto> findById(@PathVariable("idAnnonce") Long idAnnonce);
	
	@GetMapping(value = APP_ROOT + "/annonces/searchbyReference/{reference}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AnnonceDto> findByReference(@PathVariable("reference") String reference);

	@GetMapping(value = APP_ROOT + "/annonces/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<AnnonceDto> findAll();
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceBySelectedIsTrue", produces = MediaType.APPLICATION_JSON_VALUE)
	List<AnnonceDto> getListAnnonceBySelected();
	    
	@DeleteMapping(value = APP_ROOT + "/annonces/delete/{idAnnonce}")
	void delete(@PathVariable("idAnnonce") Long idAnnonce);
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByKeyword", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	List<AnnonceDto> getListArticleByKeyword(@RequestParam(name = "reference") String reference);
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByLibelle", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	List<AnnonceDto> getListAnnonceByLibelle(@RequestParam(name = "libelle") String libelle);
	
	@GetMapping(value = APP_ROOT + "/annonces/search5LatestAnnonceByIdDesc", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	List<AnnonceDto> get5LatestAnnonceRecordOrderByIdDesc();
	
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByStatusEncours", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	List<AnnonceDto> getAnnonceByStatusEncours();
	
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnoncesByPermis/{pId}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	List<AnnonceDto> getListAnnonceByPermis(@PathVariable("pId") Long pId);
	 
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByPageables", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	Page<AnnonceDto> getListAnnonceByPageable(@RequestParam(name = "page") int page, 
			@RequestParam(name = "size") int size);
	
	@GetMapping(value = APP_ROOT + "/annonces/NumbersOfAnnonces")
    public BigDecimal getNumbersOfAnnoncess();
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByPermisPageables", 
	    		produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<AnnonceDto> getAnnonceByPermisPageables(
	    		@RequestParam("id") Long permisId, 
	    		@RequestParam(name = "page") int page,
	    		@RequestParam(name = "size") int size);

}
