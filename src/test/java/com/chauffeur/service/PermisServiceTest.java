package com.chauffeur.service;

import com.chauffeur.dto.PermisDto;
import com.chauffeur.models.Permis;
import com.chauffeur.repository.PermisRepository;
import com.chauffeur.services.impl.PermisServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PermisServiceTest {
	
	@InjectMocks
    private PermisServiceImpl categorieService;

    @Mock
    private PermisRepository categoryRepository;

    @Test
    public void CreateCategoryTest() {
    	PermisDto categoryDto = new PermisDto(1L, "AAA", "TTT", 10);
    	
    	Permis category = PermisDto.fromDtoToEntity(categoryDto);
        when(categoryRepository.save(category)).thenReturn(category);

        PermisDto categoryDtoSavedResult = categorieService.save(categoryDto);

        verify(categoryRepository).save(category);
        assertThat(categoryDto).isNotNull();
  //      assertThat(categoryDtoSavedResult).isEqualTo(categoryDto);
        assertThat(categoryDtoSavedResult.getId()).isEqualTo(category.getId());
        assertThat(categoryDtoSavedResult.getDesignation()).isEqualTo(category.getDesignation());
    }

    @Test
    public void findAllTest() {
    	PermisDto categoryDto = new PermisDto();
        categoryDto.setId(1L);
        categoryDto.setDesignation("Samsung A10s");

        Permis category = PermisDto.fromDtoToEntity(categoryDto);
        when(categoryRepository.findAll()).thenReturn(singletonList(category));

        List<PermisDto> categories = categorieService.findAll();

        assertThat(categories).isNotNull();
        assertThat(categories.size()).isEqualTo(1);
        verify(categoryRepository).findAll();
        assertThat(categories.get(0)).isEqualTo(PermisDto.fromEntityToDto(category));
    }

    @Test
    public void findByIdTest() {
    	PermisDto categoryDto = PermisDto.builder()
    			.id(1L)
    			.typePermis("PPP")
                .designation("AAA")
                .build();
        Optional<Permis> categorie = Optional.ofNullable(PermisDto.fromDtoToEntity(categoryDto));
        when(categoryRepository.findById(categorie.get().getId())).thenReturn(categorie);

        PermisDto categoryDtoSavedResult = categorieService.findById(categoryDto.getId());

        verify(categoryRepository).findById(categorie.get().getId());
        assertThat(categoryDto).isNotNull();
  //      assertThat(categoryDtoSavedResult).isEqualTo(categoryDto);
        assertThat(categoryDtoSavedResult.getId()).isEqualTo(categorie.get().getId());

    }

   
}
