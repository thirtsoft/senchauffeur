package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.PermisApi;
import com.chauffeur.dto.PermisDto;
import com.chauffeur.services.PermisService;

@RestController
public class PermisController implements PermisApi{
	
	private PermisService permisService;

	@Autowired
	public PermisController(PermisService permisService) {
		this.permisService = permisService;
	}
	@Override
	public ResponseEntity<PermisDto> save(PermisDto permisDto) {
		return ResponseEntity.ok(permisService.save(permisDto));
	}

	@Override
	public ResponseEntity<PermisDto> findById(Long id) {
		return ResponseEntity.ok(permisService.findById(id));
	}

	@Override
	public List<PermisDto> findAll() {
		return permisService.findAll();
	}

	@Override
	public void delete(Long id) {
		permisService.delete(id);
		
	}

}
