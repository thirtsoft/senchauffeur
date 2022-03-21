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
import com.chauffeur.dto.ReservationDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ReservationApi {
	
	@PostMapping(value = APP_ROOT + "/reservations/create", consumes = MediaType.APPLICATION_JSON_VALUE, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Reservation",
    notes = "Cette méthode permet de reserver un chauffeur", response = ReservationDto.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "La Reservation a été crée"),
	    @ApiResponse(code = 400, message = "Aucun Reservation  crée / modifié")
	
	})
	ResponseEntity<ReservationDto> saveReservation(@RequestBody ReservationDto reservationDto);
	
	@PostMapping(value = APP_ROOT + "/reservations/createReservationToChauffeur", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Reserver un Chauffeur",
    notes = "Cette méthode permet à un Employeur de reserver un Chauffeur", response = ReservationDto.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "La Reservation a été crée"),
	    @ApiResponse(code = 400, message = "Aucun Reservation  crée / modifié")
	
	})
	ResponseEntity<ReservationDto> saveReservation(@RequestBody ReservationDto reservationDto, 
			@RequestParam Long idChauff, @RequestParam Long id);
	
	@PutMapping(value = APP_ROOT + "/reservations/update/{idReservation}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Modifier une Réservation",
    notes = "Cette méthode permet à un Employeur de modifier un Chauffeur", response = ReservationDto.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La Reservation a été modifié"),
	    @ApiResponse(code = 404, message = "Aucun Reservation  crée / modifié")
	
	})
	ResponseEntity<ReservationDto> updateReservation(@PathVariable("idReservation") Long idReservation, @RequestBody ReservationDto reservationDto);
	
	@PatchMapping(value = APP_ROOT + "/reservations/updateStatusOfReservation/{id}")
	@ApiOperation(value = "Modofier le status d'une Reservation",
	    notes = "Cette méthode permet de modifier le status une Reservation", response = ReservationDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Le satus d'une Reservation a été modifié"),
		    @ApiResponse(code = 400, message = "Aucun Annonce  modifié")
		
	})
    ResponseEntity<ReservationDto> updateStatusOfReservation(@RequestParam("status") String status, @PathVariable("id") String id);

	@GetMapping(value = APP_ROOT + "/reservations/findById/{idReservation}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rechercher une Reservation par ID",
		notes = "Cette méthode permet de chercher une Reservation par son ID", response = ReservationDto.class
	)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La Reservation a été trouver"),
	    @ApiResponse(code = 404, message = "Aucun Reservation n'existe avec cette ID pas dans la BD")
		
	})
	ResponseEntity<ReservationDto> getReservationById(@PathVariable("idReservation") Long idReservation);
	
	@GetMapping(value = APP_ROOT + "/reservations/NumbersOfReservationByStatusPending")
	@ApiOperation(value = "Decompter le nombre total de Reservation Encours",
	    notes = "Cette méthode permet de compter et d'afficher le nombre total Reservation Encours", response = AnnonceDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Le nombre de Reservation")
		
	})
	BigDecimal getNumberOfReservationsByStatusPending();
	
	@GetMapping(value = APP_ROOT + "/reservations/NumbersOfReservationInYear")
	@ApiOperation(value = "Decompter le nombre total Reservation dans l'années",
	    notes = "Cette méthode permet de compter et d'afficher le nombre total Reservation  dans l'années", response = AnnonceDto.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Le nombre de Reservation")
	})
	BigDecimal getNumbersOfReservationInYear();
	
	@GetMapping(value = APP_ROOT + "/reservations/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Renvoi la liste des Reservations",
	    notes = "Cette méthode permet de chercher et renvoyer la liste des Reservations", responseContainer = "List<ReservationDto>")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des Reservations / une liste vide")
	})
	ResponseEntity<List<ReservationDto>> getAllReservations();
	
	@GetMapping(value = APP_ROOT + "/reservations/searchAllReservationsOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Renvoi la liste des Reservations par order décroissante",
	    notes = "Cette méthode permet de chercher et renvoyer la liste des Reservations par ordre décroisante", 
	    responseContainer = "List<ReservationDto>")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des Reservations / une liste vide")
	})
	ResponseEntity<List<ReservationDto>> getdAllReservationsOrderByIdDesc();
	
	@GetMapping(value = APP_ROOT + "/reservations/searchReservationByStatusPending", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Reservation Encours",
	    notes = "Cette méthode permet d'afficher la liste des Reservation Encours", responseContainer = "List<ReservationDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Reservation a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Reservation")
		
	})
	ResponseEntity<List<ReservationDto>> getAllReservationsByStatusPending();
	
	@GetMapping(value = APP_ROOT + "/reservations/searchReservationByStatusValide", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Reservation Validées",
	    notes = "Cette méthode permet d'afficher la liste des Reservation Validées", responseContainer = "List<ReservationDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Reservation a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun liste Reservation")
		
	})
	ResponseEntity<List<ReservationDto>> getAllReservationsByStatusValid();
	
	@GetMapping(value = APP_ROOT + "/reservations/searchReservationByCustomerId/{userId}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rechercher la liste des Reservation par employeur",
	    notes = "Cette méthode permet d'afficher la liste des Reservations par Employeur", responseContainer = "List<ReservationDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Reservation a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun Reservation vide")
		
	})
	ResponseEntity<List<ReservationDto>> getAllReservationsByCustomerId(@PathVariable("userId") Long userId);
	
	@GetMapping(value = APP_ROOT + "/reservations/searchReservationByChauffeurId/{chauffId}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rechercher la liste des Reservation pour un chauffeur",
	    notes = "Cette méthode permet d'afficher la liste des Reservations pour un chauffeur", responseContainer = "List<ReservationDto>")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La liste des Reservation a été trouvé"),
		    @ApiResponse(code = 400, message = "Aucun Reservation vide")
		
	})
	ResponseEntity<List<ReservationDto>> getAllReservationsByChauffeurId(@PathVariable("chauffId") Long chauffId);
	
	@GetMapping(value = APP_ROOT + "/reservations/numberOfReservationsPeerMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Décompter les reservation par mois",
	    notes = "Cette méthode permet de recuperer et d'afficher le nombre de Reservation par mois sur un graphe")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le nombre de Reservation / Mois")
	
	})
	List<?> countNumberOfReservationsPeerMonth();
	
	@GetMapping(value = APP_ROOT + "/reservations/numberOfReservationsPeerYeer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Décompter les Reservation par années",
	    notes = "Cette méthode permet de recuperer et d'afficher le nombre de Reservation par années sur un graphe")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le nombre de Reservation / Années")
	
	})
	List<?> countNumberOfReservationsPeerYear();
	
	@DeleteMapping(value = APP_ROOT + "/reservations/delete/{idReservation}")
	@ApiOperation(value = "Supprimer une Reservation par ID",
	    notes = "Cette méthode permet de supprimer un Reservation par son identifiant")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La Reservation a été supprimé")
	
	})
	void delete(@PathVariable("idReservation") Long idReservation);


}
