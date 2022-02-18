package com.chauffeur.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.JetonApi;
import com.chauffeur.dto.JetonDto;
import com.chauffeur.services.JetonService;

@CrossOrigin
@RestController
public class JetonController implements JetonApi {
	
	private final JetonService jetonService;
	
	@Autowired
	public JetonController(JetonService jetonService) {
		this.jetonService = jetonService;
	}

	@Override
	public ResponseEntity<JetonDto> save(JetonDto jetonDto) {
		JetonDto newJetonDto = jetonService.save(jetonDto);
		return new ResponseEntity<>(newJetonDto, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<JetonDto> update(Long idJeton, JetonDto jetonDto) {
		jetonDto.setId(idJeton);
		JetonDto newJetonDto = jetonService.save(jetonDto);
		return new ResponseEntity<>(newJetonDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<JetonDto> findById(Long idJeton) {
		JetonDto newJetonDto = jetonService.findById(idJeton);
		return new ResponseEntity<>(newJetonDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<JetonDto>> findAll() {
		List<JetonDto> jetonDtos = jetonService.findAll();
		return new ResponseEntity<>(jetonDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<JetonDto>> getidJetonOrderByIdDesc() {
		List<JetonDto> jetonDtos = jetonService.findAllJetonsByOrderByIdDesc();
		return new ResponseEntity<>(jetonDtos, HttpStatus.OK);
	}

	@Override
	public BigDecimal getNumbersOfjetons() {
		return jetonService.countNumbersOfJetons();
	}

	@Override
	public void delete(Long idJeton) {
		jetonService.delete(idJeton);
		
	}

}
