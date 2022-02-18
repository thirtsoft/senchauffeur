package com.chauffeur.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.JetonDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Jeton;
import com.chauffeur.repository.JetonRepository;
import com.chauffeur.services.JetonService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class JetonServiceImpl implements JetonService {
	 
	private final JetonRepository jetonRepository;
	
	@Autowired
	public JetonServiceImpl(JetonRepository jetonRepository) {
		this.jetonRepository = jetonRepository;
	}

	@Override
	public JetonDto save(JetonDto jetonDto) {
		return JetonDto.fromEntityToDto(
				jetonRepository.save(
						JetonDto.fromDtoToEntity(jetonDto)
	                )
	        );
	}

	@Override
	public JetonDto update(Long id, JetonDto jetonDto) {
		if (!jetonRepository.existsById(id)) {
            throw new ResourceNotFoundException("Jeton not found");
        }

        Optional<Jeton> jeton = jetonRepository.findById(id);

        if (!jeton.isPresent()) {
            throw new ResourceNotFoundException("Jeton not found");
        }
        
        JetonDto jetonDtoResult = JetonDto.fromEntityToDto(jeton.get());
        jetonDtoResult.setMontant(jetonDto.getMontant());
        jetonDtoResult.setEtat(jetonDto.getEtat());
        jetonDtoResult.setCreatedDate(jetonDto.getCreatedDate());
        jetonDtoResult.setUtilisateurDto(jetonDto.getUtilisateurDto());
       
        return JetonDto.fromEntityToDto(
        		jetonRepository.save(
        				JetonDto.fromDtoToEntity(jetonDtoResult)
                )
        );
	}

	@Override
	public JetonDto findById(Long id) {
		if (id == null) {
            log.error("Jeton Id is null");
            return null;
        }

        Optional<Jeton> jeton = jetonRepository.findById(id);

        return Optional.of(JetonDto.fromEntityToDto(jeton.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Jeton avec l'Id = " + id + "n'a été trouvé")
        );
	}

	@Override
	public List<JetonDto> findAll() {
		return jetonRepository.findAll().stream()
                .map(JetonDto::fromEntityToDto)
                .collect(Collectors.toList());
	}

	@Override
	public List<JetonDto> findAllJetonsByOrderByIdDesc() {
		return jetonRepository.findListOfJetonByOrderByIdDesc().stream()
                .map(JetonDto::fromEntityToDto)
                .collect(Collectors.toList());
	}

	@Override
	public BigDecimal countNumbersOfJetons() {
		return jetonRepository.countNumberOfJetons();
	}

	@Override
	public void delete(Long id) {
		if (id == null) {
            log.error("Jeton Id is null");
            return;
        }
		jetonRepository.deleteById(id);
		
	}

}
