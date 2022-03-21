package com.chauffeur.dto;

import java.util.Date;

import com.chauffeur.models.Reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
	
	private Long id;

    private Date createdDate;

	private String description;
	
	private String status;
	
	private Date dateDemarrage;

    private ChauffeurDto chauffeurDto;

	private UtilisateurDto utilisateurDto;
	
	public static ReservationDto fromEntityToDto(Reservation reservation) {
		if (reservation == null) {
			return null;
		}
		
		return ReservationDto.builder()
				.id(reservation.getId())
				.description(reservation.getDescription())
				.status(reservation.getStatus())
				.dateDemarrage(reservation.getDateDemarrage())
				.createdDate(reservation.getCreatedDate())
				.utilisateurDto(UtilisateurDto.fromEntityToDto(reservation.getUtilisateur()))
				.chauffeurDto(ChauffeurDto.fromEntityToDto(reservation.getChauffeur()))
				.build();
		
	}
	
	public static Reservation fromDtoToEntity(ReservationDto reservationDto) {
		if (reservationDto == null) {
			return null;
		}
		
		Reservation reservation = new Reservation();
		reservation.setId(reservationDto.getId());
		reservation.setDescription(reservationDto.getDescription());
		reservation.setStatus(reservationDto.getStatus());
		reservation.setDateDemarrage(reservationDto.getDateDemarrage());
		reservation.setCreatedDate(reservationDto.getCreatedDate());
		reservation.setUtilisateur(UtilisateurDto.fromDtoToEntity(reservationDto.getUtilisateurDto()));
		reservation.setChauffeur(ChauffeurDto.fromDtoToEntity(reservationDto.getChauffeurDto()));
		
		
		return reservation;
	}




}
