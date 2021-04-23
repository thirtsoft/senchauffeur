package com.chauffeur.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.NotificationDto;
import com.chauffeur.dto.UtilisateurDto;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NotificationRepositoryTest {
	
	@Autowired
    private NotificationRepository notificationRepository;

    @Test
    @Rollback(false)
    public void testCreateNotification() {
    	String name = "A";
    	String username = "Poids Legers";
    	UtilisateurDto utilisateurDto = new UtilisateurDto();
    	utilisateurDto.setName(name); utilisateurDto.setUsername(username);
    	
    	String firstName = "Tairou"; String lastName = "Diallo";
    	String sexe = "M"; String addressActuel = "ADD1"; String mobilite = "Dk - Thies - Kaolack";
    	ChauffeurDto chauffeurDto = new ChauffeurDto();
    	chauffeurDto.setFirstName(firstName);
    	chauffeurDto.setLastName(lastName); chauffeurDto.setSexe(sexe);
    	chauffeurDto.setAddressActuel(addressActuel); chauffeurDto.setMobilite(mobilite);
    	
    	String reference = "CH1"; String nbreEtoile = "Tairou"; String observation = "Diallo";
    	NotificationDto notificationDto = new NotificationDto();
    	notificationDto.setReference(reference); notificationDto.setNbreEtoile(nbreEtoile);
    	notificationDto.setObservation(observation);
    	notificationDto.setUtilisateurDto(utilisateurDto);
    	notificationDto.setChauffeurDto(chauffeurDto);
    	
    	NotificationDto notificationDtoResult = NotificationDto.fromEntityToDto(
    			notificationRepository.save(
    					NotificationDto.fromDtoToEntity(notificationDto)
                )
        );
    	
        assertNotNull(notificationDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateNotification() {
    	String name = "A";
    	String username = "Poids Legers";
    	UtilisateurDto utilisateurDto = new UtilisateurDto();
    	utilisateurDto.setName(name); utilisateurDto.setUsername(username);
    	
    	String firstName = "Tairou"; String lastName = "Diallo";
    	ChauffeurDto chauffeurDto = new ChauffeurDto();
    	chauffeurDto.setFirstName(firstName); chauffeurDto.setLastName(lastName);
    	
    	String reference = "CH1"; String nbreEtoile = "Tairou"; String observation = "Diallo";
    	NotificationDto notificationDto = new NotificationDto();
    	notificationDto.setReference(reference); notificationDto.setNbreEtoile(nbreEtoile);
    	notificationDto.setObservation(observation);
    	notificationDto.setUtilisateurDto(utilisateurDto);
    	notificationDto.setChauffeurDto(chauffeurDto);
    	
    	NotificationDto.fromEntityToDto(
    			notificationRepository.save(
    					NotificationDto.fromDtoToEntity(notificationDto)
                )
        );
    	
        String referenceChauffeur = "DialloDiallo";
        notificationDto.setReference(referenceChauffeur);
        notificationDto.setId((long) 1);
        NotificationDto.fromEntityToDto(notificationRepository.save(NotificationDto.fromDtoToEntity(notificationDto)));

        assertThat(notificationDto.getReference()).isEqualTo(referenceChauffeur);

    }

    @Test
    public void testFindById() {
    	String name = "A";
    	String username = "Poids Legers";
    	UtilisateurDto utilisateurDto = new UtilisateurDto();
    	utilisateurDto.setName(name); utilisateurDto.setUsername(username);
    	
    	String firstName = "Tairou"; String lastName = "Diallo";
    	ChauffeurDto chauffeurDto = new ChauffeurDto();
    	chauffeurDto.setFirstName(firstName); chauffeurDto.setLastName(lastName);
    	
    	String reference = "CH1"; String nbreEtoile = "Tairou"; String observation = "Diallo";
    	NotificationDto notificationDto = new NotificationDto();
    	notificationDto.setReference(reference); notificationDto.setNbreEtoile(nbreEtoile);
    	notificationDto.setObservation(observation);
    	notificationDto.setUtilisateurDto(utilisateurDto);
    	notificationDto.setChauffeurDto(chauffeurDto);
    	
    	NotificationDto notificationDtoResult = NotificationDto.fromEntityToDto(
    			notificationRepository.save(
    					NotificationDto.fromDtoToEntity(notificationDto)
                )
        );
    	
    	boolean isExistNotification = notificationRepository.findById(notificationDtoResult.getId()).isPresent();

        assertTrue(isExistNotification);
        
    }

    @Test
    public void testFindAll() {
    	String name = "A";
    	String username = "Poids Legers";
    	UtilisateurDto utilisateurDto = new UtilisateurDto();
    	utilisateurDto.setName(name); utilisateurDto.setUsername(username);
    	
    	String firstName = "Tairou"; String lastName = "Diallo";
    	ChauffeurDto chauffeurDto = new ChauffeurDto();
    	chauffeurDto.setFirstName(firstName); chauffeurDto.setLastName(lastName);
    	
    	String reference = "CH1"; String nbreEtoile = "Tairou"; String observation = "Diallo";
    	NotificationDto notificationDto = new NotificationDto();
    	notificationDto.setReference(reference); notificationDto.setNbreEtoile(nbreEtoile);
    	notificationDto.setObservation(observation);
    	notificationDto.setUtilisateurDto(utilisateurDto);
    	notificationDto.setChauffeurDto(chauffeurDto);
    	
    	NotificationDto.fromEntityToDto(
    			notificationRepository.save(
    					NotificationDto.fromDtoToEntity(notificationDto)
                )
        );
    	
    	String reference2 = "CH1"; String nbreEtoile2 = "Tairou";
    	NotificationDto notificationDto2 = new NotificationDto();
    	notificationDto2.setReference(reference2); notificationDto2.setNbreEtoile(nbreEtoile2);
    	notificationDto2.setUtilisateurDto(utilisateurDto);
    	notificationDto2.setChauffeurDto(chauffeurDto);
    	
    	NotificationDto.fromEntityToDto(
    			notificationRepository.save(
    					NotificationDto.fromDtoToEntity(notificationDto2)
                )
        );

        List<?> notifications = notificationRepository.findAll();

        assertThat(notifications).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
    	String name = "A";
    	String username = "Poids Legers";
    	UtilisateurDto utilisateurDto = new UtilisateurDto();
    	utilisateurDto.setName(name); utilisateurDto.setUsername(username);
    	
    	String firstName = "Tairou"; String lastName = "Diallo";
    	ChauffeurDto chauffeurDto = new ChauffeurDto();
    	chauffeurDto.setFirstName(firstName); chauffeurDto.setLastName(lastName);
    	
    	String reference = "CH1"; String nbreEtoile = "Tairou"; String observation = "Diallo";
    	NotificationDto notificationDto = new NotificationDto();
    	notificationDto.setReference(reference); notificationDto.setNbreEtoile(nbreEtoile);
    	notificationDto.setObservation(observation);
    	notificationDto.setUtilisateurDto(utilisateurDto);
    	notificationDto.setChauffeurDto(chauffeurDto);
    	
    	NotificationDto notificationDtoResult = NotificationDto.fromEntityToDto(
    			notificationRepository.save(
    					NotificationDto.fromDtoToEntity(notificationDto)
                )
        );
        boolean isExistBeforeDelete = notificationRepository.findById(notificationDtoResult.getId()).isPresent();

        notificationRepository.deleteById(notificationDtoResult.getId());

        boolean notExistAfterDelete = notificationRepository.findById(notificationDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }


}
