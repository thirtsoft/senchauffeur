package com.chauffeur.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Chauffeur;
import com.chauffeur.repository.ChauffeurRepository;
import com.chauffeur.services.ChauffeurService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ChauffeurServiceImpl implements ChauffeurService {
	
	@Autowired
    private final ChauffeurRepository chauffeurRepository;

    public ChauffeurServiceImpl(ChauffeurRepository chauffeurRepository) {
        this.chauffeurRepository = chauffeurRepository;
    }

    @Override
    public ChauffeurDto save(ChauffeurDto chauffeurDto) {

        return ChauffeurDto.fromEntityToDto(
        		chauffeurRepository.save(
                		ChauffeurDto.fromDtoToEntity(chauffeurDto)
                )
        );
    }
    
    @Override
	public ChauffeurDto update(Long idChauffeur, ChauffeurDto chauffeurDto) {
		if (!chauffeurRepository.existsById(idChauffeur)) {
            throw new ResourceNotFoundException("Chauffeur not found");
        }

        Optional<Chauffeur> chauffeur = chauffeurRepository.findById(idChauffeur);

        if (!chauffeur.isPresent()) {
            throw new ResourceNotFoundException("Chauffeur not found");
        }

        ChauffeurDto chauffeurDtoResult = ChauffeurDto.fromEntityToDto(chauffeur.get());
        chauffeurDtoResult.setReference(chauffeurDto.getReference());
        chauffeurDtoResult.setFirstName(chauffeurDto.getFirstName());
        chauffeurDtoResult.setLastName(chauffeurDto.getLastName());
        chauffeurDtoResult.setSexe(chauffeurDto.getSexe());
        chauffeurDtoResult.setAddressActuel(chauffeurDto.getAddressActuel());
        chauffeurDtoResult.setEmail(chauffeurDto.getEmail());
        chauffeurDtoResult.setPhoneChauffeur(chauffeurDto.getPhoneChauffeur());
        chauffeurDtoResult.setPhotoChauffeur(chauffeurDto.getPhotoChauffeur());
        chauffeurDtoResult.setPretentionSalaire(chauffeurDto.getPretentionSalaire());
        chauffeurDtoResult.setCvChauffeur(chauffeurDto.getCvChauffeur());
        chauffeurDtoResult.setNbreAnneeExperience(chauffeurDto.getNbreAnneeExperience());
        chauffeurDtoResult.setMobilite(chauffeurDto.getMobilite());
        chauffeurDtoResult.setPermisDto(chauffeurDto.getPermisDto());
        
        return ChauffeurDto.fromEntityToDto(
        		chauffeurRepository.save(
        				ChauffeurDto.fromDtoToEntity(chauffeurDtoResult)
                )
        );
	}

    @Override
    public ChauffeurDto findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Chauffeur> chauffeur = chauffeurRepository.findById(id);

        return Optional.of(ChauffeurDto.fromEntityToDto(chauffeur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun chauffeur avec l'Id = " + id + "n'a été trouvé")
        );
    }

    
    @Override
    public List<ChauffeurDto> findAll() {
        return chauffeurRepository.findAll().stream()
                .map(ChauffeurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("chauffeur Id is null");
            return;
        }
        chauffeurRepository.deleteById(id);

    }

	

}
