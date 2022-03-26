package com.chauffeur.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.dto.JetonDto;
import com.chauffeur.models.Jeton;

public interface JetonService {
	
	JetonDto save(JetonDto jetonDto);
	
	JetonDto update(Long id, JetonDto jetonDto);
	
	JetonDto updateEtatOfJetonDto(String etat, String id);

	JetonDto findById(Long id);

    List<JetonDto> findAll();
    
    List<JetonDto> findAllJetonsByOrderByIdDesc();
    
    List<JetonDto> FindListJetonByCustomerId(Long userId);
    
    BigDecimal countNumbersOfJetons();
    
    void delete(Long id);

}
