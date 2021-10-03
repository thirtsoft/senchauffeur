package com.chauffeur.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chauffeur.controllers.api.ChauffeurApi;
import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.services.ChauffeurService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@CrossOrigin
public class ChauffeurController implements ChauffeurApi {
	
	private final String chauffeurPhotosDir = "C://Users//Folio9470m//senchauffeur//chauffeur//photos//";
	
//	private final String chauffeurPhotosDir = "../../src//main//resources//static//images//";
	
//	private static final String chauffeurPhotosDir = "./src/main/resources//static//images//";
	 
	private final String chauffeurCvDir = "C://Users//Folio9470m//senchauffeur//chauffeur//cvs//";
	
//	private final String chauffeurCvDir = "./src/main/resources//static//cvs//";
	
	
	private ChauffeurService chauffeurService;

	@Autowired
	public ChauffeurController(ChauffeurService chauffeurService) {
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
	/*
	
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
	*/
	
	@Override
	public List<ChauffeurDto> getListChauffeurByKeyword(String keyword) {
		return chauffeurService.findListChauffeurByKeyword("%" + keyword + "%");
	}
	
	@Override
	public Page<ChauffeurDto> getListChauffeurByPageable(int page, int size) {
		final Pageable pageable = PageRequest.of(page, size);
        return chauffeurService.findChauffeurByPageable(pageable);
	}
	
	
	@Override
	public byte[] getPhotoChauffeur(Long id) throws Exception {
		ChauffeurDto chauffeurDto = chauffeurService.findById(id);

        System.out.println("Article DTO -- " + chauffeurDto);
         
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/senchauffeur/chauffeur/photos/" + chauffeurDto.getPhotoChauffeur()));

	}
	

/*
	@Override
	public byte[] getPhotoChauffeur(Long id) throws Exception {
		ChauffeurDto chauffeurDto = chauffeurService.findById(id);

        System.out.println("Article DTO -- " + chauffeurDto);
      
        return Files.readAllBytes(Paths.get("./src/main/resources//static//images//" + chauffeurDto.getPhotoChauffeur()));

	}
	*/
	
	@Override
	public void uploadPhotoChauffeur(MultipartFile photoChauffeur, Long idChauffeur) throws IOException {
		ChauffeurDto chauffeurDto = chauffeurService.findById(idChauffeur);
		chauffeurDto.setPhotoChauffeur(photoChauffeur.getOriginalFilename());
	    Files.write(Paths.get(
	    		System.getProperty("user.home") + "/senchauffeur/chauffeur/photos/" + chauffeurDto.getPhotoChauffeur()), 
	    		photoChauffeur.getBytes());

	    chauffeurService.save(chauffeurDto);
		
	}
	
	
	@Override
	public byte[] getCvChauffeur(Long id) throws Exception {
		
		ChauffeurDto chauffeurDto = chauffeurService.findById(id);

        System.out.println("Article DTO -- " + chauffeurDto);
      
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/senchauffeur/chauffeur/cvs/" + chauffeurDto.getCvChauffeur()));

	}
	
	/*
	@Override
	public byte[] getCvChauffeur(Long id) throws Exception {
		
		ChauffeurDto chauffeurDto = chauffeurService.findById(id);

        System.out.println("Article DTO -- " + chauffeurDto);
      
        return Files.readAllBytes(Paths.get("./src/main/resources//static//cvs//" + chauffeurDto.getCvChauffeur()));
	}
	*/
	
	public void downloadChauffeurFile(HttpServletRequest request, HttpServletResponse response,
             @PathVariable("fileName") String fileName) throws IOException {
	  File file = new File(chauffeurCvDir + fileName);
	  if (file.exists()) {
		  String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		  if (mimeType == null) {
			  mimeType = "application/octet-stream";
	       }
	  
		  response.setContentType(mimeType);
		  response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
		  response.setContentLength((int) file.length());
		  InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			
		  FileCopyUtils.copy(inputStream, response.getOutputStream());
      }
	  

    }
	
	@Override
	public void uploadCvChauffeur(MultipartFile cvChauffeur, Long idChauffeur) throws IOException {
		
		ChauffeurDto chauffeurDto = chauffeurService.findById(idChauffeur);
		chauffeurDto.setCvChauffeur(cvChauffeur.getOriginalFilename());
	    Files.write(Paths.get(
	    		System.getProperty("user.home") + "/senchauffeur/chauffeur/cvs/" + chauffeurDto.getCvChauffeur()), 
	    		cvChauffeur.getBytes());

	    chauffeurService.save(chauffeurDto);
	}
	@Override
	public List<ChauffeurDto> getListChauffeurByPermis(Long pId) {
		return chauffeurService.findListChauffeurByPermis(pId);
	}
	@Override
	public BigDecimal getNumbersOfChauffeurs() {
		return chauffeurService.countNumbersOfChauffeurs();
	}
	@Override
	public Page<ChauffeurDto> getChauffeurByKeywordByPageable(String mc, int page, int size) {
		 final Pageable pageable = PageRequest.of(page, size);
	     return chauffeurService.findChauffeurByKeywordByPageable(mc, pageable);
	}
	@Override
	public Page<ChauffeurDto> getChauffeurByLocalityPageables(Long addId, int page, int size) {
		final Pageable pageable = PageRequest.of(page, size);
	    return chauffeurService.findChauffeurByLocalityPageables(addId, pageable);
	}
	@Override
	public List<ChauffeurDto> getListChauffeurByDisponibility(String disponibility) {
		return chauffeurService.findChauffeurByDisponibility("%" + disponibility + "%");
	}
	@Override
	public Page<ChauffeurDto> getChauffeurByPermisPageables(Long permisId, int page, int size) {
		final Pageable pageable = PageRequest.of(page, size);
	    return chauffeurService.findChauffeurByPermisPageables(permisId, pageable);
	}
	@Override
	public List<ChauffeurDto> getListChauffeurBySelected() {
		return chauffeurService.findListChauffeurBySelected();
	}
	

}
