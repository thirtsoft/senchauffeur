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

import com.chauffeur.dto.HistoriqueLoginDto;

public interface HistoriqueLoginApi {
	
	@PostMapping(value = APP_ROOT + "/historiqueLogins/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<HistoriqueLoginDto> save(@RequestBody HistoriqueLoginDto historiqueLoginDto);
	
	@PutMapping(value = APP_ROOT + "/historiqueLogins/update/{idHistoriqueLogin}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<HistoriqueLoginDto> update(@PathVariable("idHistoriqueLogin") Long idHistoriqueLogin, 
			@RequestBody HistoriqueLoginDto historiqueLoginDto);
	
	@GetMapping(value = APP_ROOT + "/historiqueLogins/findById/{idHistoriqueLogin}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<HistoriqueLoginDto> findById(@PathVariable("idHistoriqueLogin") Long idHistoriqueLogin);
	
	@GetMapping(value = APP_ROOT + "/historiqueLogins/all", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<HistoriqueLoginDto>> findAll();
		    
	@GetMapping(value = APP_ROOT + "/historiqueLogins/searchHistoriqueLoginByIdDesc", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<HistoriqueLoginDto>> getidHistoriqueLoginOrderByIdDesc();
		
	@GetMapping(value = APP_ROOT + "/historiqueLogins/NumbersOfHistoriqueLogins")
    public BigDecimal getNumbersOfHistoriqueLogins();

	@DeleteMapping(value = APP_ROOT + "/historiqueLogins/delete/{idHistoriqueLogin}")
	void delete(@PathVariable("idHistoriqueLogin") Long idHistoriqueLogin);
	
}
