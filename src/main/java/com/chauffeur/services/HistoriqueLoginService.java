package com.chauffeur.services;

import java.math.BigDecimal;
import java.util.List;


import com.chauffeur.dto.HistoriqueLoginDto;

public interface HistoriqueLoginService {
	
	HistoriqueLoginDto save(HistoriqueLoginDto historiqueLoginDto);
	
	HistoriqueLoginDto update(Long idAnnonce, HistoriqueLoginDto historiqueLoginDto);

	HistoriqueLoginDto findById(Long id);

    List<HistoriqueLoginDto> findAll();
    
    List<HistoriqueLoginDto> findHistoriqueLoginByOrderByIdDesc();
    
    BigDecimal countNumbersOfHistoriqueLogins();
    
    void delete(Long id);


}
