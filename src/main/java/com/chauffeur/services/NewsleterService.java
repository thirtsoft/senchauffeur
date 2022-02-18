package com.chauffeur.services;

import java.math.BigDecimal;
import java.util.List;

import com.chauffeur.dto.NewsleterDto;

public interface NewsleterService {
	
	NewsleterDto save(NewsleterDto newsleterDto);
	
	NewsleterDto update(Long id, NewsleterDto newsleterDto);

	NewsleterDto findById(Long id);

    List<NewsleterDto> findAll();
    
    List<NewsleterDto> findAllNewsletersByOrderByIdDesc();
    
    BigDecimal countNumbersOfNewsleters();
    
    void delete(Long id);

}
