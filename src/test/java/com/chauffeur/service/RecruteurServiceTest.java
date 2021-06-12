package com.chauffeur.service;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.chauffeur.dto.RecruteurDto;
import com.chauffeur.models.Recruteur;
import com.chauffeur.repository.RecruteurRepository;
import com.chauffeur.services.impl.RecruteurServiceImpl;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecruteurServiceTest {
	
	@InjectMocks
    private RecruteurServiceImpl recruteurService;

    @Mock
    private RecruteurRepository recruteurRepository;
    
    
    @Test
    public void CreateRecruteurTest() {
        RecruteurDto recruteurDto = new RecruteurDto();
        recruteurDto.setId(1L);
        recruteurDto.setNomEntreprise("Wokite");
        recruteurDto.setFirstName("tairou");
        recruteurDto.setLastName("diallo");
        recruteurDto.setSecteurActivite("ing Inf");
       
        Recruteur recruteur = RecruteurDto.fromDtoToEntity(recruteurDto);
        when(recruteurRepository.save(recruteur)).thenReturn(recruteur);

        RecruteurDto RecruteurDtoSavedResult = recruteurService.save(recruteurDto);

        verify(recruteurRepository).save(recruteur);
        assertThat(recruteurDto).isNotNull();
  //      assertThat(RecruteurDtoSavedResult).isEqualTo(recruteurDto);
        assertThat(RecruteurDtoSavedResult.getId()).isEqualTo(recruteur.getId());
        assertThat(RecruteurDtoSavedResult.getNomEntreprise()).isEqualTo(recruteur.getNomEntreprise());
        assertThat(RecruteurDtoSavedResult.getFirstName()).isEqualTo(recruteur.getFirstName());
    }

    @Test
    public void findAllTest() {
        RecruteurDto recruteurDto = new RecruteurDto();
        recruteurDto.setId(1L);
        recruteurDto.setId(1L);
        recruteurDto.setNomEntreprise("Wokite");
        recruteurDto.setFirstName("tairou");
        recruteurDto.setLastName("diallo");
        recruteurDto.setSecteurActivite("ing Inf");

        Recruteur recruteur = RecruteurDto.fromDtoToEntity(recruteurDto);
        when(recruteurRepository.findAll()).thenReturn(singletonList(recruteur));

        List<RecruteurDto> categories = recruteurService.findAll();

        assertThat(categories).isNotNull();
        assertThat(categories.size()).isEqualTo(1);
        verify(recruteurRepository).findAll();
        assertThat(categories.get(0)).isEqualTo(RecruteurDto.fromEntityToDto(recruteur));
    }

    @Test
    public void findByIdTest() {
    	RecruteurDto recruteurDto = new RecruteurDto();
        recruteurDto.setId(1L);
        recruteurDto.setNomEntreprise("Wokite");
        recruteurDto.setFirstName("tairou");
        recruteurDto.setLastName("diallo");
        recruteurDto.setSecteurActivite("ing Inf");
        
        Optional<Recruteur> categorie = Optional.ofNullable(RecruteurDto.fromDtoToEntity(recruteurDto));
        when(recruteurRepository.findById(categorie.get().getId())).thenReturn(categorie);

        RecruteurDto RecruteurDtoSavedResult = recruteurService.findById(recruteurDto.getId());

        verify(recruteurRepository).findById(categorie.get().getId());
        assertThat(recruteurDto).isNotNull();
  //      assertThat(RecruteurDtoSavedResult).isEqualTo(recruteurDto);
        assertThat(RecruteurDtoSavedResult.getId()).isEqualTo(categorie.get().getId());

    }

    

}
