package com.chauffeur.services;

import java.util.List;

import com.chauffeur.dto.VilleDto;

public interface VilleService {
	
	VilleDto save(VilleDto villeDto);
	
	VilleDto update(Long idVille, VilleDto villeDto);

	VilleDto findById(Long id);

    List<VilleDto> findAll();
    
    List<VilleDto> findByVillesByIdDesc();

    void delete(Long id);

}
