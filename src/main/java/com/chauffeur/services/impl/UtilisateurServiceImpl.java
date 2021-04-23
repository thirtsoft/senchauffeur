package com.chauffeur.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.UtilisateurDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Utilisateur;
import com.chauffeur.repository.UtilisateurRepository;
import com.chauffeur.services.UtilisateurService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
	
	@Autowired
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {

        return UtilisateurDto.fromEntityToDto(
        		utilisateurRepository.save(
                		UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );
    }

    @Override
    public UtilisateurDto findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Utilisateur> utiliOptional = utilisateurRepository.findById(id);

        return Optional.of(UtilisateurDto.fromEntityToDto(utiliOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun chauffeur avec l'Id = " + id + "n'a été trouvé")
        );
    }

    
    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("chauffeur Id is null");
            return;
        }
        utilisateurRepository.deleteById(id);

    }

}
