package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.AnnonceApi;
import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.services.AnnonceService;

@RestController
@CrossOrigin
public class AnnonceController implements AnnonceApi {
	
	private AnnonceService annonceService;
	
	@Autowired
	public AnnonceController(AnnonceService annonceService) {
		this.annonceService = annonceService;
	}

	@Override
	public ResponseEntity<AnnonceDto> save(AnnonceDto annonceDto) {
		return ResponseEntity.ok(annonceService.save(annonceDto));
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
	public List<AnnonceDto> getListArticleByKeyword(String keyword) {
		return annonceService.findListAnnonceByKeyword("%" + keyword + "%");
	}

	@Override
	public Page<AnnonceDto> getListAnnonceByPageable(int page, int size) {
		final Pageable pageable = PageRequest.of(page, size);
        return annonceService.findAnnonceByPageable(pageable);
	}

	
	

}
