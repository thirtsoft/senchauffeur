package com.chauffeur.controllers.api;

import static com.chauffeur.utils.Constants.APP_ROOT;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.dto.JetonDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface JetonApi {
	
	@PostMapping(value = APP_ROOT + "/jetons/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer un Jeton",
	    notes = "Cette méthode permet d'ajouter un nouveau Jeton", response = JetonDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Le satus de Jeton a été modifié"),
		    @ApiResponse(code = 400, message = "Aucun Jeton  modifié")
		
	})
	ResponseEntity<JetonDto> save(@RequestBody JetonDto jetonDto);
	
	@PutMapping(value = APP_ROOT + "/jetons/update/{idJeton}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<JetonDto> update(@PathVariable("idJeton") Long idJeton, 
			@RequestBody JetonDto jetonDto);
	
	@PatchMapping(value = APP_ROOT + "/jetons/updateEtatOfJeton/{id}")
	@ApiOperation(value = "Modofier l'etat d'un Jeton",
	    notes = "Cette méthode permet de modifier le status une Jeton", response = JetonDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Le satus de Jeton a été modifié"),
		    @ApiResponse(code = 400, message = "Aucun Jeton  modifié")
		
	})
    ResponseEntity<JetonDto> updateEtatOfJeton(@RequestParam("etat") String etat, @PathVariable("id") String id);
	
	@GetMapping(value = APP_ROOT + "/jetons/findById/{idJeton}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<JetonDto> getJetonById(@PathVariable("idJeton") Long idJeton);
	
	@GetMapping(value = APP_ROOT + "/jetons/all", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<JetonDto>> getAllJetons();
		    
	@GetMapping(value = APP_ROOT + "/jetons/searchJetonsByIdDesc", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<JetonDto>> getAllJetonOrderByIdDesc();
	
	@GetMapping(value = APP_ROOT + "/jetons/searchJetonsByCustomerId/{userId}", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<JetonDto>> getAllJetonByCustomerIdOrderIdDesc(@PathVariable("userId") Long userId);

		
	@GetMapping(value = APP_ROOT + "/jetons/NumbersOfjetons")
    public BigDecimal getNumbersOfjetons();

	@DeleteMapping(value = APP_ROOT + "/jetons/delete/{idJeton}")
	void delete(@PathVariable("idJeton") Long idJeton);
	

}
