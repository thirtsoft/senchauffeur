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
import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.dto.EmailDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.chauffeur.utils.Constants.APP_ROOT;;

public interface AddresseApi {
	
	@PostMapping(value = APP_ROOT + "/addresses/create", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer un Addresse",
       notes = "Cette méthode permet d'ajouter un Addresse",
	       response = EmailDto.class)
	@ApiResponses(value = {
	       @ApiResponse(code = 201, message = "Addresse a été envoyé / modifié"),
	       @ApiResponse(code = 400, message = "Aucun Addresse  envoyé")
	})
	ResponseEntity<AddresseDto> save(@RequestBody AddresseDto addresseDto);

	@PutMapping(value = APP_ROOT + "/addresses/update/{idAddresse}", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Modofier une Addresse",
	    notes = "Cette méthode permet de modifier une Addresse", response = AddresseDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Addresse a été modifié"),
		    @ApiResponse(code = 400, message = "Aucun Addresse  modifié")
		
	})
	ResponseEntity<AddresseDto> update(@PathVariable("idAddresse") Long id, @RequestBody AddresseDto addresseDto);

	@GetMapping(value = APP_ROOT + "/addresses/findById/{idAddresse}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rechercher un Addresse par ID",
     notes = "Cette méthode permet de chercher un Addresse par son ID", response = EmailDto.class
		)
	@ApiResponses(value = {
	     @ApiResponse(code = 200, message = "Addresse a été trouver"),
	     @ApiResponse(code = 404, message = "Aucun Addresse n'existe avec cette ID pas dans la BD")
	})
	ResponseEntity<AddresseDto> findById(@PathVariable("idAddresse") Long id);

	@GetMapping(value = APP_ROOT + "/addresses/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Addresse",
	    notes = "Cette méthode permet d'afficher la liste des Addresse ", responseContainer = "List<AddresseDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Addresse a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun Addresse  avec cet ID")
		
	})
	ResponseEntity<List<AddresseDto>> getAllAddresses();
	
	@GetMapping(value = APP_ROOT + "/addresses/searchAddressOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Addresse par ordre decroissante",
	    notes = "Cette méthode permet d'afficher la liste des Addresse par ordre decroissante", responseContainer = "List<AddresseDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Addresse a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun Addresse  avec cet ID")
		
	})
	ResponseEntity<List<AddresseDto>> getdAllAddressOrderByIdDesc();

	@DeleteMapping(value = APP_ROOT + "/addresses/delete/{idAddresse}")
	@ApiOperation(value = "Supprimer une Addresse par son ID",
	    notes = "Cette méthode permet de supprimer une Addresse par son ID")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "L'Annonce a été supprimé")
	
	})
	void delete(@PathVariable("idAddresse") Long id);

}
