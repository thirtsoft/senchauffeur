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
	
	@Autowired
    private final PermisRepository permisRepository;

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

}
