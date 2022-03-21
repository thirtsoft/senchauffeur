package com.chauffeur.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.TypeAnnonceApi;
import com.chauffeur.dto.TypeAnnonceDto;
import com.chauffeur.services.TypeAnnonceService;

@RestController
@CrossOrigin
public class TypeAnnonceController implements TypeAnnonceApi {
	
	private TypeAnnonceService typeAnnonceService;

	@Autowired
	public TypeAnnonceController(TypeAnnonceService typeAnnonceService) {
		this.typeAnnonceService = typeAnnonceService;
	}

	@Override
	public ResponseEntity<TypeAnnonceDto> saveTypeAnnonce(TypeAnnonceDto typeAnnonceDto) {
		typeAnnonceDto.setCreatedDate(new Date());
		TypeAnnonceDto typeAnnonceDtoResult = typeAnnonceService.save(typeAnnonceDto);
		return new ResponseEntity<>(typeAnnonceDtoResult, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<TypeAnnonceDto> updateTypeAnnonce(Long idTypeAnnonce, TypeAnnonceDto typeAnnonceDto) {
		typeAnnonceDto.setId(idTypeAnnonce);
		TypeAnnonceDto typeAnnonceDtoResult = typeAnnonceService.save(typeAnnonceDto);
		return new ResponseEntity<>(typeAnnonceDtoResult, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TypeAnnonceDto> getTypeAnnonceById(Long idTypeAnnonce) {
		TypeAnnonceDto typeAnnonceDtoResult = typeAnnonceService.findById(idTypeAnnonce);
		return new ResponseEntity<>(typeAnnonceDtoResult, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TypeAnnonceDto>> getAllTypeAnnonces() {
		List<TypeAnnonceDto> typeAnnonceDtos = typeAnnonceService.findAll();
		return new ResponseEntity<>(typeAnnonceDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TypeAnnonceDto>> getdAllTypeAnnoncesOrderByIdDesc() {
		List<TypeAnnonceDto> typeAnnonceDtos = typeAnnonceService.findByTypeAnnonceByIdDesc();
		return new ResponseEntity<>(typeAnnonceDtos, HttpStatus.OK);
	}

	@Override
	public void delete(Long idTypeAnnonce) {
		typeAnnonceService.delete(idTypeAnnonce);
		
	}
	
	

}
