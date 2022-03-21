package com.chauffeur.dto;

import java.util.Date;

import com.chauffeur.models.TypeAnnonce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeAnnonceDto {
	
	private Long id;
	
    private String code;

    private String libelle;
    
    private Date createdDate;
    
    public static TypeAnnonceDto fromEntityToDto(TypeAnnonce typeAnnonce) {
		if (typeAnnonce == null) {
			return null;
		}
		
		return TypeAnnonceDto.builder()
				.id(typeAnnonce.getId())
				.code(typeAnnonce.getCode())
				.libelle(typeAnnonce.getLibelle())
				.createdDate(typeAnnonce.getCreatedDate())
				.build();
		
	}
	
	public static TypeAnnonce fromDtoToEntity(TypeAnnonceDto typeAnnonceDto) {
		if (typeAnnonceDto == null) {
			return null;
		}
		
		TypeAnnonce typeAnnonce = new TypeAnnonce();
		typeAnnonce.setId(typeAnnonceDto.getId());
		typeAnnonce.setCode(typeAnnonceDto.getCode());
		typeAnnonce.setLibelle(typeAnnonceDto.getLibelle());
		typeAnnonce.setCreatedDate(typeAnnonceDto.getCreatedDate());
		
		return typeAnnonce;
	}

}
