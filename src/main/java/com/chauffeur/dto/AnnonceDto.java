
package com.chauffeur.dto;

import java.util.Date;

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
	
	private String emailPoste;
	
	private String salaire;
	
	private String modeCandidature;
	
	private String time;
	
	private String anneeExperience;
	
	private String description;

	private Date dateCandidature;
	
	private Date dateCloture;

    private StatusAnnonce statusAnnonce;

	private PermisDto permisDto;

	private RecruteurDto recruteurDto;
	
	private VilleDto villeDto;
	
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
				.emailPoste(annonce.getEmailPoste())
				.time(annonce.getTime())
				.anneeExperience(annonce.getAnneeExperience())
				.description(annonce.getDescription())
				.dateCandidature(annonce.getDateCandidature())
				.dateCloture(annonce.getDateCloture())
				.statusAnnonce(annonce.getStatusAnnonce())
				.permisDto(PermisDto.fromEntityToDto(annonce.getPermis()))
				.recruteurDto(RecruteurDto.fromEntityToDto(annonce.getRecruteur()))
				.villeDto(VilleDto.fromEntityToDto(annonce.getVille()))
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
		annonce.setTime(annonceDto.getTime());
		annonce.setEmailPoste(annonceDto.getEmailPoste());
		annonce.setAnneeExperience(annonce.getAnneeExperience());
		annonce.setDescription(annonceDto.getDescription());
		annonce.setDateCandidature(annonceDto.getDateCandidature());
		annonce.setDateCloture(annonceDto.getDateCloture());
		annonce.setStatusAnnonce(annonceDto.getStatusAnnonce());
		annonce.setPermis(PermisDto.fromDtoToEntity(annonceDto.getPermisDto()));
		annonce.setRecruteur(RecruteurDto.fromDtoToEntity(annonceDto.getRecruteurDto()));
		annonce.setVille(VilleDto.fromDtoToEntity(annonceDto.getVilleDto()));

		return annonce;
	}

}
