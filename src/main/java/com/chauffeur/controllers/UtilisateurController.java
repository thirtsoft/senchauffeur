package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.UtilisateurApi;
import com.chauffeur.dto.UtilisateurDto;
import com.chauffeur.services.UtilisateurService;

@RestController
@CrossOrigin
public class UtilisateurController implements UtilisateurApi {
	
	private UtilisateurService utilisateurService;

	@Autowired
	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}
	@Override
	public ResponseEntity<UtilisateurDto> save(UtilisateurDto utilisateurDto) {
		return ResponseEntity.ok(utilisateurService.save(utilisateurDto));
	}

	@Override
	public ResponseEntity<UtilisateurDto> findById(Long id) {
		return ResponseEntity.ok(utilisateurService.findById(id));
	}

	@Override
	public List<UtilisateurDto> findAll() {
		return utilisateurService.findAll();
	}

	@Override
	public void delete(Long id) {
		utilisateurService.delete(id);
		
	}
	@Override
	public ResponseEntity<UtilisateurDto> update(Long id, UtilisateurDto utilisateurDto) {
		utilisateurDto.setId(id);
		return ResponseEntity.ok(utilisateurService.save(utilisateurDto));
	}


}
