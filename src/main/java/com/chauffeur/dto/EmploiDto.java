package com.chauffeur.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmploiDto {
	
	private long id;
	
	private String reference;

	private String firstName;

	private String lastName;
	
	private String sexe;
	
	private double addressActuel;

	private String email;
	
	private int nbreAnneeExperience;

	private double pretentionSalaire;
	
	private double mobilite;

	private String phoneChauffeur;
	
	private String cvChauffeur;

	private String photoChauffeur;
	
	private PermisDto permisDto;

}
