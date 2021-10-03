package com.chauffeur.dto;

import java.util.Date;

import com.chauffeur.models.Notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
	
	private Long id;

    private float nbreEtoile;

    private String observation;
    
    private Date createdDate;

    private ChauffeurDto chauffeurDto;

    private UtilisateurDto utilisateurDto;
	
	
	public static NotificationDto fromEntityToDto(Notification notification) {
		if (notification == null) {
			return null;
		}
		
		return NotificationDto.builder()
				.id(notification.getId())
				.nbreEtoile(notification.getNbreEtoile())
				.observation(notification.getObservation())
				.createdDate(notification.getCreatedDate())
				.chauffeurDto(ChauffeurDto.fromEntityToDto(notification.getChauffeur()))
				.utilisateurDto(UtilisateurDto.fromEntityToDto(notification.getUtilisateur()))
				.build();
		
	}
	
	public static Notification fromDtoToEntity(NotificationDto notificationDto) {
		if (notificationDto == null) {
			return null;
		}
		Notification notification = new Notification();
		notification.setId(notificationDto.getId());
		notification.setNbreEtoile(notificationDto.getNbreEtoile());
		notification.setObservation(notificationDto.getObservation());
		notification.setCreatedDate(notificationDto.getCreatedDate());
		notification.setChauffeur(ChauffeurDto.fromDtoToEntity(notificationDto.getChauffeurDto()));
		notification.setUtilisateur(UtilisateurDto.fromDtoToEntity(notificationDto.getUtilisateurDto()));
		
		return notification;
	}


}
