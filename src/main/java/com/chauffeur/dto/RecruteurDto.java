package com.chauffeur.dto;

import com.chauffeur.models.Recruteur;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecruteurDto {
	
	private long id;

	private String firstName;

	private String lastName;

	private String nomEntreprise;
	
	private String addressRecruteur;

	private String email;

	private String phoneRecruteur;
	
	private String website;

	private String secteurActivite;
	
	private String villeRecruteur;
	
	public static RecruteurDto fromEntityToDto(Recruteur recruteur) {
		if (recruteur == null) {
			return null;
		}
		
		return RecruteurDto.builder()
				.firstName(recruteur.getFirstName())
				.lastName(recruteur.getLastName())
				.addressRecruteur(recruteur.getAddressRecruteur())
				.phoneRecruteur(recruteur.getPhoneRecruteur())
				.email(recruteur.getEmail())
				.nomEntreprise(recruteur.getNomEntreprise())
				.secteurActivite(recruteur.getSecteurActivite())
				.website(recruteur.getWebsite())
				.villeRecruteur(recruteur.getVilleRecruteur())
				
				.build();
		
	}
	
	public static Recruteur fromDtoToEntity(RecruteurDto recruteurDto) {
		if (recruteurDto == null) {
			return null;
		}
		Recruteur recruteur = new Recruteur();
		recruteur.setFirstName(recruteurDto.getFirstName());
		recruteur.setLastName(recruteurDto.getLastName());
		recruteur.setAddressRecruteur(recruteurDto.getAddressRecruteur());
		recruteur.setPhoneRecruteur(recruteurDto.getPhoneRecruteur());
		recruteur.setEmail(recruteurDto.getEmail());
		recruteur.setNomEntreprise(recruteurDto.getNomEntreprise());
		recruteur.setSecteurActivite(recruteurDto.getSecteurActivite());
		recruteur.setWebsite(recruteurDto.getWebsite());
		recruteur.setVilleRecruteur(recruteurDto.getVilleRecruteur());
	
		return recruteur;
	}


}
