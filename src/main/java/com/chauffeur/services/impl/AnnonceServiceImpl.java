package com.chauffeur.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.enumeration.StatusAnnonce;
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
	public AnnonceDto findByReference(String reference) {
    	if (!StringUtils.hasLength(reference)) {
            log.error("Annonce REFERENCE is null");
        }

        Optional<Annonce> annonce = annonceRepository.findAnnonceByReference(reference);

        return Optional.of(AnnonceDto.fromEntityToDto(annonce.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Annonce avec l'Id = " + reference + "n'a été trouvé")
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

	@Override
	public AnnonceDto update(Long idAnnonce, AnnonceDto annonceDto) {
		if (!annonceRepository.existsById(idAnnonce)) {
            throw new ResourceNotFoundException("Annonce not found");
        }

        Optional<Annonce> annonce = annonceRepository.findById(idAnnonce);

        if (!annonce.isPresent()) {
            throw new ResourceNotFoundException("Annonce not found");
        }

        AnnonceDto annonceDtoResult = AnnonceDto.fromEntityToDto(annonce.get());
        annonceDtoResult.setReference(annonceDto.getReference());
        annonceDtoResult.setLibelle(annonceDto.getLibelle());
        annonceDtoResult.setModeCandidature(annonceDto.getModeCandidature());
        annonceDtoResult.setTime(annonceDto.getTime());
        annonceDtoResult.setAnneeExperience(annonceDto.getAnneeExperience());
        annonceDtoResult.setDescription(annonceDto.getDescription());
        annonceDtoResult.setLieuPoste(annonceDto.getLieuPoste());
        annonceDtoResult.setSalaire(annonceDto.getSalaire());
        annonceDtoResult.setStatusAnnonce(StatusAnnonce.ENCOURS);
        annonceDtoResult.setDateCandidature(annonceDto.getDateCandidature());
        annonceDtoResult.setDateCloture(annonceDto.getDateCloture());
        annonceDtoResult.setPermisDto(annonceDto.getPermisDto());
        annonceDtoResult.setRecruteurDto(annonceDto.getRecruteurDto());
        annonceDtoResult.setAddresseDto(annonceDto.getAddresseDto());
       
        return AnnonceDto.fromEntityToDto(
        		annonceRepository.save(
        				AnnonceDto.fromDtoToEntity(annonceDtoResult)
                )
        );
	}

	@Override
	public List<AnnonceDto> findListAnnonceByKeyword(String keyword) {
		if (keyword == null) {
            log.error("Annonce not found");
        }
        return annonceRepository.findAnnonceByKeyword(keyword).stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
	}
	
	@Override
	public List<AnnonceDto> findListAnnonceByLibelle(String libelle) {
		if (libelle == null) {
            log.error("Annonce not found");
        }
        return annonceRepository.findListAnnonceByLibelle(libelle)
        		.stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
	}

	@Override
	public Page<AnnonceDto> findAnnonceByPageable(Pageable pageable) {
		return annonceRepository.findAll(pageable)
                .map(AnnonceDto::fromEntityToDto);
	}

	@Override
	public List<AnnonceDto> findListAnnonceByPermis(Long pId) {
		return annonceRepository.findAnnonceByPermis(pId).stream()
                .map(AnnonceDto::fromEntityToDto)
                .collect(Collectors.toList());
	}

	@Override
	public BigDecimal countNumbersOfAnnonces() {
		return annonceRepository.countNumberOfAnnonces();
	}

	@Override
	public Page<AnnonceDto> findAnnonceByPermisByPageable(Long permisId, Pageable pageable) {
		return annonceRepository.findAnnonceByPermisPageables(permisId, pageable)
                .map(AnnonceDto::fromEntityToDto);
	}



	
}
