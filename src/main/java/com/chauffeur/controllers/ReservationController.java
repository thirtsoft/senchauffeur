package com.chauffeur.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.ReservationApi;
import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.ReservationDto;
import com.chauffeur.dto.UtilisateurDto;
import com.chauffeur.models.Chauffeur;
import com.chauffeur.models.Utilisateur;
import com.chauffeur.services.ChauffeurService;
import com.chauffeur.services.ReservationService;
import com.chauffeur.services.UtilisateurService;

@RestController
@CrossOrigin
public class ReservationController implements ReservationApi {
	
	private final ReservationService reservationService;
	
	private final ChauffeurService chauffeurService;
	
	private final UtilisateurService utilisateurService;
	

	@Autowired
	public ReservationController(ReservationService reservationService,
								ChauffeurService chauffeurService,
								UtilisateurService utilisateurService) {
		this.reservationService = reservationService;
		this.chauffeurService = chauffeurService;
		this.utilisateurService = utilisateurService;
	}

	@Override
	public ResponseEntity<ReservationDto> saveReservation(ReservationDto reservationDto) {
        reservationDto.setStatus("ENCOURS");
		reservationDto.setCreatedDate(new Date());
		ReservationDto reservationDtoResult = reservationService.save(reservationDto);
		return new ResponseEntity<>(reservationDtoResult, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ReservationDto> saveReservation(ReservationDto reservationDto, Long idChauff, Long id) {
		
		Chauffeur chauffeur = Optional.of(ChauffeurDto.fromDtoToEntity(chauffeurService.findById(idChauff))).get();

        Utilisateur utilisateur = Optional.of(UtilisateurDto.fromDtoToEntity(utilisateurService.findById(id))).get();

        reservationDto.setCreatedDate(new Date()); 
        reservationDto.setStatus("ENCOURS");
       
        reservationDto.setChauffeurDto(ChauffeurDto.fromEntityToDto(chauffeur));
        reservationDto.setUtilisateurDto(UtilisateurDto.fromEntityToDto(utilisateur));
        
        ReservationDto reservationDtoResult = reservationService.save(reservationDto);

        return new ResponseEntity<>(reservationDtoResult, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ReservationDto> updateReservation(Long idReservation, ReservationDto reservationDto) {
		reservationDto.setId(idReservation);
		ReservationDto reservationDtoResult = reservationService.save(reservationDto);
        return new ResponseEntity<>(reservationDtoResult, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ReservationDto> updateStatusOfReservation(String status, String id) {
		ReservationDto newReservationDto = reservationService.updateStatusOfReservation(status, id);
        return new ResponseEntity<>(newReservationDto, HttpStatus.OK);
	}
	
	@Override
	public BigDecimal getNumberOfReservationsByStatusPending() {
		return reservationService.countNumberOfReservationByStatusPending();
	}

	@Override
	public BigDecimal getNumbersOfReservationInYear() {
		return reservationService.countNumberOfReservationsInYear();
	}

	@Override
	public ResponseEntity<ReservationDto> getReservationById(Long idReservation) {
		ReservationDto reservationDtoResult = reservationService.findById(idReservation);
        return new ResponseEntity<>(reservationDtoResult, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ReservationDto>> getAllReservations() {
		List<ReservationDto> reservationDtos = reservationService.findAll();
		return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ReservationDto>> getdAllReservationsOrderByIdDesc() {
		List<ReservationDto> reservationDtos = reservationService.findByTypeReservationByIdDesc();
		return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ReservationDto>> getAllReservationsByStatusPending() {
		List<ReservationDto> reservationDtos = reservationService.findListReservationByStatusPending();
		return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ReservationDto>> getAllReservationsByStatusValid() {
		List<ReservationDto> reservationDtos = reservationService.findListReservationByStatusValidated();
		return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<ReservationDto>> getAllReservationsByCustomerId(Long userId) {
		List<ReservationDto> reservationDtos = reservationService.FindListReservationByCustomerId(userId);
		return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<ReservationDto>> getAllReservationsByChauffeurId(Long chauffId) {
		List<ReservationDto> reservationDtos = reservationService.FindListReservationByChauffeurId(chauffId);
		return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
	}

	@Override
	public List<?> countNumberOfReservationsPeerMonth() {
		return reservationService.countNumberOfReservationsPeerMonth();
	}

	@Override
	public List<?> countNumberOfReservationsPeerYear() {
		return reservationService.countNumberOfReservationsPeerYear();
	}
	
	@Override
	public void delete(Long idReservation) {
		reservationService.delete(idReservation);
		
	}


}
