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
import com.chauffeur.dto.HistoriqueAnnonceDto;
import com.chauffeur.dto.UtilisateurDto;
import com.chauffeur.enumeration.StatusAnnonce;
import com.chauffeur.models.Utilisateur;
import com.chauffeur.services.AnnonceService;
import com.chauffeur.services.HistoriqueAnnonceService;
import com.chauffeur.services.UtilisateurService;

@RestController
@CrossOrigin
public class AnnonceController implements AnnonceApi {
	
	private AnnonceService annonceService;
	
	private UtilisateurService utilisateurService;
	
	private HistoriqueAnnonceService historiqueAnnonceService;
	
	@Autowired
	public AnnonceController(AnnonceService annonceService,
							UtilisateurService utilisateurService,
							HistoriqueAnnonceService historiqueAnnonceService) {
		this.annonceService = annonceService;
		this.utilisateurService = utilisateurService;
		this.historiqueAnnonceService = historiqueAnnonceService;
	}

	@Override
	public ResponseEntity<AnnonceDto> save(AnnonceDto annonceDto) {
		annonceDto.setStatusAnnonce(StatusAnnonce.ENCOURS);
		annonceDto.setStatus("ENCOURS");
		
		AnnonceDto newAnnonceDto = annonceService.save(annonceDto);
		
		AnnonceDto annonceDTOResult = annonceService.findById(newAnnonceDto.getId());
	    
	    HistoriqueAnnonceDto historiqueAnnonceDto = new HistoriqueAnnonceDto();
	    historiqueAnnonceDto.setAnnonceDto(annonceDTOResult);
	    historiqueAnnonceDto.setAction("ANNONCE AJOUTEE");
	    historiqueAnnonceDto.setCreatedDate(new Date());
	    historiqueAnnonceService.save(historiqueAnnonceDto);
	    
	    return new ResponseEntity<>(newAnnonceDto, HttpStatus.CREATED);
		
	//	return ResponseEntity.ok(annonceService.save(annonceDto));
	}
	
	@Override
	public ResponseEntity<AnnonceDto> createAnnonceWithUser(AnnonceDto annonceDto, Long id) {
		
		Utilisateur utilisateur = Optional.of(UtilisateurDto.fromDtoToEntity(utilisateurService.findById(id))).get();

        annonceDto.setUtilisateurDto(UtilisateurDto.fromEntityToDto(utilisateur));

     
        annonceDto.setStatusAnnonce(StatusAnnonce.ENCOURS);
        
        annonceDto.setStatus("ENCOURS");
        
        AnnonceDto annonceDtoResult = annonceService.save(annonceDto);
		
		AnnonceDto annonceDTOResult = annonceService.findById(annonceDtoResult.getId());
	    
	    HistoriqueAnnonceDto historiqueAnnonceDto = new HistoriqueAnnonceDto();
	    historiqueAnnonceDto.setAnnonceDto(annonceDTOResult);
	    historiqueAnnonceDto.setAction("ANNONCE AJOUTEE");
	    historiqueAnnonceDto.setCreatedDate(new Date());
	    historiqueAnnonceService.save(historiqueAnnonceDto);
	    
        
        return new ResponseEntity<>(annonceDtoResult, HttpStatus.CREATED);

	}
	
	@Override
	public ResponseEntity<AnnonceDto> update(Long idAnnonce, AnnonceDto annonceDto) {
		annonceDto.setId(idAnnonce);
		AnnonceDto annonceDtoResult = annonceService.save(annonceDto);
		
		AnnonceDto annonceDTOResult = annonceService.findById(annonceDtoResult.getId());
	    
	    HistoriqueAnnonceDto historiqueAnnonceDto = new HistoriqueAnnonceDto();
	    historiqueAnnonceDto.setAnnonceDto(annonceDTOResult);
	    historiqueAnnonceDto.setAction("ANNONCE MODIFIEE");
	    historiqueAnnonceDto.setCreatedDate(new Date());
	    historiqueAnnonceService.save(historiqueAnnonceDto);
		
		return new ResponseEntity<>(annonceDtoResult, HttpStatus.OK);
	
	}
	
	@Override
	public ResponseEntity<AnnonceDto> updateStatusOfAnnonce(String status, String id) {
		AnnonceDto newAnnonceDto = annonceService.updateStatusOfAnnonce(status, id);
		
		AnnonceDto annonceDTOResult = annonceService.findById(newAnnonceDto.getId());
	    
	    HistoriqueAnnonceDto historiqueAnnonceDto = new HistoriqueAnnonceDto();
	    historiqueAnnonceDto.setAnnonceDto(annonceDTOResult);
	    historiqueAnnonceDto.setAction("STATUS ANNONCE MODIFIE");
	    historiqueAnnonceDto.setCreatedDate(new Date());
	    historiqueAnnonceService.save(historiqueAnnonceDto);
	    
        return new ResponseEntity<>(newAnnonceDto, HttpStatus.OK);
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
	public List<AnnonceDto> getAllAnnonces() {
		return annonceService.findAll();
	}
	
	@Override
	public ResponseEntity<List<AnnonceDto>> getAllAnnonceOrderByIdDesc() {
		List<AnnonceDto> annonceDtoList = annonceService.findByAnnonceByIdDesc();
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
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
	public ResponseEntity<List<AnnonceDto>> getAnnoncesByUserOrderByIdDesc(Long id) {
		List<AnnonceDto> annonceDtoList = annonceService.FindListAnnonceByCustomerId(id);
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AnnonceDto> getAnnonceByCustomerId(Long userId) {
		AnnonceDto annonceDto = annonceService.FindAnnonceByCustomerId(userId);
        return new ResponseEntity<>(annonceDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<AnnonceDto>> getAnnoncesByStatusPending() {
		List<AnnonceDto> annonceDtoList = annonceService.findListAnnonceByStatusPending();
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<AnnonceDto>> getAnnoncesByStatusValid() {
		List<AnnonceDto> annonceDtoList = annonceService.findListAnnonceByStatusValid();
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<AnnonceDto>> getAnnoncesByStatusRejet() {
		List<AnnonceDto> annonceDtoList = annonceService.findListAnnonceByStatusRejet();
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}
	
	@Override
	public BigDecimal getNumbersOfAnnoncess() {
		return annonceService.countNumbersOfAnnonces();
	}
	
	@Override
	public BigDecimal getNumberOfAnnoncesByStatusPending() {
		return annonceService.countNumberOfAnnonceByStatusPending();
	}

	@Override
	public BigDecimal getNumberOfAnnonceInMonth() {
		return annonceService.countNumberOfAnnoncesInMonth();
	}

	@Override
	public List<?> countNumberOfAnnonceByMonth() {
		return annonceService.countNumberTotalOfAnnonceByMonth();
	}

	@Override
	public List<?> countNumberOfAnnonceByYear() {
		return annonceService.countNumberTotalOfAnnonceByYear();
	}
	
	@Override
	public Page<AnnonceDto> getListAnnonceByPageable(int page, int size) {
		final Pageable pageable = PageRequest.of(page, size);
        return annonceService.findAnnonceByPageable(pageable);
	}
	
	@Override
	public Page<AnnonceDto> getAnnonceByPermisPageables(Long permisId, int page, int size) {
		final Pageable pageable = PageRequest.of(page, size);
        return annonceService.findAnnonceByPermisByPageable(permisId, pageable);
	}
	
	@Override
	public void delete(Long id) {
		
		AnnonceDto annonceDTOResult = annonceService.findById(id);
		 
	    HistoriqueAnnonceDto historiqueAnnonceDto = new HistoriqueAnnonceDto();
	    historiqueAnnonceDto.setAnnonceDto(annonceDTOResult);
	    historiqueAnnonceDto.setAction("ANNONCE SUPPRIMEE");
	    historiqueAnnonceDto.setCreatedDate(new Date());
	    historiqueAnnonceService.save(historiqueAnnonceDto);
	    
		annonceService.delete(id);
	}
	

}
