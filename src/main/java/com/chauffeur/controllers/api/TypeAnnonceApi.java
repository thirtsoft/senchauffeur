package com.chauffeur.controllers.api;

import static com.chauffeur.utils.Constants.APP_ROOT;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chauffeur.dto.TypeAnnonceDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface TypeAnnonceApi {
	
	@PostMapping(value = APP_ROOT + "/typeAnnonces/create", consumes = MediaType.APPLICATION_JSON_VALUE, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un typeAnnonce",
    notes = "Cette méthode permet d'enregistrer un typeAnnonce", response = TypeAnnonceDto.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Le TypeAnnonce a été crée"),
	    @ApiResponse(code = 400, message = "Aucun TypeAnnonce  crée / modifié")
	
	})
	ResponseEntity<TypeAnnonceDto> saveTypeAnnonce(@RequestBody TypeAnnonceDto typeAnnonceDto);
	
	@PutMapping(value = APP_ROOT + "/typeAnnonces/update/{idTypeAnnonce}",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Modifier un TypeAnnonce",
    notes = "Cette méthode permet de modifier TypeAnnonce", response = TypeAnnonceDto.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le TypeAnnonce a été modifié"),
	    @ApiResponse(code = 404, message = "Aucun TypeAnnonce  crée / modifié")
	
	})
	ResponseEntity<TypeAnnonceDto> updateTypeAnnonce(@PathVariable("idTypeAnnonce") Long idTypeAnnonce, @RequestBody TypeAnnonceDto typeAnnonceDto);

	@GetMapping(value = APP_ROOT + "/typeAnnonces/findById/{idTypeAnnonce}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rechercher une TypeAnnonce par ID",
		notes = "Cette méthode permet de chercher une Reservation par son ID", response = TypeAnnonceDto.class
	)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La TypeAnnonce a été trouver"),
	    @ApiResponse(code = 404, message = "Aucun TypeAnnonce n'existe avec cette ID pas dans la BD")
		
	})
	ResponseEntity<TypeAnnonceDto> getTypeAnnonceById(@PathVariable("idTypeAnnonce") Long idTypeAnnonce);
	
	@GetMapping(value = APP_ROOT + "/typeAnnonces/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Renvoi la liste des TypeAnnonce",
	    notes = "Cette méthode permet de chercher et renvoyer la liste des TypeAnnonce", responseContainer = "List<TypeAnnonceDto>")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des TypeAnnonce / une liste vide")
	})
	ResponseEntity<List<TypeAnnonceDto>> getAllTypeAnnonces();
	
	@GetMapping(value = APP_ROOT + "/typeAnnonces/searchAllTypeAnnoncesOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Renvoi la liste des TypeAnnonce par order décroissante",
	    notes = "Cette méthode permet de chercher et renvoyer la liste des TypeAnnonce par ordre décroisante", 
	    responseContainer = "List<TypeAnnonceDto>")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des TypeAnnonces / une liste vide")
	})
	ResponseEntity<List<TypeAnnonceDto>> getdAllTypeAnnoncesOrderByIdDesc();
	
	
	@DeleteMapping(value = APP_ROOT + "/typeAnnonces/delete/{idTypeAnnonce}")
	@ApiOperation(value = "Supprimer une TypeAnnonce par ID",
	    notes = "Cette méthode permet de supprimer un TypeAnnonce par son identifiant")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La TypeAnnonce a été supprimé")
	
	})
	void delete(@PathVariable("idTypeAnnonce") Long idTypeAnnonce);

}
