package com.chauffeur.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.chauffeur.dto.ChauffeurDto;


public interface ChauffeurService {
	
	ChauffeurDto save(ChauffeurDto chauffeurDto);
	
	ChauffeurDto update(Long idChauffeur, ChauffeurDto chauffeurDto);
	
	ChauffeurDto saveChauffeurWithFiles(String chauffeurDto, MultipartFile photoChauffeur, 
			MultipartFile cvChauffeur) throws IOException;

	ChauffeurDto findById(Long id);

    List<ChauffeurDto> findAll();

    void delete(Long id);


}
