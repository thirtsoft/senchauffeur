package com.chauffeur.services;

import java.math.BigDecimal;
import java.util.List;

import com.chauffeur.dto.JetonDto;

public interface JetonService {
	
	JetonDto save(JetonDto jetonDto);
	
	JetonDto update(Long id, JetonDto jetonDto);

	JetonDto findById(Long id);

    List<JetonDto> findAll();
    
    List<JetonDto> findAllJetonsByOrderByIdDesc();
    
    BigDecimal countNumbersOfJetons();
    
    void delete(Long id);

}
