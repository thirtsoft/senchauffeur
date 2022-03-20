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
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

	private final String chauffeurCvDir = "C://Users//Folio9470m//senchauffeur//chauffeur//cvs//";
		
	private ChauffeurService chauffeurService;

	@Autowired
	public ChauffeurController(ChauffeurService chauffeurService) {
		this.chauffeurService = chauffeurService;
	}

	@Override
	public ResponseEntity<ChauffeurDto> save(ChauffeurDto chauffeurDto) {
		chauffeurDto.setDateInscription(new Date());
		return ResponseEntity.ok(chauffeurService.save(chauffeurDto));
	}
	
	@Override
	public ResponseEntity<ChauffeurDto> update(Long id, ChauffeurDto chauffeurDto) {
		chauffeurDto.setId(id);
		return ResponseEntity.ok(chauffeurService.save(chauffeurDto));
	}
	
	@Override
	public ResponseEntity<ChauffeurDto> getChauffeurById(Long id) {
		return ResponseEntity.ok(chauffeurService.findById(id));
	}

	@Override
	public ResponseEntity<List<ChauffeurDto>> getAllChauffeurs() {
		List<ChauffeurDto> chauffeurDtoList = chauffeurService.findAll();
        return new ResponseEntity<>(chauffeurDtoList, HttpStatus.OK);
		
	}
	
	@Override
	public ResponseEntity<List<ChauffeurDto>> getdAllChauffeursOrderByIdDesc() {
		List<ChauffeurDto> chauffeurDtoList = chauffeurService.findByChauffeurByIdDesc();
        return new ResponseEntity<>(chauffeurDtoList, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<ChauffeurDto>> getListChauffeurBySelected() {
		List<ChauffeurDto> chauffeurDtoList = chauffeurService.findListChauffeurBySelected();
        return new ResponseEntity<>(chauffeurDtoList, HttpStatus.OK);
	}
	/*
	@Override
	public ResponseEntity<ChauffeurDto> saveChauffeurWithFiles(String chauffeur, 
			MultipartFile photoChauffeur,
			MultipartFile cvChauffeur) throws IOException {
		
		ChauffeurDto chauffeurDto = new ObjectMapper().readValue(chauffeur, ChauffeurDto.class);
		
		chauffeurDto.setDateInscription(new Date());
	    
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
	public ResponseEntity<ChauffeurDto> saveChauffeurWithFiles(String chauffeur, 
			MultipartFile photoChauffeur,
			MultipartFile cvChauffeur) throws IOException {
		
		ChauffeurDto chauffeurDto = new ObjectMapper().readValue(chauffeur, ChauffeurDto.class);
		chauffeurDto.setDateInscription(new Date());
	    
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
	
	
	@Override
	public ResponseEntity<List<ChauffeurDto>> getListChauffeurByKeyword(String keyword) {
		List<ChauffeurDto> chauffeurDtoList = chauffeurService.findListChauffeurByKeyword("%" + keyword + "%");
        return new ResponseEntity<>(chauffeurDtoList, HttpStatus.OK);
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
	public void uploadPhotoChauffeur(MultipartFile file, Long id) throws IOException {
		ChauffeurDto chauffeurDto = chauffeurService.findById(id);
		chauffeurDto.setPhotoChauffeur(file.getOriginalFilename());
	    Files.write(Paths.get(
	    		System.getProperty("user.home") + "/senchauffeur/chauffeur/photos/" + chauffeurDto.getPhotoChauffeur()), 
	    		file.getBytes());

	    chauffeurService.save(chauffeurDto);
		
	}
	
	

	@Override
	public byte[] getCvChauffeur(Long id) throws Exception {
		ChauffeurDto chauffeurDto = chauffeurService.findById(id);
        System.out.println("Article DTO -- " + chauffeurDto);
     
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/senchauffeur/chauffeur/cvs/" + chauffeurDto.getCvChauffeur()));

	}
	
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
	public void uploadCvChauffeur(MultipartFile file, Long id) throws IOException {
		ChauffeurDto chauffeurDto = chauffeurService.findById(id);
		chauffeurDto.setCvChauffeur(file.getOriginalFilename());
	    Files.write(Paths.get(
	    		System.getProperty("user.home") + "/senchauffeur/chauffeur/cvs/" + chauffeurDto.getCvChauffeur()), 
	    		file.getBytes());

	    chauffeurService.save(chauffeurDto);
	}
	
	@Override
	public ResponseEntity<List<ChauffeurDto>> getListChauffeurByPermis(Long pId) {
		List<ChauffeurDto> chauffeurDtoList = chauffeurService.findListChauffeurByPermis(pId);
        return new ResponseEntity<>(chauffeurDtoList, HttpStatus.OK);
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
	public ResponseEntity<List<ChauffeurDto>> getListChauffeurByDisponibility(String disponibility) {
		List<ChauffeurDto> chauffeurDtoList = chauffeurService.findChauffeurByDisponibility("%" + disponibility + "%");
        return new ResponseEntity<>(chauffeurDtoList, HttpStatus.OK);
		
	}
	
	@Override
	public Page<ChauffeurDto> getChauffeurByPermisPageables(Long permisId, int page, int size) {
		final Pageable pageable = PageRequest.of(page, size);
	    return chauffeurService.findChauffeurByPermisPageables(permisId, pageable);
	}
	
	@Override
	public void delete(Long id) {
		chauffeurService.delete(id);
		
	}
	
	@Override
	public List<?> countNumberOfChauffeurByMonth() {
		return chauffeurService.countNumberOfChauffeurByMonth();
	}
	
	@Override
	public List<?> countNumberOfChauffeurByYear() {
		return chauffeurService.countNumberOfChauffeurByYear();
	}
	

}
