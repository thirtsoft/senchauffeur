package com.chauffeur.services;

import java.util.List;

import com.chauffeur.dto.TypeAnnonceDto;

public interface TypeAnnonceService {
	
	TypeAnnonceDto save(TypeAnnonceDto typeAnnonceDto);
	
	TypeAnnonceDto update(Long idTypeAnnonce, TypeAnnonceDto typeAnnonceDto);

	TypeAnnonceDto findById(Long id);

    List<TypeAnnonceDto> findAll();
    
    List<TypeAnnonceDto> findByTypeAnnonceByIdDesc();
    
    void delete(Long id);

}
