package com.chauffeur.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.PermisDto;
import com.chauffeur.models.Chauffeur;
import com.chauffeur.repository.ChauffeurRepository;
import com.chauffeur.services.impl.ChauffeurServiceImpl;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class ChauffeurServiceTest {
	
	 @InjectMocks
	    private ChauffeurServiceImpl scategorieService;

	    @Mock
	    private ChauffeurRepository scategoryRepository;

	    @Test
	    public void CreateScategoryTest() {
	        PermisDto permisDto = new PermisDto();
	        ChauffeurDto chauffeurDto = new ChauffeurDto();
	        chauffeurDto.setId(1L);
	        chauffeurDto.setFirstName("AAA");
	        chauffeurDto.setLastName("AAA");
	        chauffeurDto.setPermisDto(permisDto);
	              
	        Chauffeur chauffeur = ChauffeurDto.fromDtoToEntity(chauffeurDto);
	        when(scategoryRepository.save(chauffeur)).thenReturn(chauffeur);

	        ChauffeurDto scategoryDtoSavedResult = scategorieService.save(chauffeurDto);

	        verify(scategoryRepository).save(chauffeur);
	        assertThat(chauffeurDto).isNotNull();
	        assertThat(scategoryDtoSavedResult).isEqualTo(chauffeurDto);
	        assertThat(scategoryDtoSavedResult.getId()).isEqualTo(chauffeur.getId());
	        assertThat(scategoryDtoSavedResult.getFirstName()).isEqualTo(chauffeur.getFirstName());
	        assertThat(scategoryDtoSavedResult.getLastName()).isEqualTo(chauffeur.getLastName());
	    }

	    @Test
	    public void findAllTest() {
	    	PermisDto permisDto = new PermisDto();
	        ChauffeurDto chauffeurDto = new ChauffeurDto();
	        chauffeurDto.setId(1L);
	        chauffeurDto.setFirstName("AAA");
	        chauffeurDto.setLastName("AAA");
	        chauffeurDto.setPermisDto(permisDto);
	              
	        Chauffeur chauffeur = ChauffeurDto.fromDtoToEntity(chauffeurDto);
	        when(scategoryRepository.findAll()).thenReturn(singletonList(chauffeur));

	        List<ChauffeurDto> scategories = scategorieService.findAll();

	        assertThat(scategories).isNotNull();
	        assertThat(scategories.size()).isEqualTo(1);
	        verify(scategoryRepository).findAll();
	        assertThat(scategories.get(0)).isEqualTo(ChauffeurDto.fromEntityToDto(chauffeur));
	    }

	    @Test
	    public void findByIdTest() {
	    	PermisDto permisDto = new PermisDto();
	        ChauffeurDto chauffeurDto = new ChauffeurDto();
	        chauffeurDto.setId(1L);
	        chauffeurDto.setFirstName("AAA");
	        chauffeurDto.setLastName("AAA");
	        chauffeurDto.setPermisDto(permisDto);
	                  
	        Optional<Chauffeur> scategorie = Optional.ofNullable(ChauffeurDto.fromDtoToEntity(chauffeurDto));
	        when(scategoryRepository.findById(scategorie.get().getId())).thenReturn(scategorie);

	        ChauffeurDto scategoryDtoSavedResult = scategorieService.findById(chauffeurDto.getId());

	        verify(scategoryRepository).findById(scategorie.get().getId());
	        assertThat(chauffeurDto).isNotNull();
	        assertThat(scategoryDtoSavedResult).isEqualTo(chauffeurDto);
	        assertThat(scategoryDtoSavedResult.getId()).isEqualTo(scategorie.get().getId());

	    }



}
