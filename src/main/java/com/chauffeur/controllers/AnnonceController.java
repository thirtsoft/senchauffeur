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
		annonceDto.setStatus("ENCOURS");
		annonceDto.setDateCandidature(new Date());
		
		AnnonceDto newAnnonceDto = annonceService.save(annonceDto);
		
		AnnonceDto annonceDTOResult = annonceService.findById(newAnnonceDto.getId());
	    
	    HistoriqueAnnonceDto historiqueAnnonceDto = new HistoriqueAnnonceDto();
	    historiqueAnnonceDto.setAnnonceDto(annonceDTOResult);
	    historiqueAnnonceDto.setAction("ANNONCE AJOUTEE");
	    historiqueAnnonceDto.setCreatedDate(new Date());
	    historiqueAnnonceService.save(historiqueAnnonceDto);
	    
	    return new ResponseEntity<>(newAnnonceDto, HttpStatus.CREATED);

	}
	
	@Override
	public ResponseEntity<AnnonceDto> createAnnonceWithUser(AnnonceDto annonceDto, Long id) {
		
		Utilisateur utilisateur = Optional.of(UtilisateurDto.fromDtoToEntity(utilisateurService.findById(id))).get();

        annonceDto.setUtilisateurDto(UtilisateurDto.fromEntityToDto(utilisateur));
        
        annonceDto.setStatus("ENCOURS");
        annonceDto.setDateCandidature(new Date());
        
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
		AnnonceDto annonceDTOResult = annonceService.findById(id);
		
		return new ResponseEntity<>(annonceDTOResult, HttpStatus.OK);
		
	}
	
	@Override
	public ResponseEntity<AnnonceDto> findByReference(String reference) {
		AnnonceDto annonceDTOResult = annonceService.findByReference(reference);
		return new ResponseEntity<>(annonceDTOResult, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<AnnonceDto>> getAllAnnonces() {
		List<AnnonceDto> annonceDtos = annonceService.findAll();
		return new ResponseEntity<>(annonceDtos, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<AnnonceDto>> getAllAnnonceOrderByIdDesc() {
		List<AnnonceDto> annonceDtoList = annonceService.findByAnnonceByIdDesc();
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<AnnonceDto>> getListAnnonceBySelected() {
		List<AnnonceDto> annonceDtoList = annonceService.findListAnnonceBySelected();
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<AnnonceDto>> getListAnnonceByPermis(Long pId) {
		List<AnnonceDto> annonceDtoList = annonceService.findListAnnonceByPermis(pId);
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<AnnonceDto>>  getListArticleByKeyword(String keyword) {
		List<AnnonceDto> annonceDtoList = annonceService.findListAnnonceByKeyword("%" + keyword + "%");
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<AnnonceDto>> getListAnnonceByLibelle(String libelle) {
		List<AnnonceDto> annonceDtoList = annonceService.findListAnnonceByLibelle("%" + libelle + "%");
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<AnnonceDto>> get5LatestAnnonceRecordOrderByIdDesc() {
		List<AnnonceDto> annonceDtoList = annonceService.find5LatestRecordsByOrderByIdDesc();
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<AnnonceDto>> get6LatestValidatedAnnonceOrderByIdDesc() {
		List<AnnonceDto> annonceDtoList = annonceService.find6LatestValidatedRecordsByOrderByIdDesc();
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
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

	@Override
	public ResponseEntity<List<AnnonceDto>> getAllAnnonces(int page, int size) {
		List<AnnonceDto> annonceDtoList = annonceService.getAllAnnonceDtos(page, size);
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<AnnonceDto>> getAllAnnoncesByPermisId(Long id, int page, int size) {
		List<AnnonceDto> annonceDtoList = annonceService.getAllAnnonceDtosByIdPermis(id, page, size);
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<AnnonceDto>> getAnnoncesByKeyWord(String keyWord, int page, int size) {
		List<AnnonceDto> annonceDtoList = annonceService.getAllAnnonceDtosByKey(keyWord, page, size);
        return new ResponseEntity<>(annonceDtoList, HttpStatus.OK);
	}

	@Override
	public long annonceSize() {
		return annonceService.getAllAnnonceDtosSize();
	}

	@Override
	public long getAnnoncesByIdPermisSize(Long id) {
		return annonceService.getAnnonceDtosByPermisIdLength(id);
	}

	@Override
	public long sizeOfAnnoncesByKey(String keyWord) {
		return annonceService.getAnnonceDtosSizeByKey(keyWord);
	}
	

}
