package com.chauffeur.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chauffeur.controllers.api.ChauffeurApi;
import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.services.ChauffeurService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class CheuffeurController implements ChauffeurApi {
	
	private final String chauffeurPhotosDir = "C://Users//Folio9470m//senchauffeur//chauffeur//photos//";
	 
	private final String chauffeurCvDir = "C://Users//Folio9470m//senchauffeur//chauffeur//cvs//";
	
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
	@Override
	public ResponseEntity<ChauffeurDto> saveChauffeurWithFiles(String chauffeur, 
			MultipartFile photoChauffeur,
			MultipartFile cvChauffeur) throws IOException {
		
		ChauffeurDto chauffeurDto = new ObjectMapper().readValue(chauffeur, ChauffeurDto.class);
	    
		if (photoChauffeur != null && !photoChauffeur.isEmpty()) {
	      	chauffeurDto.setPhotoChauffeur(photoChauffeur.getOriginalFilename());
	      	photoChauffeur.transferTo(new File(chauffeurPhotosDir + photoChauffeur.getOriginalFilename()));
	    }
		
		if (cvChauffeur != null && !cvChauffeur.isEmpty()) {
        	chauffeurDto.setCvChauffeur(cvChauffeur.getOriginalFilename());
        	cvChauffeur.transferTo(new File(chauffeurCvDir + cvChauffeur.getOriginalFilename()));
        }
		
		return ResponseEntity.ok(chauffeurService.save(chauffeurDto));
	}
	

}
