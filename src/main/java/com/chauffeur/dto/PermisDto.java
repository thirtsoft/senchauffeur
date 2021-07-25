package com.chauffeur.dto;

import com.chauffeur.models.Permis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermisDto {
	
	private Long id;
	
	private String typePermis;

	private String designation;
	
	private int validite;
	
	public static PermisDto fromEntityToDto(Permis permis) {
		if (permis == null) {
			return null;
		}
		
		return PermisDto.builder()
				.id(permis.getId())
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
		permis.setId(permisDto.getId());
		permis.setTypePermis(permisDto.getTypePermis());
		permis.setDesignation(permisDto.getDesignation());
		permis.setValidite(permisDto.getValidite());
	
		return permis;
	}


}
