package com.chauffeur.dto;

import java.util.Date;

import com.chauffeur.models.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
	
	private Long id;

    private String customerName;

    private String recipient;

    private String subject;

    private String message;

    private Date createDate;

    private UtilisateurDto utilisateurDto;

    private NewsleterDto newsleterDto;
    
    private ChauffeurDto chauffeurDto;

    public static EmailDto fromEntityToDto(Email email) {
        if (email == null) {
            return null;
        }

        return EmailDto.builder()
                .id(email.getId())
                .customerName(email.getCustomerName())
                .recipient(email.getRecipient())
                .subject(email.getSubject())
                .message(email.getMessage())
                .createDate(email.getCreateDate())
                .utilisateurDto(UtilisateurDto.fromEntityToDto(email.getUtilisateur()))
                .chauffeurDto(ChauffeurDto.fromEntityToDto(email.getChauffeur()))
                .newsleterDto(NewsleterDto.fromEntityToDto(email.getNewsleter()))
                .build();
    }

    public static Email fromDtoToEntity(EmailDto emailDto) {
        if (emailDto == null) {
            return null;
        }

        Email email = new Email();
        email.setId(emailDto.getId());
        email.setCustomerName(emailDto.getCustomerName());
        email.setRecipient(emailDto.getRecipient());
        email.setSubject(emailDto.getSubject());
        email.setMessage(emailDto.getMessage());
        email.setCreateDate(emailDto.getCreateDate());
        email.setUtilisateur(UtilisateurDto.fromDtoToEntity(emailDto.getUtilisateurDto()));
        email.setChauffeur(ChauffeurDto.fromDtoToEntity(emailDto.getChauffeurDto()));
        email.setNewsleter(NewsleterDto.fromDtoToEntity(emailDto.getNewsleterDto()));

        return email;
    }


}
