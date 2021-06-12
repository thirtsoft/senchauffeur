package com.chauffeur.services;

import java.util.List;

import com.chauffeur.dto.ParticulierDto;

public interface ParticulierService {
	
	ParticulierDto save(ParticulierDto particulierDto);
	
	ParticulierDto update(Long idParticulier, ParticulierDto particulierDto);

	ParticulierDto findById(Long id);

    List<ParticulierDto> findAll();

    void delete(Long id);

}
