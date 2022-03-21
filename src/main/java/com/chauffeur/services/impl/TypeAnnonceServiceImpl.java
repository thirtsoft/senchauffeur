package com.chauffeur.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.TypeAnnonceDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.TypeAnnonce;
import com.chauffeur.repository.TypeAnnonceRepository;
import com.chauffeur.services.TypeAnnonceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class TypeAnnonceServiceImpl implements TypeAnnonceService {
	
	@Autowired
    private final TypeAnnonceRepository typeAnnonceRepository;

	public TypeAnnonceServiceImpl(TypeAnnonceRepository typeAnnonceRepository) {
		this.typeAnnonceRepository = typeAnnonceRepository;
	}

	@Override
	public TypeAnnonceDto save(TypeAnnonceDto typeAnnonceDto) {
		
		return TypeAnnonceDto.fromEntityToDto(
				typeAnnonceRepository.save(
						TypeAnnonceDto.fromDtoToEntity(typeAnnonceDto)
                )
        );
	}

	@Override
	public TypeAnnonceDto update(Long idTypeAnnonce, TypeAnnonceDto typeAnnonceDto) {
		if (!typeAnnonceRepository.existsById(idTypeAnnonce)) {
            throw new ResourceNotFoundException("TypeAnnonce not found");
        }

        Optional<TypeAnnonce> typeAnnonceOptional = typeAnnonceRepository.findById(idTypeAnnonce);

        if (!typeAnnonceOptional.isPresent()) {
            throw new ResourceNotFoundException("TypeAnnonce not found");
        }
        
        TypeAnnonceDto typeAnnonceDtoResult = TypeAnnonceDto.fromEntityToDto(typeAnnonceOptional.get());
        typeAnnonceDtoResult.setCode(typeAnnonceDto.getCode());
        typeAnnonceDtoResult.setLibelle(typeAnnonceDto.getLibelle());
        typeAnnonceDtoResult.setCreatedDate(typeAnnonceDto.getCreatedDate());
        
        return TypeAnnonceDto.fromEntityToDto(
				typeAnnonceRepository.save(
						TypeAnnonceDto.fromDtoToEntity(typeAnnonceDtoResult)
                )
        );
        
		
     
	}

	@Override
	public TypeAnnonceDto findById(Long id) {
		if (id == null) {
            log.error("TypeAnnonce Id is null");
            return null;
        }
		
		Optional<TypeAnnonce> typeAnnonceOptional = typeAnnonceRepository.findById(id);
		
		 return Optional.of(TypeAnnonceDto.fromEntityToDto(typeAnnonceOptional.get())).orElseThrow(() ->
		 		new ResourceNotFoundException(
		 				"Aucnun TypeAnnonce avec l'Id = " + id + "n'a été trouvé")
         );
	
	}

	@Override
	public List<TypeAnnonceDto> findAll() {
		return typeAnnonceRepository.findAll().stream()
                .map(TypeAnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
	}

	@Override
	public List<TypeAnnonceDto> findByTypeAnnonceByIdDesc() {
		return typeAnnonceRepository.findTypeAnnonceByOrderByIdDesc().stream()
                .map(TypeAnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
	}

	@Override
	public void delete(Long id) {
		if (id == null) {
            log.error("TypeAnnonce Id is null");
            return;
        }
		
		typeAnnonceRepository.deleteById(id);
		
	}

}
