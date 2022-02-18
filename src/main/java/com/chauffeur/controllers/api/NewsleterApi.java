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

import com.chauffeur.dto.NewsleterDto;

public interface NewsleterApi {
	
	@PostMapping(value = APP_ROOT + "/newsleters/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<NewsleterDto> createNewsleter(@RequestBody NewsleterDto newsleterDto);
	
	@PutMapping(value = APP_ROOT + "/newsleters/update/{idNewsleter}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<NewsleterDto> updateNewsleter(@PathVariable("idNewsleter") Long idNewsleter, 
			@RequestBody NewsleterDto newsleterDto);
	
	@GetMapping(value = APP_ROOT + "/newsleters/findById/{idNewsleter}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<NewsleterDto> findById(@PathVariable("idNewsleter") Long idNewsleter);
	
	@GetMapping(value = APP_ROOT + "/newsleters/all", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<NewsleterDto>> getAllNewsleters();
		    
	@GetMapping(value = APP_ROOT + "/newsleters/searchNewsleterOrderByIdDesc", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<NewsleterDto>> getListOfNewsletersOrderByIdDesc();
		
	@GetMapping(value = APP_ROOT + "/newsleters/NumbersOfNewsleters")
    public BigDecimal getNumbersOfNewsleters();

	@DeleteMapping(value = APP_ROOT + "/newsleters/delete/{idNewsleter}")
	void delete(@PathVariable("idNewsleter") Long idNewsleter);
	

}
