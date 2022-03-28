package com.chauffeur.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.EmailDto;
import com.chauffeur.dto.NewsleterDto;
import com.chauffeur.dto.UtilisateurDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Email;
import com.chauffeur.repository.EmailRepository;
import com.chauffeur.services.EmailService;
import com.chauffeur.services.NewsleterService;
import com.chauffeur.services.UtilisateurService;
import com.chauffeur.utils.EmailConstants;


import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class EmailServiceImpl implements EmailService {
	
	private final EmailRepository emailRepository;

    private final JavaMailSender javaMailSender;

    private final NewsleterService newsleterService;
    
    public EmailServiceImpl(EmailRepository emailRepository,
				            JavaMailSender javaMailSender,
				            NewsleterService newsleterService) {
    	this.emailRepository = emailRepository;
		this.javaMailSender = javaMailSender;
		this.newsleterService = newsleterService;
    }

	
	
	@Override
	public void sendEmailToManager(EmailDto emailDto) throws MailException {
		StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + emailDto.getCustomerName()).append(System.lineSeparator());
        sb.append("\n Subject : " + emailDto.getSubject());
        sb.append("\n Message : " + emailDto.getMessage());

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(EmailConstants.to);
        mail.setFrom(emailDto.getRecipient());
        mail.setSubject(emailDto.getSubject());
        mail.setText(emailDto.getMessage());

        emailDto.setCreateDate(new Date());
        emailDto.setCustomerName(emailDto.getCustomerName());

        System.out.println(emailDto);

        javaMailSender.send(mail);

        EmailDto.fromEntityToDto(
                emailRepository.save(
                        EmailDto.fromDtoToEntity(emailDto)
                )
        );
		
	}

	@Override
	public void sendEmailToRecruteur(UtilisateurDto utilisateurDto) throws MailException {
		StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + EmailConstants.managerName).append(System.lineSeparator());
        sb.append("\n Subject : " + utilisateurDto.getSubject());
        sb.append("\n Message : " + utilisateurDto.getMessage());

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(utilisateurDto.getEmail());
        mail.setFrom(EmailConstants.from);
        mail.setSubject(utilisateurDto.getSubject());
        mail.setText(utilisateurDto.getMessage());

        javaMailSender.send(mail);

		
	}
	
	@Override
	public void sendEmailToChauffeur(ChauffeurDto chauffeurDto) throws MailException {
		StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + EmailConstants.managerName).append(System.lineSeparator());
        sb.append("\n Subject : " + chauffeurDto.getSubject());
        sb.append("\n Message : " + chauffeurDto.getMessage());

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(chauffeurDto.getEmail());
        mail.setFrom(EmailConstants.from);
        mail.setSubject(chauffeurDto.getSubject());
        mail.setText(chauffeurDto.getMessage());

        javaMailSender.send(mail);
		
	}


	@Override
	public void sendEmailToNewsletter(NewsleterDto newsletterDto) throws MailException {
		StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + EmailConstants.managerName).append(System.lineSeparator());
        sb.append("\n Subject : " + newsletterDto.getSubject());
        sb.append("\n Message : " + newsletterDto.getMessage());

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(newsletterDto.getEmailVisiteur());
        mail.setFrom(EmailConstants.from);
        mail.setSubject(newsletterDto.getSubject());
        mail.setText(newsletterDto.getMessage());

        javaMailSender.send(mail);
		
	}

	@Override
	public void sendMailToAllNewsletters(NewsleterDto newsletterDto) {
		 StringBuilder sb = new StringBuilder();
	     sb.append("Nom : " + EmailConstants.managerName).append(System.lineSeparator());
	     sb.append("\n Subject : " + newsletterDto.getSubject());
	     sb.append("\n Message : " + newsletterDto.getMessage());

	     List<NewsleterDto> newsletterDtos = newsleterService.findAll();

	     SimpleMailMessage mail = new SimpleMailMessage();

	     for (int i = 0; i < newsletterDtos.size(); i++) {
	    	 NewsleterDto newsletterDtoResult = newsletterDtos.get(i);
	         mail.setTo(newsletterDtoResult.getEmailVisiteur());
	         mail.setSubject(newsletterDtoResult.getSubject());
	         mail.setText(newsletterDtoResult.getMessage());
	         mail.setFrom(EmailConstants.from);
	     }

	     javaMailSender.send(mail);
		
	}

	@Override
    public EmailDto findById(Long id) {
        if (id == null) {
            log.error("Notification Id is null");
            return null;
        }

        Optional<Email> optionalEmail = emailRepository.findById(id);

        return Optional.of(EmailDto.fromEntityToDto(optionalEmail.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Email avec l'Id = " + id + "n'a été trouvé")
        );
    }


    @Override
    public List<EmailDto> findByOrderByIdDesc() {
        return emailRepository.findByOrderByIdDesc().stream()
                .map(EmailDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal countNumberOfEmailInMonth() {
        return emailRepository.countNumberOfEmail();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Email Id is null");
            return;
        }

        emailRepository.deleteById(id);
    }


}
