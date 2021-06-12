package com.chauffeur.service;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.chauffeur.dto.RecruteurDto;
import com.chauffeur.dto.UtilisateurDto;
import com.chauffeur.models.Recruteur;
import com.chauffeur.models.Utilisateur;
import com.chauffeur.repository.RecruteurRepository;
import com.chauffeur.repository.UtilisateurRepository;
import com.chauffeur.services.impl.RecruteurServiceImpl;
import com.chauffeur.services.impl.UtilisateurServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UtilisateurServiceTest {
	
	@InjectMocks
    private UtilisateurServiceImpl recruteurService;

    @Mock
    private UtilisateurRepository recruteurRepository;
    
    
    @Test
    public void CreateRecruteurTest() {
    	UtilisateurDto recruteurDto = new UtilisateurDto();
        recruteurDto.setId(1L);
        recruteurDto.setName("tairou");
        recruteurDto.setUsername("thir");
       
        Utilisateur recruteur = UtilisateurDto.fromDtoToEntity(recruteurDto);
        when(recruteurRepository.save(recruteur)).thenReturn(recruteur);

        UtilisateurDto RecruteurDtoSavedResult = recruteurService.save(recruteurDto);

        verify(recruteurRepository).save(recruteur);
        assertThat(recruteurDto).isNotNull();
  //      assertThat(RecruteurDtoSavedResult).isEqualTo(recruteurDto);
        assertThat(RecruteurDtoSavedResult.getId()).isEqualTo(recruteur.getId());
        assertThat(RecruteurDtoSavedResult.getName()).isEqualTo(recruteur.getName());
        assertThat(RecruteurDtoSavedResult.getUsername()).isEqualTo(recruteur.getUsername());
    }

    @Test
    public void findAllTest() {
    	UtilisateurDto recruteurDto = new UtilisateurDto();
        recruteurDto.setId(1L);
        recruteurDto.setName("tairou");
        recruteurDto.setUsername("thir");
       
        Utilisateur recruteur = UtilisateurDto.fromDtoToEntity(recruteurDto); 
        when(recruteurRepository.findAll()).thenReturn(singletonList(recruteur));

        List<UtilisateurDto> categories = recruteurService.findAll();

        assertThat(categories).isNotNull();
        assertThat(categories.size()).isEqualTo(1);
        verify(recruteurRepository).findAll();
        assertThat(categories.get(0)).isEqualTo(UtilisateurDto.fromEntityToDto(recruteur));
    }

    @Test
    public void findByIdTest() {
    	UtilisateurDto recruteurDto = new UtilisateurDto();
        recruteurDto.setId(1L);
        recruteurDto.setName("tairou");
        recruteurDto.setUsername("thir");
       
        Optional<Utilisateur> categorie = Optional.ofNullable(UtilisateurDto.fromDtoToEntity(recruteurDto));
        when(recruteurRepository.findById(categorie.get().getId())).thenReturn(categorie);

        UtilisateurDto RecruteurDtoSavedResult = recruteurService.findById(recruteurDto.getId());

        verify(recruteurRepository).findById(categorie.get().getId());
        assertThat(recruteurDto).isNotNull();
  //      assertThat(RecruteurDtoSavedResult).isEqualTo(recruteurDto);
        assertThat(RecruteurDtoSavedResult.getId()).isEqualTo(categorie.get().getId());

    }

}
