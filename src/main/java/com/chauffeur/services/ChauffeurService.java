package com.chauffeur.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    List<ChauffeurDto> findListChauffeurByPermis(Long pId);
    
    List<ChauffeurDto> findListChauffeurByKeyword(String keyword);
    
    BigDecimal countNumbersOfChauffeurs();

    Page<ChauffeurDto> findChauffeurByPageable(Pageable pageable);

    void delete(Long id);


}
