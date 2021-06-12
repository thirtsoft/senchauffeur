package com.chauffeur.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.ParticulierDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Particulier;
import com.chauffeur.repository.ParticulierRepository;
import com.chauffeur.services.ParticulierService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ParticulierServiceImpl implements ParticulierService {
	
	@Autowired
    private final ParticulierRepository particulierRepository;

    public ParticulierServiceImpl(ParticulierRepository particulierRepository) {
        this.particulierRepository = particulierRepository;
    }

    @Override
    public ParticulierDto save(ParticulierDto particulierDto) {

        return ParticulierDto.fromEntityToDto(
        		particulierRepository.save(
                		ParticulierDto.fromDtoToEntity(particulierDto)
                )
        );
    }
    
    @Override
	public ParticulierDto update(Long idParticulier, ParticulierDto particulierDto) {
		if (!particulierRepository.existsById(idParticulier)) {
            throw new ResourceNotFoundException("Particulier not found");
        }

        Optional<Particulier> particulier = particulierRepository.findById(idParticulier);

        if (!particulier.isPresent()) {
            throw new ResourceNotFoundException("Particulier not found");
        }

        ParticulierDto particulierDtoResult = ParticulierDto.fromEntityToDto(particulier.get());
        particulierDtoResult.setFirstName(particulierDto.getFirstName());
        particulierDtoResult.setLastName(particulierDto.getLastName());
        particulierDtoResult.setMobileParticulier(particulierDto.getMobileParticulier());
        particulierDtoResult.setMobileParticulier(particulierDto.getMobileParticulier());
        particulierDtoResult.setEmail(particulierDto.getEmail());
        particulierDtoResult.setAddressParticulier(particulierDto.getAddressParticulier());
        particulierDtoResult.setVilleParticulier(particulierDto.getVilleParticulier());
       
        return ParticulierDto.fromEntityToDto(
        		particulierRepository.save(
        				ParticulierDto.fromDtoToEntity(particulierDtoResult)
                )
        );
	}

    @Override
    public ParticulierDto findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Particulier> particulier = particulierRepository.findById(id);

        return Optional.of(ParticulierDto.fromEntityToDto(particulier.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Particulier avec l'Id = " + id + "n'a été trouvé")
        );
    }

    
    @Override
    public List<ParticulierDto> findAll() {
        return particulierRepository.findAll().stream()
                .map(ParticulierDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Particulier Id is null");
            return;
        }
        particulierRepository.deleteById(id);

    }

	

}
