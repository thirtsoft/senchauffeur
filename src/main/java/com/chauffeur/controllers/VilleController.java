package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.VilleApi;
import com.chauffeur.dto.VilleDto;
import com.chauffeur.services.VilleService;

@RestController
@CrossOrigin
public class VilleController implements VilleApi {
	
	private VilleService villeService;

	@Autowired
	public VilleController(VilleService villeService) {
		this.villeService = villeService;
	}

	@Override
	public ResponseEntity<VilleDto> save(VilleDto villeDto) {
		return ResponseEntity.ok(villeService.save(villeDto));
	}

	@Override
	public ResponseEntity<VilleDto> update(Long id, VilleDto villeDto) {
		villeDto.setId(id);
		return ResponseEntity.ok(villeService.save(villeDto));
	}

	@Override
	public ResponseEntity<VilleDto> findById(Long id) {
		return ResponseEntity.ok(villeService.findById(id));
	}

	@Override
	public List<VilleDto> findAll() {
		return villeService.findAll();
	}

	@Override
	public void delete(Long id) {
		villeService.delete(id);
		
	}
	

}
