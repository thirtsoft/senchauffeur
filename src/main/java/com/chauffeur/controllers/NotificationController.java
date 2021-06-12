package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.NotificationApi;
import com.chauffeur.dto.NotificationDto;
import com.chauffeur.services.NotificationService;

@RestController
@CrossOrigin
public class NotificationController implements NotificationApi {
	
	private NotificationService notificationService;

	@Autowired
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	@Override
	public ResponseEntity<NotificationDto> save(NotificationDto notificationDto) {
		return ResponseEntity.ok(notificationService.save(notificationDto));
	}
	
	@Override
	public ResponseEntity<NotificationDto> update(Long id, NotificationDto notificationDto) {
		notificationDto.setId(id);
		return ResponseEntity.ok(notificationService.save(notificationDto));
	}

	@Override
	public ResponseEntity<NotificationDto> findById(Long id) {
		return ResponseEntity.ok(notificationService.findById(id));
	}

	@Override
	public List<NotificationDto> findAll() {
		return notificationService.findAll();
	}

	@Override
	public void delete(Long id) {
		notificationService.delete(id);
		
	}
	

}
