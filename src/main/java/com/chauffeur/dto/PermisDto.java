package com.chauffeur.dto;

import com.chauffeur.models.Permis;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PermisDto {
	
	private long id;
	
	private String typePermis;

	private String designation;
	
	private int validite;
	
	public static PermisDto fromEntityToDto(Permis permis) {
		if (permis == null) {
			return null;
		}
		
		return PermisDto.builder()
				.typePermis(permis.getTypePermis())
				.designation(permis.getDesignation())
				.validite(permis.getValidite())
				.build();
		
	}
	
	public static Permis fromDtoToEntity(PermisDto permisDto) {
		if (permisDto == null) {
			return null;
		}
		Permis permis = new Permis();
		permis.setTypePermis(permisDto.getTypePermis());
		permis.setDesignation(permisDto.getDesignation());
		permis.setValidite(permisDto.getValidite());
	
		return permis;
	}


}
