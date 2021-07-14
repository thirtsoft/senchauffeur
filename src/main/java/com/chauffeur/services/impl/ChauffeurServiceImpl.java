package com.chauffeur.services.impl;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Chauffeur;
import com.chauffeur.repository.ChauffeurRepository;
import com.chauffeur.services.ChauffeurService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        chauffeurDtoResult.setAddresseDto(chauffeurDto.getAddresseDto());
        
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

	@Override
	public ChauffeurDto saveChauffeurWithFiles(String chauffeurDto, MultipartFile photoChauffeur,
			MultipartFile cvChauffeur) throws IOException {
		ChauffeurDto chauffeurDtoMapper = new ObjectMapper().readValue(chauffeurDto, ChauffeurDto.class);
        System.out.println(chauffeurDtoMapper);

        chauffeurDtoMapper.setPhotoChauffeur(photoChauffeur.getOriginalFilename());
        
        chauffeurDtoMapper.setCvChauffeur(cvChauffeur.getOriginalFilename());

        return ChauffeurDto.fromEntityToDto(
        		chauffeurRepository.save(
        				ChauffeurDto.fromDtoToEntity(chauffeurDtoMapper)
                )
        );
	}

	@Override
	public List<ChauffeurDto> findListChauffeurByKeyword(String keyword) {
		if (keyword == null) {
            log.error("Article not found");
        }
		
        return chauffeurRepository.findChauffeurByKeyword(keyword).stream()
                .map(ChauffeurDto::fromEntityToDto)
                .collect(Collectors.toList());
	}

	@Override
	public Page<ChauffeurDto> findChauffeurByPageable(Pageable pageable) {
		return chauffeurRepository.findAllChauffeurByPageable(pageable)
                .map(ChauffeurDto::fromEntityToDto);
	}

	@Override
	public List<ChauffeurDto> findListChauffeurByPermis(Long pId) {
		return chauffeurRepository.findChauffeurByPermis(pId).stream()
				.map(ChauffeurDto::fromEntityToDto)
                .collect(Collectors.toList());
	}

	@Override
	public ChauffeurDto findByReference(String reference) {
		if (!StringUtils.hasLength(reference)) {
            log.error("Annonce REFERENCE is null");
        }

        Optional<Chauffeur> chauffeur = chauffeurRepository.findChauffeurByReference(reference);

        return Optional.of(ChauffeurDto.fromEntityToDto(chauffeur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Annonce avec l'Id = " + reference + "n'a été trouvé")
        );
	}

	@Override
	public BigDecimal countNumbersOfChauffeurs() {
		return chauffeurRepository.countNumberOfChauffeurs();
	}

	

}
