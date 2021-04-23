package com.chauffeur.controllers.api;

import static com.chauffeur.utils.Constants.APP_ROOT;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chauffeur.dto.NotificationDto;

public interface NotificationApi {
	
	@PostMapping(value = APP_ROOT + "/notifications/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<NotificationDto> save(@RequestBody NotificationDto notificationDto);

	@GetMapping(value = APP_ROOT + "/annonces/{idNotification}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<NotificationDto> findById(@PathVariable("idNotification") Long id);

	@GetMapping(value = APP_ROOT + "/notifications/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<NotificationDto> findAll();

	@DeleteMapping(value = APP_ROOT + "/notifications/delete/{idNotification}")
	void delete(@PathVariable("idNotification") Long id);

}
