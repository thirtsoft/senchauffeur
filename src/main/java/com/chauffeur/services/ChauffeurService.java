package com.chauffeur.services;

import java.util.List;

import com.chauffeur.dto.ChauffeurDto;


public interface ChauffeurService {
	
	ChauffeurDto save(ChauffeurDto chauffeurDto);
	
	ChauffeurDto update(Long idChauffeur, ChauffeurDto chauffeurDto);

	ChauffeurDto findById(Long id);

    List<ChauffeurDto> findAll();

    void delete(Long id);


}
