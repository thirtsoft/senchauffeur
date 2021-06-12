package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.ChauffeurApi;
import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.services.ChauffeurService;

@RestController
@CrossOrigin
public class CheuffeurController implements ChauffeurApi {
	
	private ChauffeurService chauffeurService;

	@Autowired
	public CheuffeurController(ChauffeurService chauffeurService) {
		this.chauffeurService = chauffeurService;
	}
	@Override
	public ResponseEntity<ChauffeurDto> save(ChauffeurDto chauffeurDto) {
		return ResponseEntity.ok(chauffeurService.save(chauffeurDto));
	}
	
	@Override
	public ResponseEntity<ChauffeurDto> update(Long id, ChauffeurDto chauffeurDto) {
		chauffeurDto.setId(id);
		return ResponseEntity.ok(chauffeurService.save(chauffeurDto));
	}
	
	@Override
	public ResponseEntity<ChauffeurDto> findById(Long id) {
		return ResponseEntity.ok(chauffeurService.findById(id));
	}

	@Override
	public List<ChauffeurDto> findAll() {
		return chauffeurService.findAll();
	}

	@Override
	public void delete(Long id) {
		chauffeurService.delete(id);
		
	}
	

}
