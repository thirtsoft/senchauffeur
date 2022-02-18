package com.chauffeur.dto;

import java.util.Date;

import com.chauffeur.models.HistoriqueAnnonce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoriqueAnnonceDto {
	
	private Long id;

	private String action;

	private Date createdDate;
	
    private AnnonceDto annonceDto;
	
	public static HistoriqueAnnonceDto fromEntityToDto(HistoriqueAnnonce historiqueAnnonce) {
		if (historiqueAnnonce == null) {
			return null;
		}
		
		return HistoriqueAnnonceDto.builder()
				.id(historiqueAnnonce.getId())
				.action(historiqueAnnonce.getAction())
				.createdDate(historiqueAnnonce.getCreatedDate())
				.annonceDto(AnnonceDto.fromEntityToDto(historiqueAnnonce.getAnnonce()))
				.build();
		
	}
	
	public static HistoriqueAnnonce fromDtoToEntity(HistoriqueAnnonceDto historiqueAnnonceDto) {
		if (historiqueAnnonceDto == null) {
			return null;
		}
		
		HistoriqueAnnonce historiqueAnnonce = new HistoriqueAnnonce();
		historiqueAnnonce.setId(historiqueAnnonceDto.getId());
		historiqueAnnonce.setAction(historiqueAnnonceDto.getAction());
		historiqueAnnonce.setCreatedDate(historiqueAnnonceDto.getCreatedDate());
		historiqueAnnonce.setAnnonce(AnnonceDto.fromDtoToEntity(historiqueAnnonceDto.getAnnonceDto()));
		
		return historiqueAnnonce;
	}


}
