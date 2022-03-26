package com.chauffeur.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.JetonApi;
import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.dto.HistoriqueAnnonceDto;
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
		jetonDto.setEtat("ENCOURS");
		jetonDto.setCreatedDate(new Date());
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
	public ResponseEntity<JetonDto> updateEtatOfJeton(String etat, String id) {
		JetonDto newAnnonceDto = jetonService.updateEtatOfJetonDto(etat, id);    
        return new ResponseEntity<>(newAnnonceDto, HttpStatus.OK);
	}

	
	@Override
	public ResponseEntity<JetonDto> getJetonById(Long idJeton) {
		JetonDto newJetonDto = jetonService.findById(idJeton);
		return new ResponseEntity<>(newJetonDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<JetonDto>> getAllJetons() {
		List<JetonDto> jetonDtos = jetonService.findAll();
		return new ResponseEntity<>(jetonDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<JetonDto>> getAllJetonOrderByIdDesc() {
		List<JetonDto> jetonDtos = jetonService.findAllJetonsByOrderByIdDesc();
		return new ResponseEntity<>(jetonDtos, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<JetonDto>> getAllJetonByCustomerIdOrderIdDesc(Long userId) {
		List<JetonDto> jetonDtos = jetonService.FindListJetonByCustomerId(userId);
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
