package com.chauffeur.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chauffeur.dto.AnnonceDto;

public interface AnnonceService {
	
	AnnonceDto save(AnnonceDto annonceDto);
	
	AnnonceDto update(Long idAnnonce, AnnonceDto annonceDto);

	AnnonceDto findById(Long id);
	
	AnnonceDto findByReference(String reference);

    List<AnnonceDto> findAll();
    
    List<AnnonceDto> findListAnnonceByKeyword(String keyword);
    
    List<AnnonceDto> findListAnnonceByPermis(Long pId);
    
    BigDecimal countNumbersOfAnnonces();

    Page<AnnonceDto> findAnnonceByPageable(Pageable pageable);

    void delete(Long id);

}
