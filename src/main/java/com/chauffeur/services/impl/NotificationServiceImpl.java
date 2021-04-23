package com.chauffeur.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.NotificationDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Notification;
import com.chauffeur.repository.NotificationRepository;
import com.chauffeur.services.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public NotificationDto save(NotificationDto notificationDto) {

        return NotificationDto.fromEntityToDto(
        		notificationRepository.save(
                		NotificationDto.fromDtoToEntity(notificationDto)
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
    public void delete(Long id) {
        if (id == null) {
            log.error("chauffeur Id is null");
            return;
        }
        notificationRepository.deleteById(id);

    }

}
