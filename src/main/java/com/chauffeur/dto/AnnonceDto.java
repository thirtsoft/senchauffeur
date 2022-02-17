
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
	
	private String libelle;
	
	private String lieuPoste;
	
	private String emailPoste;
	
	private String salaire;
	
	private String time;
	
	private String anneeExperience;
	
	private String typeContrat;
	
	private boolean selected;
	
	private String status;
	
	private String description;

	private Date dateCandidature;
	
	private Date dateCloture;

    private StatusAnnonce statusAnnonce;

	private PermisDto permisDto;

	private RecruteurDto recruteurDto;
	
	private VilleDto villeDto;
	
	private AddresseDto addresseDto;
	
	private UtilisateurDto utilisateurDto;
	
	public static AnnonceDto fromEntityToDto(Annonce annonce) {
		if (annonce == null) {
			return null;
		}
		
		return AnnonceDto.builder()
				.id(annonce.getId())
				.reference(annonce.getReference())
				.libelle(annonce.getLibelle())
				.lieuPoste(annonce.getLieuPoste())
				.salaire(annonce.getSalaire())
				.emailPoste(annonce.getEmailPoste())
				.time(annonce.getTime())
				.anneeExperience(annonce.getAnneeExperience())
				.typeContrat(annonce.getTypeContrat())
				.selected(annonce.isSelected())
				.status(annonce.getStatus())
				.description(annonce.getDescription())
				.dateCandidature(annonce.getDateCandidature())
				.dateCloture(annonce.getDateCloture())
				.statusAnnonce(annonce.getStatusAnnonce())
				.permisDto(PermisDto.fromEntityToDto(annonce.getPermis()))
			//	.recruteurDto(RecruteurDto.fromEntityToDto(annonce.getRecruteur()))
				.utilisateurDto(UtilisateurDto.fromEntityToDto(annonce.getUtilisateur()))
	//			.villeDto(VilleDto.fromEntityToDto(annonce.getVille()))
				.addresseDto(AddresseDto.fromEntityToDto(annonce.getAddresse()))
				.build();
		
	}
	
	public static Annonce fromDtoToEntity(AnnonceDto annonceDto) {
		if (annonceDto == null) {
			return null;
		}
		Annonce annonce = new Annonce();
		annonce.setId(annonceDto.getId());
		annonce.setReference(annonceDto.getReference());
		annonce.setLibelle(annonceDto.getLibelle());
		annonce.setLieuPoste(annonceDto.getLieuPoste());
		annonce.setSalaire(annonceDto.getSalaire());
		annonce.setTime(annonceDto.getTime());
		annonce.setEmailPoste(annonceDto.getEmailPoste());
		annonce.setAnneeExperience(annonce.getAnneeExperience());
		annonce.setTypeContrat(annonceDto.getTypeContrat());
		annonce.setSelected(annonceDto.isSelected());
		annonce.setDescription(annonceDto.getDescription());
		annonce.setStatus(annonceDto.getStatus());
		annonce.setDateCandidature(annonceDto.getDateCandidature());
		annonce.setDateCloture(annonceDto.getDateCloture());
		annonce.setStatusAnnonce(annonceDto.getStatusAnnonce());
		annonce.setPermis(PermisDto.fromDtoToEntity(annonceDto.getPermisDto()));
	//	annonce.setRecruteur(RecruteurDto.fromDtoToEntity(annonceDto.getRecruteurDto()));
		annonce.setUtilisateur(UtilisateurDto.fromDtoToEntity(annonceDto.getUtilisateurDto()));
	//	annonce.setVille(VilleDto.fromDtoToEntity(annonceDto.getVilleDto()));
		annonce.setAddresse(AddresseDto.fromDtoToEntity(annonceDto.getAddresseDto()));

		return annonce;
	}

}
