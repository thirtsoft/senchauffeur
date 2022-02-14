package com.chauffeur.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.AnnonceApi;
import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.NotificationDto;
import com.chauffeur.dto.UtilisateurDto;
import com.chauffeur.enumeration.StatusAnnonce;
import com.chauffeur.models.Utilisateur;
import com.chauffeur.services.AnnonceService;
import com.chauffeur.services.UtilisateurService;

@RestController
@CrossOrigin
public class AnnonceController implements AnnonceApi {
	
	private AnnonceService annonceService;
	
	private UtilisateurService utilisateurService;
	
	@Autowired
	public AnnonceController(AnnonceService annonceService,
							UtilisateurService utilisateurService) {
		this.annonceService = annonceService;
		this.utilisateurService = utilisateurService;
	}

	@Override
	public ResponseEntity<AnnonceDto> save(AnnonceDto annonceDto) {
		
		annonceDto.setStatusAnnonce(StatusAnnonce.ENCOURS);
		
		return ResponseEntity.ok(annonceService.save(annonceDto));
	}
	
	@Override
	public ResponseEntity<AnnonceDto> createAnnonceWithUser(AnnonceDto annonceDto, Long id) {
		
		Utilisateur utilisateur = Optional.of(UtilisateurDto.fromDtoToEntity(utilisateurService.findById(id))).get();

        annonceDto.setUtilisateurDto(UtilisateurDto.fromEntityToDto(utilisateur));

     
        annonceDto.setStatusAnnonce(StatusAnnonce.ENCOURS);
        
        AnnonceDto annonceDtoResult = annonceService.save(annonceDto);
        
        return new ResponseEntity<>(annonceDtoResult, HttpStatus.CREATED);

	}
	
	@Override
	public ResponseEntity<AnnonceDto> update(Long idAnnonce, AnnonceDto annonceDto) {
		annonceDto.setId(idAnnonce);
		return ResponseEntity.ok(annonceService.save(annonceDto));
	
	}

	@Override
	public ResponseEntity<AnnonceDto> findById(Long id) {
		return ResponseEntity.ok(annonceService.findById(id));
	}
	
	@Override
	public ResponseEntity<AnnonceDto> findByReference(String reference) {
		return ResponseEntity.ok(annonceService.findByReference(reference));
	}


	@Override
	public List<AnnonceDto> findAll() {
		return annonceService.findAll();
	}

	@Override
	public void delete(Long id) {
		annonceService.delete(id);
	}
	
	@Override
	public List<AnnonceDto> getListAnnonceBySelected() {
		return annonceService.findListAnnonceBySelected();
	}
	
	@Override
	public List<AnnonceDto> getListAnnonceByPermis(Long pId) {
		return annonceService.findListAnnonceByPermis(pId);
	}


	@Override
	public List<AnnonceDto> getListArticleByKeyword(String keyword) {
		return annonceService.findListAnnonceByKeyword("%" + keyword + "%");
	}
	
	@Override
	public List<AnnonceDto> getListAnnonceByLibelle(String libelle) {
		return annonceService.findListAnnonceByLibelle("%" + libelle + "%");
	}
	
	@Override
	public List<AnnonceDto> get5LatestAnnonceRecordOrderByIdDesc() {
		return annonceService.find5LatestRecordsByOrderByIdDesc();
	}
	
	@Override
	public List<AnnonceDto> getAnnonceByStatusEncours() {
		return annonceService.findListAnnonceByStatusEncours();

	}

	@Override
	public Page<AnnonceDto> getListAnnonceByPageable(int page, int size) {
		final Pageable pageable = PageRequest.of(page, size);
        return annonceService.findAnnonceByPageable(pageable);
	}

	@Override
	public BigDecimal getNumbersOfAnnoncess() {
		return annonceService.countNumbersOfAnnonces();
	}
	
	@Override
	public BigDecimal getNumberOfAnnoncesByStatusPending() {
		return annonceService.countNumberOfAnnoncesByStatusPending();
	}

	@Override
	public Page<AnnonceDto> getAnnonceByPermisPageables(Long permisId, int page, int size) {
		final Pageable pageable = PageRequest.of(page, size);
        return annonceService.findAnnonceByPermisByPageable(permisId, pageable);
	}

	@Override
	public ResponseEntity<List<AnnonceDto>> getCommandesByUserOrderByIdDesc(Long id) {
		List<AnnonceDto> annonceDtoList = annonceService.FindListAnnonceByCustomerId(id);
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}

	

	


}
