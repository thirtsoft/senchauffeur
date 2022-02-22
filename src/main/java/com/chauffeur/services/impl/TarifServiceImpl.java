package com.chauffeur.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chauffeur.dto.TarifDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Tarif;
import com.chauffeur.repository.TarifRepository;
import com.chauffeur.services.TarifService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class TarifServiceImpl implements TarifService {
	
    private final TarifRepository tarifRepository;

    @Autowired
    public TarifServiceImpl(TarifRepository tarifRepository) {
        this.tarifRepository = tarifRepository;
    }

    @Override
    public TarifDto save(TarifDto tarifDto) {

        return TarifDto.fromEntityToDto(
        		tarifRepository.save(
        				TarifDto.fromDtoToEntity(tarifDto)
                )
        );
    }
    
    @Override
	public TarifDto update(Long idTarif, TarifDto tarifDto) {
		if (!tarifRepository.existsById(idTarif)) {
            throw new ResourceNotFoundException("Tarif not found");
        }

		Optional<Tarif> tarifOptional = tarifRepository.findById(idTarif);
		
        if (!tarifOptional.isPresent()) {
            throw new ResourceNotFoundException("Tarif not found");
        }

        TarifDto tarifDtoResult = TarifDto.fromEntityToDto(tarifOptional.get());
        tarifDtoResult.setReference(tarifDto.getReference());
        tarifDtoResult.setMontantTarif(tarifDto.getMontantTarif());
        tarifDtoResult.setDescription(tarifDto.getDescription());
        tarifDtoResult.setAnnonceDto(tarifDto.getAnnonceDto());
       
        return TarifDto.fromEntityToDto(
        		tarifRepository.save(
        				TarifDto.fromDtoToEntity(tarifDtoResult)
                )
        );
	}


    @Override
    public TarifDto findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Tarif> tarifOptional = tarifRepository.findById(id);

        return Optional.of(TarifDto.fromEntityToDto(tarifOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Tarif avec l'Id = " + id + "n'a été trouvé")
        );
    }
    
    @Override
	public TarifDto findByReference(String reference) {
    	if (!StringUtils.hasLength(reference)) {
            log.error("Tarif REFERENCE is null");
        }

    	Optional<Tarif> tarifOptional = tarifRepository.findTarifByReference(reference);

        return Optional.of(TarifDto.fromEntityToDto(tarifOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Tarif avec l'Id = " + reference + "n'a été trouvé")
        );

	}

    
    @Override
    public List<TarifDto> findAll() {
        return tarifRepository.findAll().stream()
                .map(TarifDto::fromEntityToDto)
                .collect(Collectors.toList());
    }
    
    @Override
	public List<TarifDto> findByTarifByIdDesc() {
    	 return tarifRepository.findTarifByOrderByIdDesc().stream()
                 .map(TarifDto::fromEntityToDto)
                 .collect(Collectors.toList());
	}
	
	@Override
	public List<TarifDto> findListTarifDtoByKeyword(String keyword) {
		if (keyword == null) {
            log.error("Tarif not found");
        }
        return tarifRepository.findTarifByKeyword(keyword).stream()
                .map(TarifDto::fromEntityToDto)
                .collect(Collectors.toList());
	}

	@Override
	public List<TarifDto> findListTarifDtoByAnnonce(Long pId) {
		return tarifRepository.findTarifByAnnonce(pId).stream()
                .map(TarifDto::fromEntityToDto)
                .collect(Collectors.toList());
	}
	
	@Override
	public Page<TarifDto> findTarifByPageable(Pageable pageable) {
		return tarifRepository.findAll(pageable)
                .map(TarifDto::fromEntityToDto);
	}


	@Override
	public Page<TarifDto> findTarifByAnnonceByPageable(Long annonceId, Pageable pageable) {
		return tarifRepository.findTarifByAnnoncePageables(annonceId, pageable)
                .map(TarifDto::fromEntityToDto);
	}
	
	
	@Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Annonce Id is null");
            return;
        }
        tarifRepository.deleteById(id);

    }

	
}
