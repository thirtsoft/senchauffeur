package com.chauffeur.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Annonce;
import com.chauffeur.repository.AnnonceRepository;
import com.chauffeur.services.AnnonceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AnnonceServiceImpl implements AnnonceService {
	
	@Autowired
    private final AnnonceRepository annonceRepository;

    public AnnonceServiceImpl(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    @Override
    public AnnonceDto save(AnnonceDto annonceDto) {

        return AnnonceDto.fromEntityToDto(
        		annonceRepository.save(
        				AnnonceDto.fromDtoToEntity(annonceDto)
                )
        );
    }

    @Override
    public AnnonceDto findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Annonce> annonce = annonceRepository.findById(id);

        return Optional.of(AnnonceDto.fromEntityToDto(annonce.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Annonce avec l'Id = " + id + "n'a été trouvé")
        );
    }

    
    @Override
    public List<AnnonceDto> findAll() {
        return annonceRepository.findAll().stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Annonce Id is null");
            return;
        }
        annonceRepository.deleteById(id);

    }

}
