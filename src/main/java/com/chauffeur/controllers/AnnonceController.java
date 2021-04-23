package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.AnnonceApi;
import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.services.AnnonceService;

@RestController
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
	public ResponseEntity<AnnonceDto> findById(Long id) {
		return ResponseEntity.ok(annonceService.findById(id));
	}

	@Override
	public List<AnnonceDto> findAll() {
		return annonceService.findAll();
	}

	@Override
	public void delete(Long id) {
		annonceService.delete(id);
	}

}
