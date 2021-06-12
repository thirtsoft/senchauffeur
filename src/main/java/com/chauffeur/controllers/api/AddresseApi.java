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

import com.chauffeur.dto.AddresseDto;

import static com.chauffeur.utils.Constants.APP_ROOT;;

public interface AddresseApi {
	
	@PostMapping(value = APP_ROOT + "/addresses/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<AddresseDto> save(@RequestBody AddresseDto addresseDto);

	@PutMapping(value = APP_ROOT + "/addresses/update/{idAddresse}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<AddresseDto> update(@PathVariable("idAddresse") Long id, @RequestBody AddresseDto addresseDto);

	@GetMapping(value = APP_ROOT + "/addresses/{idAddresse}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<AddresseDto> findById(@PathVariable("idAddresse") Long id);

	@GetMapping(value = APP_ROOT + "/addresses/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<AddresseDto> findAll();

	@DeleteMapping(value = APP_ROOT + "/addresses/delete/{idAddresse}")
	void delete(@PathVariable("idAddresse") Long id);

}
