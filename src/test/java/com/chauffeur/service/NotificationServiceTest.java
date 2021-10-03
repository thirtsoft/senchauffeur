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

import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.NotificationDto;
import com.chauffeur.dto.UtilisateurDto;
import com.chauffeur.models.Notification;
import com.chauffeur.repository.NotificationRepository;
import com.chauffeur.services.impl.NotificationServiceImpl;

public class NotificationServiceTest {
	
	@InjectMocks
    private NotificationServiceImpl scategorieService;

    @Mock
    private NotificationRepository scategoryRepository;

    @Test
    public void CreateScategoryTest() {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(1L);
        utilisateurDto.setName("user");
        ChauffeurDto chauffeurDto = new ChauffeurDto();
        chauffeurDto.setId(1L);
        chauffeurDto.setFirstName("chauf");
     //   NotificationDto notationDto = new NotificationDto();
        NotificationDto notationDto = NotificationDto.builder()
                .id(1L)
                .nbreEtoile(4)
      //          .utilisateurDto(utilisateurDto)
                .chauffeurDto(chauffeurDto)
                .build();
              
        Notification chauffeur = NotificationDto.fromDtoToEntity(notationDto);
        when(scategoryRepository.save(chauffeur)).thenReturn(chauffeur);

        NotificationDto scategoryDtoSavedResult = scategorieService.save(notationDto);

        verify(scategoryRepository).save(chauffeur);
        assertThat(chauffeurDto).isNotNull();
        assertThat(scategoryDtoSavedResult).isEqualTo(chauffeurDto);
        assertThat(scategoryDtoSavedResult.getId()).isEqualTo(chauffeur.getId());
        assertThat(scategoryDtoSavedResult.getNbreEtoile()).isEqualTo(chauffeur.getNbreEtoile());
    }

    @Test
    public void findAllTest() {
    	UtilisateurDto utilisateurDto = new UtilisateurDto();
        ChauffeurDto chauffeurDto = new ChauffeurDto();
        NotificationDto notationDto = new NotificationDto();
        notationDto.setId(1L);
        notationDto.setNbreEtoile(3);
        notationDto.setChauffeurDto(chauffeurDto);
 //       notationDto.setUtilisateurDto(utilisateurDto);
              
        Notification chauffeur = NotificationDto.fromDtoToEntity(notationDto);
        when(scategoryRepository.findAll()).thenReturn(singletonList(chauffeur));

        List<NotificationDto> scategories = scategorieService.findAll();

        assertThat(scategories).isNotNull();
        assertThat(scategories.size()).isEqualTo(1);
        verify(scategoryRepository).findAll();
        assertThat(scategories.get(0)).isEqualTo(NotificationDto.fromEntityToDto(chauffeur));
    }

    @Test
    public void findByIdTest() {
    	UtilisateurDto utilisateurDto = new UtilisateurDto();
        ChauffeurDto chauffeurDto = new ChauffeurDto();
        NotificationDto notationDto = new NotificationDto();
        notationDto.setId(1L);
        notationDto.setNbreEtoile(2);
        notationDto.setChauffeurDto(chauffeurDto);
 //       notationDto.setUtilisateurDto(utilisateurDto);
               
        Optional<Notification> scategorie = Optional.ofNullable(NotificationDto.fromDtoToEntity(notationDto));
        when(scategoryRepository.findById(scategorie.get().getId())).thenReturn(scategorie);

        NotificationDto scategoryDtoSavedResult = scategorieService.findById(notationDto.getId());

        verify(scategoryRepository).findById(scategorie.get().getId());
        assertThat(chauffeurDto).isNotNull();
        assertThat(scategoryDtoSavedResult).isEqualTo(notationDto);
        assertThat(scategoryDtoSavedResult.getId()).isEqualTo(scategorie.get().getId());

    }



}
