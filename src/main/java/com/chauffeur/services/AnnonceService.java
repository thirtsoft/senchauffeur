package com.chauffeur.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.models.Annonce;

public interface AnnonceService {
	
	AnnonceDto save(AnnonceDto annonceDto);
	
	AnnonceDto update(Long idAnnonce, AnnonceDto annonceDto);
	
    AnnonceDto updateStatusOfAnnonce(String status, String id);

	AnnonceDto findById(Long id);
	
	AnnonceDto findByReference(String reference);
	
	AnnonceDto FindAnnonceByCustomerId(Long userId);
	
    BigDecimal countNumbersOfAnnonces();

    BigDecimal countNumberOfAnnoncesInMonth();

    BigDecimal countNumberOfAnnonceByStatusPending();
    
    List<AnnonceDto> findAll();
    
    List<AnnonceDto> findByAnnonceByIdDesc();
    
    List<AnnonceDto> findListAnnonceBySelected();
    
    List<AnnonceDto> FindListAnnonceByCustomerId(Long userId);
    
    List<AnnonceDto> findListAnnonceByKeyword(String keyword);
    
    List<AnnonceDto> findListAnnonceByLibelle(String libelle);
    
    List<AnnonceDto> findListAnnonceByPermis(Long pId);
    
    List<AnnonceDto> find5LatestRecordsByOrderByIdDesc();
    
	List<AnnonceDto> findListAnnonceByStatusEncours();
   
    List<AnnonceDto> findListAnnonceByStatusPending();

    List<AnnonceDto> findListAnnonceByStatusValid();
    
    List<AnnonceDto> findListAnnonceByStatusRejet();

    List<?> countNumberTotalOfAnnonceByMonth();
    
    List<?> countNumberTotalOfAnnonceByYear();
    
    Page<AnnonceDto> findAnnonceByPageable(Pageable pageable);
    
    Page<AnnonceDto> findAnnonceByPermisByPageable(Long permisId, Pageable pageable);
    
    void delete(Long id);


}
