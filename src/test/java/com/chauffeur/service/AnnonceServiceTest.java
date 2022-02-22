package com.chauffeur.service;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.PermisDto;
import com.chauffeur.dto.RecruteurDto;
import com.chauffeur.models.Annonce;
import com.chauffeur.models.Chauffeur;
import com.chauffeur.repository.AnnonceRepository;
import com.chauffeur.repository.ChauffeurRepository;
import com.chauffeur.services.impl.AnnonceServiceImpl;
import com.chauffeur.services.impl.ChauffeurServiceImpl;

public class AnnonceServiceTest {
	
	@InjectMocks
    private AnnonceServiceImpl scategorieService;

    @Mock
    private AnnonceRepository scategoryRepository;

    @Test
    public void CreateScategoryTest() {
        PermisDto permisDto = new PermisDto();
    //    RecruteurDto recruteurDto = new RecruteurDto();
        AnnonceDto chauffeurDto = new AnnonceDto();
        chauffeurDto.setId(1L);
        chauffeurDto.setReference("AAA");
        chauffeurDto.setLieuPoste("AAA");
        chauffeurDto.setPermisDto(permisDto);
   //     chauffeurDto.setRecruteurDto(recruteurDto);
              
        Annonce chauffeur = AnnonceDto.fromDtoToEntity(chauffeurDto);
        when(scategoryRepository.save(chauffeur)).thenReturn(chauffeur);

        AnnonceDto scategoryDtoSavedResult = scategorieService.save(chauffeurDto);

        verify(scategoryRepository).save(chauffeur);
        assertThat(chauffeurDto).isNotNull();
        assertThat(scategoryDtoSavedResult).isEqualTo(chauffeurDto);
        assertThat(scategoryDtoSavedResult.getId()).isEqualTo(chauffeur.getId());
        assertThat(scategoryDtoSavedResult.getReference()).isEqualTo(chauffeur.getReference());
        assertThat(scategoryDtoSavedResult.getLieuPoste()).isEqualTo(chauffeur.getLieuPoste());
    }

    @Test
    public void findAllTest() {
    	PermisDto permisDto = new PermisDto();
  //      RecruteurDto recruteurDto = new RecruteurDto();
        AnnonceDto chauffeurDto = new AnnonceDto();
        chauffeurDto.setId(1L);
        chauffeurDto.setReference("AAA");
        chauffeurDto.setLieuPoste("AAA");
        chauffeurDto.setPermisDto(permisDto);
  //      chauffeurDto.setRecruteurDto(recruteurDto);
              
        Annonce chauffeur = AnnonceDto.fromDtoToEntity(chauffeurDto);
        when(scategoryRepository.findAll()).thenReturn(singletonList(chauffeur));

        List<AnnonceDto> scategories = scategorieService.findAll();

        assertThat(scategories).isNotNull();
        assertThat(scategories.size()).isEqualTo(1);
        verify(scategoryRepository).findAll();
        assertThat(scategories.get(0)).isEqualTo(AnnonceDto.fromEntityToDto(chauffeur));
    }

    @Test
    public void findByIdTest() {
    	PermisDto permisDto = new PermisDto();
        RecruteurDto recruteurDto = new RecruteurDto();
        AnnonceDto chauffeurDto = new AnnonceDto();
        chauffeurDto.setId(1L);
        chauffeurDto.setReference("AAA");
        chauffeurDto.setLieuPoste("AAA");
        chauffeurDto.setPermisDto(permisDto);
     //   chauffeurDto.setRecruteurDto(recruteurDto);
        
        Optional<Annonce> scategorie = Optional.ofNullable(AnnonceDto.fromDtoToEntity(chauffeurDto));
        when(scategoryRepository.findById(scategorie.get().getId())).thenReturn(scategorie);

        AnnonceDto scategoryDtoSavedResult = scategorieService.findById(chauffeurDto.getId());

        verify(scategoryRepository).findById(scategorie.get().getId());
        assertThat(chauffeurDto).isNotNull();
        assertThat(scategoryDtoSavedResult).isEqualTo(chauffeurDto);
        assertThat(scategoryDtoSavedResult.getId()).isEqualTo(scategorie.get().getId());

    }



}
