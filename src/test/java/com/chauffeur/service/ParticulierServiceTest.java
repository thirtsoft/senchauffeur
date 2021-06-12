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

import com.chauffeur.dto.ParticulierDto;
import com.chauffeur.models.Particulier;
import com.chauffeur.repository.ParticulierRepository;
import com.chauffeur.services.impl.ParticulierServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ParticulierServiceTest {
	
	@InjectMocks
    private ParticulierServiceImpl particulierService;

    @Mock
    private ParticulierRepository particulierRepository;
    
    
    @Test
    public void CreateParticulierTest() {
        ParticulierDto particulierDto = new ParticulierDto();
        particulierDto.setId(1L);
        particulierDto.setFirstName("tairou");
        particulierDto.setLastName("diallo");
       
        Particulier particulier = ParticulierDto.fromDtoToEntity(particulierDto);
        when(particulierRepository.save(particulier)).thenReturn(particulier);

        ParticulierDto ParticulierDtoSavedResult = particulierService.save(particulierDto);

        verify(particulierRepository).save(particulier);
        assertThat(particulierDto).isNotNull();
  //      assertThat(ParticulierDtoSavedResult).isEqualTo(ParticulierDto);
        assertThat(ParticulierDtoSavedResult.getId()).isEqualTo(particulier.getId());
        assertThat(ParticulierDtoSavedResult.getFirstName()).isEqualTo(particulier.getFirstName());
    }

    @Test
    public void findAllTest() {
        ParticulierDto particulierDto = new ParticulierDto();
        particulierDto.setId(1L);
        particulierDto.setFirstName("tairou");
        particulierDto.setLastName("diallo");

        Particulier particulier = ParticulierDto.fromDtoToEntity(particulierDto);
        when(particulierRepository.findAll()).thenReturn(singletonList(particulier));

        List<ParticulierDto> categories = particulierService.findAll();

        assertThat(categories).isNotNull();
        assertThat(categories.size()).isEqualTo(1);
        verify(particulierRepository).findAll();
        assertThat(categories.get(0)).isEqualTo(ParticulierDto.fromEntityToDto(particulier));
    }

    @Test
    public void findByIdTest() {
    	ParticulierDto particulierDto = new ParticulierDto();
    	particulierDto.setId(1L);
        particulierDto.setFirstName("tairou");
        particulierDto.setLastName("diallo");
        
        Optional<Particulier> categorie = Optional.ofNullable(ParticulierDto.fromDtoToEntity(particulierDto));
        when(particulierRepository.findById(categorie.get().getId())).thenReturn(categorie);

        ParticulierDto ParticulierDtoSavedResult = particulierService.findById(particulierDto.getId());

        verify(particulierRepository).findById(categorie.get().getId());
        assertThat(particulierDto).isNotNull();
  //      assertThat(ParticulierDtoSavedResult).isEqualTo(ParticulierDto);
        assertThat(ParticulierDtoSavedResult.getId()).isEqualTo(categorie.get().getId());

    }

}
