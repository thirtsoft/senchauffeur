package com.chauffeur.services;

import java.util.List;

import com.chauffeur.dto.NotificationDto;

public interface NotificationService {
	
	NotificationDto save(NotificationDto notificationDto);

	NotificationDto findById(Long id);

    List<NotificationDto> findAll();

    void delete(Long id);

}
