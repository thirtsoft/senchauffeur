package com.chauffeur.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.VilleDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Ville;
import com.chauffeur.repository.VilleRepository;
import com.chauffeur.services.VilleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class VilleServiceImpl implements VilleService {
	
	
    private final VilleRepository villeRepository;

    @Autowired
    public VilleServiceImpl(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

	@Override
	public VilleDto save(VilleDto villeDto) {
		return VilleDto.fromEntityToDto(
				villeRepository.save(
						VilleDto.fromDtoToEntity(villeDto)
                )
        );
	}

	@Override
	public VilleDto update(Long idVille, VilleDto villeDto) {
		if (idVille == null) {
            log.error("Ville Id is null");
            return null;
        }

        Optional<Ville> ville = villeRepository.findById(idVille);

        return Optional.of(VilleDto.fromEntityToDto(ville.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Ville avec l'Id = " + idVille + "n'a été trouvé")
        );
	}

	@Override
	public VilleDto findById(Long id) {
		if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Ville> ville = villeRepository.findById(id);

        return Optional.of(VilleDto.fromEntityToDto(ville.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Ville avec l'Id = " + id + "n'a été trouvé")
        );
	}

	@Override
	public List<VilleDto> findAll() {
		return villeRepository.findAll().stream()
                .map(VilleDto::fromEntityToDto)
                .collect(Collectors.toList());
	}

	@Override
	public List<VilleDto> findByVillesByIdDesc() {
		return villeRepository.findVilleByOrderByIdDesc().stream()
                .map(VilleDto::fromEntityToDto)
                .collect(Collectors.toList());
	}
	
	@Override
	public void delete(Long id) {
		if (id == null) {
            log.error("Ville Id is null");
            return;
        }
		
		villeRepository.deleteById(id);

	
	}

   

}
