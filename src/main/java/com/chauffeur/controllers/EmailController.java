package com.chauffeur.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.EmailApi;
import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.EmailDto;
import com.chauffeur.dto.NewsleterDto;
import com.chauffeur.dto.UtilisateurDto;
import com.chauffeur.services.EmailService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class EmailController implements EmailApi {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    
    @Override
    public ResponseEntity<EmailDto> sendEmailToManager(EmailDto emailDto) {
        try {
        	emailDto.setCreateDate(new Date());
            emailService.sendEmailToManager(emailDto);
            return new ResponseEntity<EmailDto>(emailDto, HttpStatus.CREATED);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<UtilisateurDto> sendMailToRecruteur(UtilisateurDto utilisateurDto) {
        try {
            emailService.sendEmailToRecruteur(utilisateurDto);
            return new ResponseEntity<>(utilisateurDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
	public ResponseEntity<ChauffeurDto> sendMailToChauffeur(ChauffeurDto chauffeurDto) {
    	 try {
             emailService.sendEmailToChauffeur(chauffeurDto);
             return new ResponseEntity<>(chauffeurDto, HttpStatus.OK);
         } catch (MailException e) {
             return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
         }
	}


    @Override
    public ResponseEntity<NewsleterDto> sendMailToCustomer(NewsleterDto newsleterDto) {
        try {
            emailService.sendEmailToNewsletter(newsleterDto);
            return new ResponseEntity<>(newsleterDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<NewsleterDto> sendMailToAllCustomers(NewsleterDto newsletterDto) {
        try {
            emailService.sendMailToAllNewsletters(newsletterDto);
            return new ResponseEntity<>(newsletterDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<EmailDto> getEmailById(Long id) {
        return ResponseEntity.ok(emailService.findById(id));
    }

    @Override
    public ResponseEntity<List<EmailDto>> getAllEmailsOrderByIdDesc() {
        List<EmailDto> emailDtoList = emailService.findByOrderByIdDesc();
        return new ResponseEntity(emailDtoList, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfEmail() {
        return emailService.countNumberOfEmailInMonth();
    }

    @Override
    public void delete(Long id) {
        emailService.delete(id);
    }

	



}
