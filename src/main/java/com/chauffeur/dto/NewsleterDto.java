package com.chauffeur.dto;

import java.util.Date;

import com.chauffeur.models.Newsleter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsleterDto {
	
	private Long id;

    private String emailVisiteur;
    
    private String subject;
	 
	 private String message;
    
    private Date createdDate;
    
    public static NewsleterDto fromEntityToDto(Newsleter newsleter) {
    	if (newsleter == null) {
    		return null;
    	}
    	return NewsleterDto.builder()
    			.id(newsleter.getId())
    			.emailVisiteur(newsleter.getEmailVisiteur())
    			.createdDate(newsleter.getCreatedDate())
    			.build();
    }
    
    public static Newsleter fromDtoToEntity(NewsleterDto newsleterDto) {
    	if (newsleterDto == null) {
    		return null;
    	}
    	Newsleter newsleter = new Newsleter();
    	newsleter.setId(newsleterDto.getId());
    	newsleter.setEmailVisiteur(newsleterDto.getEmailVisiteur());
    	newsleter.setCreatedDate(newsleterDto.createdDate);
    	
    	return newsleter;
    }
    

}
