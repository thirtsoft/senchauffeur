package com.chauffeur.dto;

import com.chauffeur.models.Chauffeur;
import com.chauffeur.models.Notification;
import com.chauffeur.models.Utilisateur;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDto {
	
	private long id;

    private String reference;

    private String nbreEtoile;

    private String observation;

    private Chauffeur chauffeur;

    private Utilisateur utilisateur;
	
	
	public static NotificationDto fromEntityToDto(Notification notification) {
		if (notification == null) {
			return null;
		}
		
		return NotificationDto.builder()
				.reference(notification.getReference())
				.nbreEtoile(notification.getNbreEtoile())
				.observation(notification.getObservation())
				.build();
		
	}
	
	public static Notification fromDtoToEntity(NotificationDto notificationDto) {
		if (notificationDto == null) {
			return null;
		}
		Notification notification = new Notification();
		notification.setReference(notificationDto.getReference());
		notification.setNbreEtoile(notificationDto.getNbreEtoile());
		notification.setObservation(notificationDto.getObservation());
	
		return notification;
	}


}
