package com.chauffeur.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.PermisDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Permis;
import com.chauffeur.repository.PermisRepository;
import com.chauffeur.services.PermisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PermisServiceImpl implements PermisService {
	
	
    private final PermisRepository permisRepository;

    @Autowired
    public PermisServiceImpl(PermisRepository permisRepository) {
        this.permisRepository = permisRepository;
    }

    @Override
    public PermisDto save(PermisDto permisDto) {

        return PermisDto.fromEntityToDto(
        		permisRepository.save(
                		PermisDto.fromDtoToEntity(permisDto)
                )
        );
    }

    @Override
    public PermisDto findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Permis> permis = permisRepository.findById(id);

        return Optional.of(PermisDto.fromEntityToDto(permis.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Permis avec l'Id = " + id + "n'a été trouvé")
        );
    }

    
    @Override
    public List<PermisDto> findAll() {
        return permisRepository.findAll().stream()
                .map(PermisDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Permis Id is null");
            return;
        }
        permisRepository.deleteById(id);

    }

	@Override
	public PermisDto update(Long idPermis, PermisDto permisDto) {
		if (!permisRepository.existsById(idPermis)) {
            throw new ResourceNotFoundException("Permis not found");
        }

        Optional<Permis> permis = permisRepository.findById(idPermis);

        if (!permis.isPresent()) {
            throw new ResourceNotFoundException("Permis not found");
        }

        PermisDto permisDtoResult = PermisDto.fromEntityToDto(permis.get());
        permisDtoResult.setTypePermis(permisDto.getTypePermis());
        permisDtoResult.setDesignation(permisDto.getDesignation());
        permisDtoResult.setValidite(permisDto.getValidite());
       
        return PermisDto.fromEntityToDto(
        		permisRepository.save(
        				PermisDto.fromDtoToEntity(permisDtoResult)
                )
        );
	}

}
