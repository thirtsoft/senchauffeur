package com.chauffeur.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import com.chauffeur.dto.ChauffeurDto;

public interface ChauffeurService {
	
	ChauffeurDto save(ChauffeurDto chauffeurDto);
	
	ChauffeurDto update(Long idChauffeur, ChauffeurDto chauffeurDto);
	
	ChauffeurDto saveChauffeurWithFiles(String chauffeurDto, MultipartFile photoChauffeur, 
			MultipartFile cvChauffeur) throws IOException;

	ChauffeurDto findById(Long id);
	
	ChauffeurDto findByReference(String reference);

    List<ChauffeurDto> findAll();
    
    List<ChauffeurDto> findByChauffeurByIdDesc();
    
    List<ChauffeurDto> findListChauffeurBySelected();
    
    List<ChauffeurDto> findListChauffeurByPermis(Long pId);
    
    List<ChauffeurDto> findListChauffeurByKeyword(String keyword);
    
    List<ChauffeurDto> findChauffeurByDisponibility(String disponility);
    
    BigDecimal countNumbersOfChauffeurs();
    
    List<?> countNumberOfChauffeurByMonth();
    
	List<?> countNumberOfChauffeurByYear();
	
    Page<ChauffeurDto> findChauffeurByPageable(Pageable pageable);
    
    Page<ChauffeurDto> findChauffeurByKeywordByPageable(String mc, Pageable pageable);

    Page<ChauffeurDto> findChauffeurByLocalityPageables(Long addId, Pageable pageable);
    
    Page<ChauffeurDto> findChauffeurByPermisPageables(@Param("permId") Long permisId, Pageable pageable);
	
    void delete(Long id);
    
    List<ChauffeurDto> getAllChauffeurDtos(int page, int size);

    List<ChauffeurDto> getAllChauffeurDtosByIdAddress(Long id, int page, int size);
   
    List<ChauffeurDto> getAllChauffeurDtosByKey(String disponibility, int page, int size);

    long getAllChauffeurDtosSize();

    long getChauffeurDtosByAddressIdLength(Long id);
    
    long getChauffeurDtosSizeByKey(String disponibility);
    
}
