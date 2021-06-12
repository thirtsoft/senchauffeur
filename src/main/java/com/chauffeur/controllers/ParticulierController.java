package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.ParticulierApi;
import com.chauffeur.dto.ParticulierDto;
import com.chauffeur.services.ParticulierService;

@RestController
@CrossOrigin
public class ParticulierController implements ParticulierApi {
	
	private ParticulierService particulierService;

	@Autowired
	public ParticulierController(ParticulierService particulierService) {
		this.particulierService = particulierService;
	}
	@Override
	public ResponseEntity<ParticulierDto> save(ParticulierDto particulierDto) {
		return ResponseEntity.ok(particulierService.save(particulierDto));
	}
	
	@Override
	public ResponseEntity<ParticulierDto> update(Long id, ParticulierDto particulierDto) {
		particulierDto.setId(id);
		return ResponseEntity.ok(particulierService.save(particulierDto));
	}

	@Override
	public ResponseEntity<ParticulierDto> findById(Long id) {
		return ResponseEntity.ok(particulierService.findById(id));
	}

	@Override
	public List<ParticulierDto> findAll() {
		return particulierService.findAll();
	}

	@Override
	public void delete(Long id) {
		particulierService.delete(id);
		
	}
	


}
