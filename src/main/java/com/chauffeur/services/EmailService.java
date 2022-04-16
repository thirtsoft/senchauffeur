package com.chauffeur.services;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.mail.MailException;

import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.EmailDto;
import com.chauffeur.dto.NewsleterDto;
import com.chauffeur.dto.UtilisateurDto;

import javax.mail.MessagingException;

public interface EmailService {
	
	void sendEmailToManager(EmailDto emailDto) throws MailException;
	
	void responseEmailToCustomer(EmailDto emailDto) throws MailException, MessagingException, UnsupportedEncodingException;
	
	void sendEmailToRecruteur(UtilisateurDto utilisateurDto) throws MailException, MessagingException, UnsupportedEncodingException;
	
	void sendEmailToChauffeur(ChauffeurDto chauffeurDto) throws MailException, MessagingException, UnsupportedEncodingException;

	void sendEmailToNewsletter(NewsleterDto newsletterDto) throws MailException, MessagingException, UnsupportedEncodingException;

	void sendMailToAllNewsletters(NewsleterDto newsletterDto);

	EmailDto findById(Long id);

	List<EmailDto> findByOrderByIdDesc();

	BigDecimal countNumberOfEmailInMonth();

	void delete(Long id);


}
