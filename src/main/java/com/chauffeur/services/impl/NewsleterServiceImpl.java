package com.chauffeur.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.JetonDto;
import com.chauffeur.dto.NewsleterDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Jeton;
import com.chauffeur.models.Newsleter;
import com.chauffeur.repository.NewsleterRepository;
import com.chauffeur.services.NewsleterService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class NewsleterServiceImpl implements NewsleterService {
	
	private final NewsleterRepository newsleterRepository;
	
	@Autowired
	public NewsleterServiceImpl(NewsleterRepository newsleterRepository) {
		this.newsleterRepository = newsleterRepository;
	}

	@Override
	public NewsleterDto save(NewsleterDto newsleterDto) {
		return NewsleterDto.fromEntityToDto(
				newsleterRepository.save(
						NewsleterDto.fromDtoToEntity(newsleterDto)));
	}

	@Override
	public NewsleterDto update(Long id, NewsleterDto newsleterDto) {
		if (!newsleterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Newsleter not found");
        }

        Optional<Newsleter> optionalNewsleter  = newsleterRepository.findById(id);

        if (!optionalNewsleter.isPresent()) {
            throw new ResourceNotFoundException("Newsleter not found");
        }
        
        NewsleterDto newsleterDtoResult = NewsleterDto.fromEntityToDto(optionalNewsleter.get());
        newsleterDtoResult.setEmailVisiteur(newsleterDto.getEmailVisiteur());
        newsleterDtoResult.setCreatedDate(newsleterDto.getCreatedDate());
      
        return NewsleterDto.fromEntityToDto(
        		newsleterRepository.save(
        				NewsleterDto.fromDtoToEntity(newsleterDtoResult)
                )
        );
	}

	@Override
	public NewsleterDto findById(Long id) {
		if (id == null) {
            log.error("Newsleter Id is null");
            return null;
        }

        Optional<Newsleter> optionalNewsleter = newsleterRepository.findById(id);

        return Optional.of(NewsleterDto.fromEntityToDto(optionalNewsleter.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Newsleter avec l'Id = " + id + "n'a été trouvé")
        );
	}

	@Override
	public List<NewsleterDto> findAll() {
		return newsleterRepository.findAll().stream()
                .map(NewsleterDto::fromEntityToDto)
                .collect(Collectors.toList());
	}

	@Override
	public List<NewsleterDto> findAllNewsletersByOrderByIdDesc() {
		return newsleterRepository.findListOfNewsletersByOrderByIdDesc().stream()
                .map(NewsleterDto::fromEntityToDto)
                .collect(Collectors.toList());
	}

	@Override
	public BigDecimal countNumbersOfNewsleters() {
		return newsleterRepository.countNumberOfNewsleters();
	}

	@Override
	public void delete(Long id) {
		if (id == null) {
            log.error("Newsleter Id is null");
            return;
        }
		newsleterRepository.deleteById(id);
	}

}
