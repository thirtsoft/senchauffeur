package com.chauffeur.controllers.api;

import static com.chauffeur.utils.Constants.APP_ROOT;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chauffeur.dto.HistoriqueAnnonceDto;

public interface HistoriqueAnnonceApi {
	
	@PostMapping(value = APP_ROOT + "/historiqueAnnonces/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<HistoriqueAnnonceDto> save(@RequestBody HistoriqueAnnonceDto historiqueAnnonceDto);
	
	@PutMapping(value = APP_ROOT + "/historiqueAnnonces/update/{idHistoriqueAnnonce}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<HistoriqueAnnonceDto> update(@PathVariable("idHistoriqueAnnonce") Long idHistoriqueAnnonce, 
			@RequestBody HistoriqueAnnonceDto historiqueAnnonceDto);
	
	@GetMapping(value = APP_ROOT + "/historiqueAnnonces/findById/{idHistoriqueAnnonce}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<HistoriqueAnnonceDto> findById(@PathVariable("idHistoriqueAnnonce") Long idHistoriqueAnnonce);
	
	@GetMapping(value = APP_ROOT + "/historiqueAnnonces/all", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<HistoriqueAnnonceDto>> findAll();
		    
	@GetMapping(value = APP_ROOT + "/historiqueAnnonces/searchHistoriqueAnnonceByIdDesc", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<HistoriqueAnnonceDto>> getListHistoriqueAnnoncesOrderByIdDesc();
		
	@GetMapping(value = APP_ROOT + "/historiqueAnnonces/NumbersOfhistoriqueAnnonces")
    public BigDecimal getNumbersOfhistoriqueAnnonces();

	@DeleteMapping(value = APP_ROOT + "/historiqueAnnonces/delete/{idHistoriqueAnnonce}")
	void delete(@PathVariable("idHistoriqueAnnonce") Long idHistoriqueAnnonce);

}
