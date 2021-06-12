package com.chauffeur.dto;

import com.chauffeur.models.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto {
	
	private Long id;

    private String name;
    
    private String username;

    private String mobile;

    private String email;

    private String password;
	
	public static UtilisateurDto fromEntityToDto(Utilisateur utilisateur) {
		if (utilisateur == null) {
			return null;
		}
		
		return UtilisateurDto.builder()
				.id(utilisateur.getId())
				.name(utilisateur.getName())
				.username(utilisateur.getUsername())
				.mobile(utilisateur.getMobile())
				.email(utilisateur.getEmail())
				.password(utilisateur.getPassword())
				.build();
		
	}
	
	public static Utilisateur fromDtoToEntity(UtilisateurDto utilisateurDto) {
		if (utilisateurDto == null) {
			return null;
		}
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setUsername(utilisateurDto.getUsername());
		utilisateur.setMobile(utilisateurDto.getMobile());
		utilisateur.setEmail(utilisateurDto.getEmail());
		utilisateur.setPassword(utilisateurDto.getPassword());
	
	
		return utilisateur;
	}


}
