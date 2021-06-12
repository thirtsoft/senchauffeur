package com.chauffeur.services;

import java.util.List;

import com.chauffeur.dto.RecruteurDto;

public interface RecruteurService {
	
	RecruteurDto save(RecruteurDto recruteurDto);
	
	RecruteurDto update(Long idRecruteur, RecruteurDto recruteurDto);

	RecruteurDto findById(Long id);

    List<RecruteurDto> findAll();

    void delete(Long id);

}
