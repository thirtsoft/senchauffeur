package com.chauffeur.controllers.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface AnnonceApi {
	
	@PostMapping(value = APP_ROOT + "/annonces/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer une Annonce",
	    notes = "Cette méthode permet d'enregistrer une Annonce", response = AnnonceDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "L'Annonce a été crée"),
		    @ApiResponse(code = 400, message = "Aucun Annonce  crée / modifié")
		
	})
	ResponseEntity<AnnonceDto> save(@RequestBody AnnonceDto annonceDto);
	
	@PostMapping(value = APP_ROOT + "/annonces/createAnnonceWithUser", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer une Annonce par l'Employeur",
	    notes = "Cette méthode permet à un l'Employeur d'enregistrer une Annonce", response = AnnonceDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "L'Annonce a été crée"),
		    @ApiResponse(code = 400, message = "Aucun Annonce  crée / modifié")
		
	})
	ResponseEntity<AnnonceDto> createAnnonceWithUser(@RequestBody AnnonceDto annonceDto, @RequestParam Long id);
	
	@PutMapping(value = APP_ROOT + "/annonces/update/{idAnnonce}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Modofier une Annonce par l'Employeur",
	    notes = "Cette méthode permet à un l'Employeur de modifier une Annonce", response = AnnonceDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "L'Annonce a été modifié"),
		    @ApiResponse(code = 400, message = "Aucun Annonce  modifié")
		
	})
	ResponseEntity<AnnonceDto> update(@PathVariable("idAnnonce") Long idAnnonce, @RequestBody AnnonceDto annonceDto);
	
	@PatchMapping(value = APP_ROOT + "/annonces/updateStatusOfAnnonce/{id}")
	@ApiOperation(value = "Modofier le status d'une Annonce",
	    notes = "Cette méthode permet de modifier le status une Annonce", response = AnnonceDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Le satus de l'Annonce a été modifié"),
		    @ApiResponse(code = 400, message = "Aucun Annonce  modifié")
		
	})
    ResponseEntity<AnnonceDto> updateStatusOfAnnonce(@RequestParam("status") String status, @PathVariable("id") String id);

	@GetMapping(value = APP_ROOT + "/annonces/findById/{idAnnonce}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rechercher une Annonce par ID",
	    notes = "Cette méthode permet de rechercher une Annonce par son ID", response = AnnonceDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "L'Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun Annonce  avec cet ID")
		
	})
	ResponseEntity<AnnonceDto> findById(@PathVariable("idAnnonce") Long idAnnonce);
	
	@GetMapping(value = APP_ROOT + "/annonces/findAnnonceByCustomerId/{userId}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rechercher la liste des Annonce par employeur",
	    notes = "Cette méthode permet d'afficher la liste des Annonce par Employeur", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "L'Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun Annonce  avec cet ID")
		
	})
	ResponseEntity<AnnonceDto> getAnnonceByCustomerId(@PathVariable("userId") Long userId);
	
	@GetMapping(value = APP_ROOT + "/annonces/searchbyReference/{reference}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AnnonceDto> findByReference(@PathVariable("reference") String reference);

	@GetMapping(value = APP_ROOT + "/annonces/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Annonces",
	    notes = "Cette méthode permet d'afficher la liste des Annonces", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "L'Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun Annonce  avec cet ID")
		
	})
	ResponseEntity<List<AnnonceDto>> getAllAnnonces();
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Annonces par ordre décroissant",
	    notes = "Cette méthode permet d'afficher la liste des Annonces par ordre décroissante", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce  avec cet ID")
		
	})
	ResponseEntity<List<AnnonceDto>> getAllAnnonceOrderByIdDesc();
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceBySelectedIsTrue", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Annonces selectionné",
	    notes = "Cette méthode permet d'afficher la liste des Annonces selectionné", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
	ResponseEntity<List<AnnonceDto>> getListAnnonceBySelected();
	    
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByKeyword", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Annonces par mot clé",
	    notes = "Cette méthode permet d'afficher la liste des Annonces par mot clé", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
	ResponseEntity<List<AnnonceDto>> getListArticleByKeyword(@RequestParam(name = "reference") String reference);
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByLibelle", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<AnnonceDto>> getListAnnonceByLibelle(@RequestParam(name = "libelle") String libelle);
	
	@GetMapping(value = APP_ROOT + "/annonces/search5LatestAnnonceByIdDesc", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des 5 dernieres Annonces",
	    notes = "Cette méthode permet d'afficher la liste des 5 dernieres Annonces", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
	ResponseEntity<List<AnnonceDto>> get5LatestAnnonceRecordOrderByIdDesc();
	
	@GetMapping(value = APP_ROOT + "/annonces/search6ValidateLatestAnnonceByIdDesc", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des 6 dernieres Annonces",
	    notes = "Cette méthode permet d'afficher la liste des 6 dernieres Annonces", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
	ResponseEntity<List<AnnonceDto>> get6LatestValidatedAnnonceOrderByIdDesc();
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByCustomerIdOrderByIdDesc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Annonces d'un employeur",
	    notes = "Cette méthode permet d'afficher la liste des Annonces par employeur", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
	ResponseEntity<List<AnnonceDto>> getAnnoncesByUserOrderByIdDesc(@PathVariable(name = "id") Long id);
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnoncesByPermis/{pId}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Annonces par permis",
	    notes = "Cette méthode permet d'afficher la liste des Annonces par permis", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
	ResponseEntity<List<AnnonceDto>> getListAnnonceByPermis(@PathVariable("pId") Long pId);
		
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByStatusPending", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Annonces Encours",
	    notes = "Cette méthode permet d'afficher la liste des Annonces Encours", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
	ResponseEntity<List<AnnonceDto>> getAnnoncesByStatusPending();
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByStatusValide", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Annonces Validées",
	    notes = "Cette méthode permet d'afficher la liste des Annonces Validées", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
	ResponseEntity<List<AnnonceDto>> getAnnoncesByStatusValid();
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByStatusRejet", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Annonces Rejetées",
	    notes = "Cette méthode permet d'afficher la liste des Annonces Rejetées", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
	ResponseEntity<List<AnnonceDto>> getAnnoncesByStatusRejet();
	
	@GetMapping(value = APP_ROOT + "/annonces/NumbersOfAnnonces")
	@ApiOperation(value = "Decompter le nombre d'Annonces",
	    notes = "Cette méthode permet de compter et d'afficher le nombre total d'Annonces", response = AnnonceDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Le nombre d'Annonce"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
    BigDecimal getNumbersOfAnnoncess();
	
	@GetMapping(value = APP_ROOT + "/annonces/NumbersOfAnnonceByStatusPending")
	@ApiOperation(value = "Decompter le nombre total d'Annonces Encours",
	    notes = "Cette méthode permet de compter et d'afficher le nombre total d'Annonces Encours", response = AnnonceDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Le nombre d'Annonce"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
	BigDecimal getNumberOfAnnoncesByStatusPending();
	
	@GetMapping(value = APP_ROOT + "/annonces/NumbersOfAnnonceInMonth")
	@ApiOperation(value = "Decompter le nombre total d'Annonces dans le mois",
	    notes = "Cette méthode permet de compter et d'afficher le nombre total d'Annonces du mois", response = AnnonceDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Le nombre d'Annonce"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
	BigDecimal getNumberOfAnnonceInMonth();
	
	@GetMapping(value = APP_ROOT + "/annonces/numberOfAnnonceByMonth", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Décompter les Annonces par mois",
	    notes = "Cette méthode permet de recuperer et d'afficher le nombre de Annonces par mois sur un graphe")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le nombre de Annonces / Mois")
	
	})
	List<?> countNumberOfAnnonceByMonth();
	
	@GetMapping(value = APP_ROOT + "/annonces/numberOfAnnonceByYear", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Décompter les Annonces par années",
	    notes = "Cette méthode permet de recuperer et d'afficher le nombre de Annonces par années sur un graphe")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le nombre de Annonces / Mois")
	
	})
	List<?> countNumberOfAnnonceByYear();
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByPageables", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Annonces par pages",
	    notes = "Cette méthode permet d'afficher la liste des Annonces par pages", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
	Page<AnnonceDto> getListAnnonceByPageable(@RequestParam(name = "page") int page, 
			@RequestParam(name = "size") int size);
	
	@GetMapping(value = APP_ROOT + "/annonces/searchAnnonceByPermisPageables", 
	    		produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Annonces par permis et par pages",
	    notes = "Cette méthode permet d'afficher la liste des Annonces par permis et par pages", responseContainer = "List<AnnonceDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Annonce a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Annonce")
		
	})
	public Page<AnnonceDto> getAnnonceByPermisPageables(
	    		@RequestParam("id") Long permisId, 
	    		@RequestParam(name = "page") int page,
	    		@RequestParam(name = "size") int size);
	
	@DeleteMapping(value = APP_ROOT + "/annonces/delete/{idAnnonce}")
	@ApiOperation(value = "Supprimer une Annonce",
	    notes = "Cette méthode permet de supprimer par son ID")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "L'Annonce a été supprimé")
	
	})
	void delete(@PathVariable("idAnnonce") Long idAnnonce);

    @GetMapping(value = APP_ROOT + "/annonces/allAnnonces")
    @ApiOperation(value = "Afficher la listes des Annonces par pages",
	    notes = "Cette méthode permet d'afficher des Annonces par pages")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des Annonces est")
	
	})
    ResponseEntity<List<AnnonceDto>> getAllAnnonces(@RequestParam int page,@RequestParam int size);

    @GetMapping(value = APP_ROOT + "/annonces/permis")
    @ApiOperation(value = "Afficher la listes des Annonces par permis clé par pages",
	    notes = "Cette méthode permet d'afficher des Annonces par permis clé par pages")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des Annonces est")

	})
    ResponseEntity<List<AnnonceDto>> getAllAnnoncesByPermisId(@RequestParam Long id,@RequestParam int page,@RequestParam int size);

    @GetMapping(value = APP_ROOT + "/annonces/annoncekey")
    @ApiOperation(value = "Afficher la listes des Annonces par mot clé par pages",
	    notes = "Cette méthode permet d'afficher des Annonces par mot clé par pages")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des Annonces est")
	
	})
    ResponseEntity<List<AnnonceDto>> getAnnoncesByKeyWord(@RequestParam String keyWord,@RequestParam int page,@RequestParam int size);

    @GetMapping(value = APP_ROOT + "/annonces/annonceSize")
    @ApiOperation(value = "Calculer la longueur des Annonces",
	    notes = "Cette méthode permet de calculer la taille des Annonces")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La taille des Annonces")
	
	})
    long annonceSize();

    @GetMapping(value = APP_ROOT + "/annonces/ctpermisIdSize")
    @ApiOperation(value = "Calculer la longueur des Annonces par Id permis",
	    notes = "Cette méthode permet de calculer la taille des Annonces par Id perms")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La taille est")
	
	})
    long getAnnoncesByIdPermisSize(@RequestParam Long id);

    @GetMapping(value = APP_ROOT + "/annonces/keySize")
    @ApiOperation(value = "Calculer la longueur des Annonces par mot clé",
	    notes = "Cette méthode permet de calculer la taille des Annonces par mot clé")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La taille des Annonces par mot clé est")
	
	})
    long sizeOfAnnoncesByKey(@RequestParam String keyWord);

}
