package com.chauffeur.services;

import java.math.BigDecimal;
import java.util.List;

import com.chauffeur.dto.HistoriqueAnnonceDto;

public interface HistoriqueAnnonceService {
	
	HistoriqueAnnonceDto save(HistoriqueAnnonceDto historiqueAnnonceDto);
	
	HistoriqueAnnonceDto update(Long id, HistoriqueAnnonceDto historiqueAnnonceDto);

	HistoriqueAnnonceDto findById(Long id);

    List<HistoriqueAnnonceDto> findAll();
    
    List<HistoriqueAnnonceDto> findHistoriqueAnnonceByOrderByIdDesc();
    
    BigDecimal countNumbersOfHistoriqueAnnonces();
    
    void delete(Long id);

}
