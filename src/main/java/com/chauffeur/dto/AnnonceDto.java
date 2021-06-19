
package com.chauffeur.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Lob;

import com.chauffeur.enumeration.StatusAnnonce;
import com.chauffeur.models.Annonce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnonceDto {
	
	private Long id;

	private String reference;
	
	private String lieuPoste;

	private String salaire;

	private LocalDate dateCandidature;
	
	private LocalDate dateCloture;

	private String modeCandidature;
	
	private String anneeExperience;
	
	private String description;
	
    private StatusAnnonce statusAnnonce;

	private PermisDto permisDto;

	private RecruteurDto recruteurDto;
	
	public static AnnonceDto fromEntityToDto(Annonce annonce) {
		if (annonce == null) {
			return null;
		}
		
		return AnnonceDto.builder()
				.id(annonce.getId())
				.reference(annonce.getReference())
				.lieuPoste(annonce.getLieuPoste())
				.salaire(annonce.getSalaire())
				.modeCandidature(annonce.getModeCandidature())
				.dateCandidature(annonce.getDateCandidature())
				.dateCloture(annonce.getDateCloture())
				.statusAnnonce(annonce.getStatusAnnonce())
				.permisDto(PermisDto.fromEntityToDto(annonce.getPermis()))
				.recruteurDto(RecruteurDto.fromEntityToDto(annonce.getRecruteur()))
				.build();
		
	}
	
	public static Annonce fromDtoToEntity(AnnonceDto annonceDto) {
		if (annonceDto == null) {
			return null;
		}
		Annonce annonce = new Annonce();
		annonce.setReference(annonceDto.getReference());
		annonce.setLieuPoste(annonceDto.getLieuPoste());
		annonce.setSalaire(annonceDto.getSalaire());
		annonce.setModeCandidature(annonceDto.getModeCandidature());
		annonce.setDateCandidature(annonceDto.getDateCandidature());
		annonce.setDateCloture(annonceDto.getDateCloture());
		annonce.setStatusAnnonce(annonceDto.getStatusAnnonce());
		annonce.setPermis(PermisDto.fromDtoToEntity(annonceDto.getPermisDto()));
		annonce.setRecruteur(RecruteurDto.fromDtoToEntity(annonceDto.getRecruteurDto()));

		return annonce;
	}

}
