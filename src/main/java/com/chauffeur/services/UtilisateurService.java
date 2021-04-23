package com.chauffeur.services;

import java.util.List;

import com.chauffeur.dto.UtilisateurDto;

public interface UtilisateurService {
	
	UtilisateurDto save(UtilisateurDto utilisateurDto);

	UtilisateurDto findById(Long id);

    List<UtilisateurDto> findAll();

    void delete(Long id);

}
