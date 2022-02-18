package com.chauffeur.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.NewsleterApi;
import com.chauffeur.dto.JetonDto;
import com.chauffeur.dto.NewsleterDto;
import com.chauffeur.services.NewsleterService;

@CrossOrigin
@RestController
public class NewsleterController implements NewsleterApi {
	
	private final NewsleterService newsleterService;
	
	@Autowired
	public NewsleterController(NewsleterService newsleterService) {
		this.newsleterService = newsleterService;
	}

	@Override
	public ResponseEntity<NewsleterDto> createNewsleter(NewsleterDto newsleterDto) {
		NewsleterDto newNewsleterDto = newsleterService.save(newsleterDto);
		return new ResponseEntity<>(newNewsleterDto, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<NewsleterDto> updateNewsleter(Long idNewsleter, NewsleterDto newsleterDto) {
		newsleterDto.setId(idNewsleter);
		NewsleterDto newNewsleterDto = newsleterService.save(newsleterDto);
		return new ResponseEntity<>(newNewsleterDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NewsleterDto> findById(Long idNewsleter) {
		NewsleterDto newNewsleterDto = newsleterService.findById(idNewsleter);
		return new ResponseEntity<>(newNewsleterDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<NewsleterDto>> getAllNewsleters() {
		List<NewsleterDto> newsleterDtos = newsleterService.findAll();
		return new ResponseEntity<>(newsleterDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<NewsleterDto>> getListOfNewsletersOrderByIdDesc() {
		List<NewsleterDto> newsleterDtos = newsleterService.findAllNewsletersByOrderByIdDesc();
		return new ResponseEntity<>(newsleterDtos, HttpStatus.OK);
	}

	@Override
	public BigDecimal getNumbersOfNewsleters() {
		return newsleterService.countNumbersOfNewsleters();
	}

	@Override
	public void delete(Long idNewsleter) {
		newsleterService.delete(idNewsleter);
	}

}
