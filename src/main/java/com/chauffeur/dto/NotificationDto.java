package com.chauffeur.dto;

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

    private String reference;

    private String nbreEtoile;

    private String observation;

    private ChauffeurDto chauffeurDto;

 //   private UtilisateurDto utilisateurDto;
	
	
	public static NotificationDto fromEntityToDto(Notification notification) {
		if (notification == null) {
			return null;
		}
		
		return NotificationDto.builder()
				.id(notification.getId())
				.reference(notification.getReference())
				.nbreEtoile(notification.getNbreEtoile())
				.observation(notification.getObservation())
				.chauffeurDto(ChauffeurDto.fromEntityToDto(notification.getChauffeur()))
	//			.utilisateurDto(UtilisateurDto.fromEntityToDto(notification.getUtilisateur()))
				.build();
		
	}
	
	public static Notification fromDtoToEntity(NotificationDto notificationDto) {
		if (notificationDto == null) {
			return null;
		}
		Notification notification = new Notification();
		notification.setId(notificationDto.getId());
		notification.setReference(notificationDto.getReference());
		notification.setNbreEtoile(notificationDto.getNbreEtoile());
		notification.setObservation(notificationDto.getObservation());
		notification.setChauffeur(ChauffeurDto.fromDtoToEntity(notificationDto.getChauffeurDto()));
	//	notification.setUtilisateur(UtilisateurDto.fromDtoToEntity(notificationDto.getUtilisateurDto()));
		
		return notification;
	}


}
