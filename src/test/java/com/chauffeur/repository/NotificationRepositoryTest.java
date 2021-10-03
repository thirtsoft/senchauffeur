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
import com.chauffeur.models.Chauffeur;
import com.chauffeur.models.Notification;
import com.chauffeur.models.Permis;
import com.chauffeur.models.Utilisateur;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NotificationRepositoryTest {
	
	@Autowired
    private NotificationRepository notificationRepository;
	
	@Autowired
    private UtilisateurRepository utilisateurRepository;
	
	@Autowired
    private ChauffeurRepository chauffeurRepository;
	

    @Test
    @Rollback(false)
    public void testCreateNotification() {
    	Long catId = (long) 1;
        Chauffeur chauffeur = chauffeurRepository.findById(catId).orElse(null);
        
        Long userId = (long) 1;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);
      
    	float nbreEtoile = 4; String observation = "Diallo";
    	Notification notificationDto = new Notification();
    	notificationDto.setNbreEtoile(nbreEtoile);
    	notificationDto.setObservation(observation);
    	notificationDto.setUtilisateur(utilisateur);
    	notificationDto.setChauffeur(chauffeur);
    	Notification notificationDtoResult = notificationRepository.save(notificationDto);
    	
        assertNotNull(notificationDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateNotification() {
    	Long catId = (long) 1;
        Chauffeur chauffeur = chauffeurRepository.findById(catId).orElse(null);
        
        Long userId = (long) 1;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);
      
    	float nbreEtoile = 3; String observation = "Diallo";
    	Notification notificationDto = new Notification();
    	notificationDto.setNbreEtoile(nbreEtoile);
    	notificationDto.setObservation(observation);
    	notificationDto.setUtilisateur(utilisateur);
    	notificationDto.setChauffeur(chauffeur);
    	notificationRepository.save(notificationDto);
    
        float nbreEtoileChauffeur = 5;
        notificationDto.setNbreEtoile(nbreEtoileChauffeur);
//        notificationDto.setId(2L);
        Notification notificationUpdate = notificationRepository.save(notificationDto);
      
        assertThat(notificationUpdate.getNbreEtoile()).isEqualTo(nbreEtoileChauffeur);

    }

    @Test
    public void testFindById() {
    	Long catId = (long) 1;
        Chauffeur chauffeur = chauffeurRepository.findById(catId).orElse(null);
        
        Long userId = (long) 1;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);
      
    	float nbreEtoile = 3; String observation = "Diallo";
    	Notification notificationDto = new Notification();
    	notificationDto.setNbreEtoile(nbreEtoile);
    	notificationDto.setObservation(observation);
    	notificationDto.setUtilisateur(utilisateur);
    	notificationDto.setChauffeur(chauffeur);
    	Notification notificationDtoResult = notificationRepository.save(notificationDto);
    
    	boolean isExistNotification = notificationRepository.findById(notificationDtoResult.getId()).isPresent();

        assertTrue(isExistNotification);
        
    }

    @Test
    public void testFindAll() {
    	Long catId = (long) 1;
        Chauffeur chauffeur = chauffeurRepository.findById(catId).orElse(null);
        
        Long userId = (long) 1;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);
      
    	String reference = "CH1"; float nbreEtoile = 4; String observation = "Diallo";
    	Notification notificationDto = new Notification();
    	notificationDto.setNbreEtoile(nbreEtoile);
    	notificationDto.setObservation(observation);
    	notificationDto.setUtilisateur(utilisateur);
    	notificationDto.setChauffeur(chauffeur);
    	notificationRepository.save(notificationDto);
    
    	float nbreEtoile2 = 4;
    	Notification notificationDto2 = new Notification();
    	notificationDto2.setNbreEtoile(nbreEtoile2);
    	notificationRepository.save(notificationDto2);
    
        List<?> notifications = notificationRepository.findAll();

        assertThat(notifications).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
    	Long catId = (long) 1;
        Chauffeur chauffeur = chauffeurRepository.findById(catId).orElse(null);
        
        Long userId = (long) 1;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);
      
    	String reference = "CH1"; float nbreEtoile = 4; String observation = "Diallo";
    	Notification notificationDto = new Notification();
    	notificationDto.setNbreEtoile(nbreEtoile);
    	notificationDto.setObservation(observation);
    	notificationDto.setUtilisateur(utilisateur);
    	notificationDto.setChauffeur(chauffeur);
    	Notification notificationDtoResult = notificationRepository.save(notificationDto);
    
        boolean isExistBeforeDelete = notificationRepository.findById(notificationDtoResult.getId()).isPresent();

        notificationRepository.deleteById(notificationDtoResult.getId());

        boolean notExistAfterDelete = notificationRepository.findById(notificationDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }


}
