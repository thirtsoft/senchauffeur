package com.chauffeur.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.HistoriqueLoginApi;
import com.chauffeur.dto.HistoriqueLoginDto;
import com.chauffeur.services.HistoriqueLoginService;

@RestController
@CrossOrigin
public class HistoriqueLoginController implements HistoriqueLoginApi {
	
	private HistoriqueLoginService  historiqueLoginService;
	
	@Autowired
	public HistoriqueLoginController(HistoriqueLoginService historiqueLoginService) {
		this.historiqueLoginService = historiqueLoginService;
	}

	@Override
	public ResponseEntity<HistoriqueLoginDto> save(HistoriqueLoginDto historiqueLoginDto) {
		HistoriqueLoginDto historiqueLoginResultDto = historiqueLoginService.save(historiqueLoginDto);
		return ResponseEntity.ok(historiqueLoginResultDto);
	}

	@Override
	public ResponseEntity<HistoriqueLoginDto> update(Long idHistoriqueLogin, HistoriqueLoginDto historiqueLoginDto) {
		historiqueLoginDto.setId(idHistoriqueLogin);
		return ResponseEntity.ok(historiqueLoginService.save(historiqueLoginDto));
	}

	@Override
	public ResponseEntity<HistoriqueLoginDto> findById(Long idHistoriqueLogin) {
		return ResponseEntity.ok(historiqueLoginService.findById(idHistoriqueLogin));
	}

	@Override
	public ResponseEntity<List<HistoriqueLoginDto>> findAll() {
		List<HistoriqueLoginDto> historiqueLoginDtos = historiqueLoginService.findAll();
		return ResponseEntity.ok(historiqueLoginDtos);
	}

	@Override
	public ResponseEntity<List<HistoriqueLoginDto>> getidHistoriqueLoginOrderByIdDesc() {
		List<HistoriqueLoginDto> historiqueLoginDtos = historiqueLoginService.findHistoriqueLoginByOrderByIdDesc();
		return ResponseEntity.ok(historiqueLoginDtos);
	}

	@Override
	public BigDecimal getNumbersOfHistoriqueLogins() {
		return historiqueLoginService.countNumbersOfHistoriqueLogins();
	}

	@Override
	public void delete(Long idHistoriqueLogin) {
		historiqueLoginService.delete(idHistoriqueLogin);
		
	}

}
