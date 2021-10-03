package com.chauffeur.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.NotificationDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Chauffeur;
import com.chauffeur.models.Notification;
import com.chauffeur.repository.ChauffeurRepository;
import com.chauffeur.repository.NotificationRepository;
import com.chauffeur.services.ChauffeurService;
import com.chauffeur.services.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class NotificationServiceImpl implements NotificationService {
	
//	@Autowired
    private final NotificationRepository notificationRepository;
	
    private final ChauffeurService chauffeurService;
    

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository,
    							   ChauffeurService chauffeurService,
    							   ChauffeurRepository chauffeurRepository) {
        this.notificationRepository = notificationRepository;
        this.chauffeurService = chauffeurService;
    }

    @Override
    public NotificationDto save(NotificationDto notificationDto) {

        return NotificationDto.fromEntityToDto(
        		notificationRepository.save(
                		NotificationDto.fromDtoToEntity(notificationDto)
                )
        );
    }
    
    @Transactional
   	@Override
   	public NotificationDto saveNoteToChauffeur(Long idChauff, NotificationDto notificationDto) {
        
   		ChauffeurDto chauffeurDtoOptional = chauffeurService.findById(idChauff);

   		notificationDto.setChauffeurDto(chauffeurDtoOptional);
   		
   		 return NotificationDto.fromEntityToDto(
   	        		notificationRepository.save(
   	        				NotificationDto.fromDtoToEntity(notificationDto)
   	                )
   	        );
   		
   	}
    
    @Override
	public NotificationDto update(Long idNotification, NotificationDto notificationDto) {
		if (!notificationRepository.existsById(idNotification)) {
            throw new ResourceNotFoundException("Notification not found");
        }

        Optional<Notification> notification = notificationRepository.findById(idNotification);

        if (!notification.isPresent()) {
            throw new ResourceNotFoundException("Notification not found");
        }

        NotificationDto notificationDtoResult = NotificationDto.fromEntityToDto(notification.get());
        notificationDtoResult.setNbreEtoile(notificationDto.getNbreEtoile());
        notificationDtoResult.setObservation(notificationDto.getObservation());
        notificationDtoResult.setChauffeurDto(notificationDto.getChauffeurDto());
       
        return NotificationDto.fromEntityToDto(
        		notificationRepository.save(
        				NotificationDto.fromDtoToEntity(notificationDtoResult)
                )
        );
	}

    @Override
    public NotificationDto findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Notification> notification = notificationRepository.findById(id);

        return Optional.of(NotificationDto.fromEntityToDto(notification.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun notification avec l'Id = " + id + "n'a été trouvé")
        );
    }

    
    @Override
    public List<NotificationDto> findAll() {
        return notificationRepository.findAll().stream()
                .map(NotificationDto::fromEntityToDto)
                .collect(Collectors.toList());
    }
    
    @Override
	public List<NotificationDto> findTop3RatingOrderByCreatedDateDesc() {
    	 return notificationRepository.findTop3ByOrderByCreatedDateDesc().stream()
                 .map(NotificationDto::fromEntityToDto)
                 .collect(Collectors.toList());
	}

	@Override
	public BigDecimal countNumberOfNotification() {
		return notificationRepository.countNumberOfNotification();
	}

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("chauffeur Id is null");
            return;
        }
        notificationRepository.deleteById(id);

    }

   

	

	
}
