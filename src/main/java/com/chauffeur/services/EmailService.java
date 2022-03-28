package com.chauffeur.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.mail.MailException;

import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.EmailDto;
import com.chauffeur.dto.NewsleterDto;
import com.chauffeur.dto.UtilisateurDto;

public interface EmailService {
	
	void sendEmailToManager(EmailDto emailDto) throws MailException;
	
	void sendEmailToRecruteur(UtilisateurDto utilisateurDto) throws MailException;
	
	void sendEmailToChauffeur(ChauffeurDto chauffeurDto) throws MailException;

	void sendEmailToNewsletter(NewsleterDto newsletterDto) throws MailException;

	void sendMailToAllNewsletters(NewsleterDto newsletterDto);

	EmailDto findById(Long id);

	List<EmailDto> findByOrderByIdDesc();

	BigDecimal countNumberOfEmailInMonth();

	void delete(Long id);


}
