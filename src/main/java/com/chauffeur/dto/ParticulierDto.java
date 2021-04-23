package com.chauffeur.dto;

import com.chauffeur.models.Particulier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticulierDto {
	
	private long id;
	
	private String firstName;

	private String lastName;

	private String email;

	private String phoneParticulier;

	private String mobileParticulier;

	private String addressParticulier;

	private String villeParticulier;
	
	public static ParticulierDto fromEntityToDto(Particulier particulier) {
		if (particulier == null) {
			return null;
		}
		
		return ParticulierDto.builder()
				.firstName(particulier.getFirstName())
				.lastName(particulier.getLastName())
				.email(particulier.getEmail())
				.phoneParticulier(particulier.getPhoneParticulier())
				.mobileParticulier(particulier.getMobileParticulier())
				.addressParticulier(particulier.getAddressParticulier())
				.villeParticulier(particulier.getVilleParticulier())
				.build();
		
	}
	
	public static Particulier fromDtoToEntity(ParticulierDto particulierDto) {
		if (particulierDto == null) {
			return null;
		}
		Particulier particulier = new Particulier();
		particulier.setFirstName(particulierDto.getFirstName());
		particulier.setLastName(particulierDto.getLastName());
		particulier.setEmail(particulierDto.getEmail());
		particulier.setPhoneParticulier(particulierDto.getPhoneParticulier());
		particulier.setMobileParticulier(particulierDto.getMobileParticulier());
		particulier.setAddressParticulier(particulierDto.getAddressParticulier());
		particulier.setVilleParticulier(particulierDto.getVilleParticulier());
	
		return particulier;
	}

}
