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

import com.chauffeur.dto.JetonDto;

public interface JetonApi {
	
	@PostMapping(value = APP_ROOT + "/jetons/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<JetonDto> save(@RequestBody JetonDto jetonDto);
	
	@PutMapping(value = APP_ROOT + "/jetons/update/{idJeton}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<JetonDto> update(@PathVariable("idJeton") Long idJeton, 
			@RequestBody JetonDto jetonDto);
	
	@GetMapping(value = APP_ROOT + "/jetons/findById/{idJeton}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<JetonDto> getJetonById(@PathVariable("idJeton") Long idJeton);
	
	@GetMapping(value = APP_ROOT + "/jetons/all", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<JetonDto>> getAllJetons();
		    
	@GetMapping(value = APP_ROOT + "/jetons/searchJetonsByIdDesc", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<JetonDto>> getAllJetonOrderByIdDesc();
		
	@GetMapping(value = APP_ROOT + "/jetons/NumbersOfjetons")
    public BigDecimal getNumbersOfjetons();

	@DeleteMapping(value = APP_ROOT + "/jetons/delete/{idJeton}")
	void delete(@PathVariable("idJeton") Long idJeton);
	

}
