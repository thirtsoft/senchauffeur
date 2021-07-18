package com.chauffeur.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.RecruteurDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Recruteur;
import com.chauffeur.repository.RecruteurRepository;
import com.chauffeur.services.RecruteurService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class RecruteurServiceImpl implements RecruteurService {
	
    private final RecruteurRepository recruteurRepository;

    @Autowired
    public RecruteurServiceImpl(RecruteurRepository recruteurRepository) {
        this.recruteurRepository = recruteurRepository;
    }

    @Override
    public RecruteurDto save(RecruteurDto recruteurDto) {

        return RecruteurDto.fromEntityToDto(
        		recruteurRepository.save(
                		RecruteurDto.fromDtoToEntity(recruteurDto)
                )
        );
    }

    @Override
    public RecruteurDto findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Recruteur> recruteur = recruteurRepository.findById(id);

        return Optional.of(RecruteurDto.fromEntityToDto(recruteur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Recruteur avec l'Id = " + id + "n'a été trouvé")
        );
    }

    
    @Override
    public List<RecruteurDto> findAll() {
        return recruteurRepository.findAll().stream()
                .map(RecruteurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Recruteur Id is null");
            return;
        }
        recruteurRepository.deleteById(id);

    }

	@Override
	public RecruteurDto update(Long idRecruteur, RecruteurDto recruteurDto) {
		if (!recruteurRepository.existsById(idRecruteur)) {
            throw new ResourceNotFoundException("Recruteur not found");
        }

        Optional<Recruteur> recruteur = recruteurRepository.findById(idRecruteur);

        if (!recruteur.isPresent()) {
            throw new ResourceNotFoundException("Recruteur not found");
        }

        RecruteurDto recruteurDtoResult = RecruteurDto.fromEntityToDto(recruteur.get());
        recruteurDtoResult.setFirstName(recruteurDto.getFirstName());
        recruteurDtoResult.setLastName(recruteurDto.getLastName());
        recruteurDtoResult.setAddressRecruteur(recruteurDto.getAddressRecruteur());
        recruteurDtoResult.setNomEntreprise(recruteurDto.getNomEntreprise());
        recruteurDtoResult.setPhoneRecruteur(recruteurDto.getPhoneRecruteur());
        recruteurDtoResult.setEmail(recruteurDto.getEmail());
        recruteurDtoResult.setSecteurActivite(recruteurDto.getSecteurActivite());
        recruteurDtoResult.setVilleRecruteur(recruteurDto.getVilleRecruteur());
        recruteurDtoResult.setWebsite(recruteurDto.getVilleRecruteur());
        
       
        return RecruteurDto.fromEntityToDto(
        		recruteurRepository.save(
        				RecruteurDto.fromDtoToEntity(recruteurDtoResult)
                )
        );
	}

	@Override
	public BigDecimal countNumbersOfRecruteurs() {
		return recruteurRepository.countNumberOfRecruteurs();
	}

}
