package com.chauffeur.controllers.api;

import static com.chauffeur.utils.Constants.APP_ROOT;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.chauffeur.dto.NotificationDto;

public interface NotificationApi {
	
	@PostMapping(value = APP_ROOT + "/notifications/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<NotificationDto> save(@RequestBody NotificationDto notificationDto);
	
	@PostMapping(value = APP_ROOT + "/notifications/createWithChauffeur/{idNotification}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<NotificationDto> saveNoteToChauffeur(@PathVariable("idNotification") Long id, 
			@RequestBody NotificationDto notificationDto);
	
	@PutMapping(value = APP_ROOT + "/notifications/update/{idNotification}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<NotificationDto> update(@PathVariable("idNotification") Long id, @RequestBody NotificationDto notificationDto);

	@PostMapping(value = APP_ROOT + "/notifications/createRatingToChauffeur", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<NotificationDto> saveRating(@RequestBody NotificationDto notificationDto, 
			@RequestParam Long idChauff, @RequestParam Long id);


	@GetMapping(value = APP_ROOT + "/notifications/findById/{idNotification}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<NotificationDto> findById(@PathVariable("idNotification") Long id);

	@GetMapping(value = APP_ROOT + "/notifications/all", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<NotificationDto>> findAll();
	
	@GetMapping(value = APP_ROOT + "/notifications/searchAllNotificationsOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<NotificationDto>> getAllNotificationsOrderByIdDesc();
	
	@GetMapping(value = APP_ROOT + "/notifications/searchTop3RatingOrderByCreatedDateDesc", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<NotificationDto>> getTop3ByOrderByCreatedDateDesc();
	
	@GetMapping(value = APP_ROOT + "/notifications/searchTop4RatingOrderByCreatedDateDescByChauffeurId/{idChauff}", produces = MediaType.APPLICATION_JSON_VALUE)   
	ResponseEntity<List<NotificationDto>> getTop4ByOrderByCreatedDateDescByProductId(@PathVariable("idChauff") Long id);
	
	@GetMapping(value = APP_ROOT + "/notifications/countNumberOfNotificationByProductId/{idChauff}", produces = MediaType.APPLICATION_JSON_VALUE)    
	BigDecimal countNumberOfNotificationByChauffeurId(@PathVariable("idChauff") String chauffRef);

	@GetMapping(value = APP_ROOT + "/notifications/countNumberOfNotification", produces = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal countNumberOfNotification();
	
	@DeleteMapping(value = APP_ROOT + "/notifications/delete/{idNotification}")
	void delete(@PathVariable("idNotification") Long id);

}
