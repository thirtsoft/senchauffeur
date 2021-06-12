package com.chauffeur.services;

import java.util.List;


import com.chauffeur.dto.AnnonceDto;

public interface AnnonceService {
	
	AnnonceDto save(AnnonceDto annonceDto);
	
	AnnonceDto update(Long idAnnonce, AnnonceDto annonceDto);

	AnnonceDto findById(Long id);

    List<AnnonceDto> findAll();

    void delete(Long id);

}
