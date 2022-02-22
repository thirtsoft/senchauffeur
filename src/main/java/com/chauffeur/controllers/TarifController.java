package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.TarifApi;
import com.chauffeur.dto.PermisDto;
import com.chauffeur.dto.TarifDto;
import com.chauffeur.services.TarifService;

@RestController
@CrossOrigin
public class TarifController implements TarifApi {
	
	private TarifService tarifService;
	
	@Autowired
	public TarifController(TarifService tarifService) {
		this.tarifService = tarifService;
	}

	@Override
	public ResponseEntity<TarifDto> save(TarifDto tarifDto) {
		return ResponseEntity.ok(tarifService.save(tarifDto));
	}

	@Override
	public ResponseEntity<TarifDto> update(Long idTarif, TarifDto tarifDto) {
		tarifDto.setId(idTarif);
		return ResponseEntity.ok(tarifService.save(tarifDto));
	}

	@Override
	public ResponseEntity<TarifDto> findById(Long idTarif) {
		return ResponseEntity.ok(tarifService.findById(idTarif));
	}

	@Override
	public ResponseEntity<TarifDto> findByReference(String reference) {
		return ResponseEntity.ok(tarifService.findByReference(reference));
	}

	@Override
	public List<TarifDto> findAll() {
		return tarifService.findAll();
	}

	@Override
	public void delete(Long idTarif) {
		tarifService.delete(idTarif);
		
	}

	@Override
	public List<TarifDto> getListTarifByKeyword(String reference) {
		return tarifService.findListTarifDtoByKeyword("%" + reference + "%");
	}

	@Override
	public List<TarifDto> getListTarifByAnnonce(Long pId) {
		return tarifService.findListTarifDtoByAnnonce(pId);
	}

	@Override
	public Page<TarifDto> getListTarifByPageable(int page, int size) {
		final Pageable pageable = PageRequest.of(page, size);
        return tarifService.findTarifByPageable(pageable);
	}

	@Override
	public Page<TarifDto> getTarifByAnnoncePageables(Long tarifId, int page, int size) {
		final Pageable pageable = PageRequest.of(page, size);
        return tarifService.findTarifByAnnonceByPageable(tarifId, pageable);
	}

	@Override
	public ResponseEntity<List<TarifDto>> getdAllTarifsOrderByIdDesc() {
		List<TarifDto> tarifDtoList = tarifService.findByTarifByIdDesc();
	    return new ResponseEntity<>(tarifDtoList, HttpStatus.OK);
	}

	
	
}
