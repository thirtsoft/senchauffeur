package com.chauffeur.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.HistoriqueLoginDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.HistoriqueLogin;
import com.chauffeur.repository.HistoriqueLoginRepository;
import com.chauffeur.services.HistoriqueLoginService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class HistoriqueLoginServiceImpl implements HistoriqueLoginService {
	
	@Autowired
    private final HistoriqueLoginRepository historiqueLoginRepository;
	
	public HistoriqueLoginServiceImpl(HistoriqueLoginRepository historiqueLoginRepository) {
        this.historiqueLoginRepository = historiqueLoginRepository;
    }

	
	@Override
	public HistoriqueLoginDto save(HistoriqueLoginDto historiqueLoginDto) {
		return HistoriqueLoginDto.fromEntityToDto(
				historiqueLoginRepository.save(
						HistoriqueLoginDto.fromDtoToEntity(historiqueLoginDto)
	                )
	        );
	}

	@Override
	public HistoriqueLoginDto update(Long id, HistoriqueLoginDto historiqueLoginDto) {
		if (!historiqueLoginRepository.existsById(id)) {
            throw new ResourceNotFoundException("Annonce not found");
        }

        Optional<HistoriqueLogin> historiqueLogin = historiqueLoginRepository.findById(id);

        if (!historiqueLogin.isPresent()) {
            throw new ResourceNotFoundException("HistoriqueLogin not found");
        }
        
        HistoriqueLoginDto annonceDtoResult = HistoriqueLoginDto.fromEntityToDto(historiqueLogin.get());
        annonceDtoResult.setAction(historiqueLoginDto.getAction());
        annonceDtoResult.setCreatedDate(historiqueLoginDto.getCreatedDate());
        annonceDtoResult.setUtilisateurDto(historiqueLoginDto.getUtilisateurDto());
       
        return HistoriqueLoginDto.fromEntityToDto(
        		historiqueLoginRepository.save(
        				HistoriqueLoginDto.fromDtoToEntity(annonceDtoResult)
                )
        );
        
	}

	@Override
	public HistoriqueLoginDto findById(Long id) {
		if (id == null) {
            log.error("HistoriqueLogin Id is null");
            return null;
        }

        Optional<HistoriqueLogin> historiqueLogin = historiqueLoginRepository.findById(id);

        return Optional.of(HistoriqueLoginDto.fromEntityToDto(historiqueLogin.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun HistoriqueLogin avec l'Id = " + id + "n'a été trouvé")
        );
	}

	@Override
	public List<HistoriqueLoginDto> findAll() {
		 return historiqueLoginRepository.findAll().stream()
	                .map(HistoriqueLoginDto::fromEntityToDto)
	                .collect(Collectors.toList());
	}

	@Override
	public List<HistoriqueLoginDto> findHistoriqueLoginByOrderByIdDesc() {
		 return historiqueLoginRepository.findHistoriqueLoginByOrderByIdDesc()
				 .stream()
				 .map(HistoriqueLoginDto::fromEntityToDto)
				 .collect(Collectors.toList());
	}

	@Override
	public BigDecimal countNumbersOfHistoriqueLogins() {
		return historiqueLoginRepository.countNumberOfHistoriqueLogins();
	}

	@Override
	public void delete(Long id) {
		if (id == null) {
            log.error("HistoriqueLogin Id is null");
            return;
        }
		historiqueLoginRepository.deleteById(id);
	}

}
