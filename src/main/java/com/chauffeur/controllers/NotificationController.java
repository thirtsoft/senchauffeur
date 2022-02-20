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

import com.chauffeur.controllers.api.NotificationApi;
import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.NotificationDto;
import com.chauffeur.dto.UtilisateurDto;
import com.chauffeur.models.Chauffeur;
import com.chauffeur.models.Utilisateur;
import com.chauffeur.services.ChauffeurService;
import com.chauffeur.services.NotificationService;
import com.chauffeur.services.UtilisateurService;

@RestController
@CrossOrigin
public class NotificationController implements NotificationApi {
	
	private final NotificationService notificationService;
	
	private final ChauffeurService chauffeurService;
	
	private final UtilisateurService utilisateurService;
	

	@Autowired
	public NotificationController(NotificationService notificationService,
								  ChauffeurService chauffeurService,
								  UtilisateurService utilisateurService) {
		this.notificationService = notificationService;
		this.chauffeurService = chauffeurService;
		this.utilisateurService = utilisateurService;
		
	}
	
	@Override
	public ResponseEntity<NotificationDto> save(NotificationDto notificationDto) {
		return ResponseEntity.ok(notificationService.save(notificationDto));
	}
	
	@Override
	public ResponseEntity<NotificationDto> saveNoteToChauffeur(Long id, NotificationDto notificationDto) {
		return ResponseEntity.ok(notificationService.saveNoteToChauffeur(id, notificationDto));
	}
	
	
	@Override
	public ResponseEntity<NotificationDto> saveRating(NotificationDto notificationDto, Long idChauff, Long id) {

        Chauffeur chauffeur = Optional.of(ChauffeurDto.fromDtoToEntity(chauffeurService.findById(idChauff))).get();

        Utilisateur utilisateur = Optional.of(UtilisateurDto.fromDtoToEntity(utilisateurService.findById(id))).get();

        notificationDto.setChauffeurDto(ChauffeurDto.fromEntityToDto(chauffeur));
        notificationDto.setUtilisateurDto(UtilisateurDto.fromEntityToDto(utilisateur));

        notificationDto.setCreatedDate(new Date());

        NotificationDto notificationDtoResult = notificationService.save(notificationDto);
        
        return new ResponseEntity<>(notificationDtoResult, HttpStatus.CREATED);

	}
	
	@Override
	public ResponseEntity<NotificationDto> update(Long id, NotificationDto notificationDto) {
		notificationDto.setId(id);
		return ResponseEntity.ok(notificationService.save(notificationDto));
	}

	@Override
	public ResponseEntity<NotificationDto> findById(Long id) {
		return ResponseEntity.ok(notificationService.findById(id));
	}

	@Override
	public ResponseEntity<List<NotificationDto>> findAll() {
		List<NotificationDto> notificationDtos = notificationService.findAll();
		return new ResponseEntity<>(notificationDtos,  HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<NotificationDto>> getAllNotificationsOrderByIdDesc() {
		List<NotificationDto> notificationDtoList = notificationService.findByOrderByIdDesc();
	    return new ResponseEntity<>(notificationDtoList, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<NotificationDto>> getTop3ByOrderByCreatedDateDesc() {
		List<NotificationDto> notificationDtos = notificationService.findTop3RatingOrderByCreatedDateDesc();
		return new ResponseEntity<>(notificationDtos, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<NotificationDto>> getTop4ByOrderByCreatedDateDescByProductId(String chauffRef) {
		List<NotificationDto> notificationDtoList = notificationService.findTop4ByOrderByCreatedDateDescByChauffeurId(chauffRef);
	    return new ResponseEntity(notificationDtoList, HttpStatus.OK);
	}
	
	@Override
	public BigDecimal countNumberOfNotification() {
		return notificationService.countNumberOfNotification();
	}
	
	@Override
    public BigDecimal countNumberOfNotificationByChauffeurId(String chauffRef) {
        return notificationService.countNumberOfNotificationByChauffeurId(chauffRef);
    }

	@Override
	public void delete(Long id) {
		notificationService.delete(id);
		
	}
	
	
	
	

}
