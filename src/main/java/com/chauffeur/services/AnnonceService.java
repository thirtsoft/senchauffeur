package com.chauffeur.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chauffeur.dto.AnnonceDto;

public interface AnnonceService {
	
	AnnonceDto save(AnnonceDto annonceDto);
	
	AnnonceDto update(Long idAnnonce, AnnonceDto annonceDto);

	AnnonceDto findById(Long id);

    List<AnnonceDto> findAll();
    
    List<AnnonceDto> findListAnnonceByKeyword(String keyword);


    Page<AnnonceDto> findAnnonceByPageable(Pageable pageable);

    void delete(Long id);

}
