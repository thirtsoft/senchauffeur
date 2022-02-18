package com.chauffeur.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.HistoriqueAnnonceApi;
import com.chauffeur.dto.HistoriqueAnnonceDto;
import com.chauffeur.services.HistoriqueAnnonceService;

@CrossOrigin
@RestController
public class HistoriqueAnnonceController implements HistoriqueAnnonceApi {
	
	private HistoriqueAnnonceService  historiqueAnnonceService;
	
	@Autowired
	public HistoriqueAnnonceController(HistoriqueAnnonceService  historiqueAnnonceService) {
		this.historiqueAnnonceService = historiqueAnnonceService;
	}

	@Override
	public ResponseEntity<HistoriqueAnnonceDto> save(HistoriqueAnnonceDto historiqueAnnonceDto) {
		HistoriqueAnnonceDto historiqueAnnonceResultDto = historiqueAnnonceService.save(historiqueAnnonceDto);
		return ResponseEntity.ok(historiqueAnnonceResultDto);
	}

	@Override
	public ResponseEntity<HistoriqueAnnonceDto> update(Long idHistoriqueAnnonce,
			HistoriqueAnnonceDto historiqueAnnonceDto) {
		historiqueAnnonceDto.setId(idHistoriqueAnnonce);
		return ResponseEntity.ok(historiqueAnnonceService.save(historiqueAnnonceDto));
	}

	@Override
	public ResponseEntity<HistoriqueAnnonceDto> findById(Long idHistoriqueAnnonce) {
		return ResponseEntity.ok(historiqueAnnonceService.findById(idHistoriqueAnnonce));
	}

	@Override
	public ResponseEntity<List<HistoriqueAnnonceDto>> findAll() {
		List<HistoriqueAnnonceDto> historiqueAnnonceDtos = historiqueAnnonceService.findAll();
		return ResponseEntity.ok(historiqueAnnonceDtos);
	}

	@Override
	public ResponseEntity<List<HistoriqueAnnonceDto>> getListHistoriqueAnnoncesOrderByIdDesc() {
		List<HistoriqueAnnonceDto> historiqueAnnonceDtos = historiqueAnnonceService.findHistoriqueAnnonceByOrderByIdDesc();
		return ResponseEntity.ok(historiqueAnnonceDtos);
	}

	@Override
	public BigDecimal getNumbersOfhistoriqueAnnonces() {
		return historiqueAnnonceService.countNumbersOfHistoriqueAnnonces();
	}

	@Override
	public void delete(Long idHistoriqueAnnonce) {
		historiqueAnnonceService.delete(idHistoriqueAnnonce);
	}

	

}
