package com.chauffeur.controllers.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.EmailDto;
import com.chauffeur.dto.NewsleterDto;
import com.chauffeur.dto.UtilisateurDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface EmailApi {
	
	@PostMapping(value = APP_ROOT + "/emails/sendMailToManager")
    @ApiOperation(value = "Envoyer un email au Manager du site",
            notes = "Cette méthode permet d'envoyer un email au Manager du site",
            response = EmailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<EmailDto> sendEmailToManager(@RequestBody EmailDto emailDto);

    @PostMapping(value = APP_ROOT + "/emails/sendToRecruteur")
    @ApiOperation(value = "Envoyer un email à un Recruteur",
            notes = "Cette méthode permet d'envoyer un email à un Recruteur",
            response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<UtilisateurDto> sendMailToRecruteur(@RequestBody UtilisateurDto utilisateurDto);
    
    @PostMapping(value = APP_ROOT + "/emails/sendToChauffeur")
    @ApiOperation(value = "Envoyer un email à un Chauffeur",
            notes = "Cette méthode permet d'envoyer un email à un Chauffeur",
            response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<ChauffeurDto> sendMailToChauffeur(@RequestBody ChauffeurDto chauffeurDto);

    @PostMapping(value = APP_ROOT + "/emails/sendToNewsletter")
    @ApiOperation(value = "Envoyer un email à un visiteur",
            notes = "Cette méthode permet d'envoyer un email à un visiteur",
            response = EmailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<NewsleterDto> sendMailToCustomer(@RequestBody NewsleterDto newsletterDto);

    @PostMapping(value = APP_ROOT + "/emails/sendMailToAllCustomers")
    @ApiOperation(value = "Envoyer un email à plusieurs Clients",
            notes = "Cette méthode permet d'envoyer un email à plusieurs Clients",
            response = EmailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<NewsleterDto> sendMailToAllCustomers(@RequestBody NewsleterDto newsletterDto);


    @GetMapping(value = APP_ROOT + "/emails/findById/{idEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Email par ID",
            notes = "Cette méthode permet de chercher un Email par son ID", response = EmailDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Email a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Email n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<EmailDto> getEmailById(@PathVariable("idEmail") Long id);

    @GetMapping(value = APP_ROOT + "/emails/searchAllEmailsOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Emails par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Emails par ordre descroissante",
            responseContainer = "List<EmailDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Emails  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<EmailDto>> getAllEmailsOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/emails/countNumberOfEmailInMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Email dans le moi",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Email / le nombre est nulle")
    })
    BigDecimal countNumberOfEmail();

    @DeleteMapping(value = APP_ROOT + "/emails/delete/{idEmail}")
    @ApiOperation(value = "Supprimer un Email par son ID",
            notes = "Cette méthode permet de supprimer une Email par son ID", response = EmailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Notification a été supprimé")
    })
    void delete(@PathVariable("idEmail") Long id);

}
