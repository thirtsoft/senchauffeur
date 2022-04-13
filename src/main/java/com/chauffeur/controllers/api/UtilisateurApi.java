package com.chauffeur.controllers.api;

import java.io.IOException;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chauffeur.dto.UtilisateurDto;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface UtilisateurApi {
	
	@PostMapping(value = APP_ROOT + "/utilisateurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDto> save(@RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/findById/{idUtilisateur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher et afficher un utilisateur par son ID",
            notes = "Cette méthode permet de rechercher et d'afficher un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste est"),
            @ApiResponse(code = 400, message = "La liste est vide")
    })
    ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable("idUtilisateur") Long id);

    @GetMapping(value = APP_ROOT + "/utilisateurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher et afficher la liste des utilisateurs",
            notes = "Cette méthode permet de rechercher et d'afficher la liste des utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste est"),
            @ApiResponse(code = 400, message = "La liste est vide")
    })
    ResponseEntity<List<UtilisateurDto>> getAllUtilisateurs();

    @GetMapping(value = APP_ROOT + "/utilisateurs/searchAllUtilisateurOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher et afficher la liste des utilisateurs par ordre décroissante",
            notes = "Cette méthode permet de rechercher et d'afficher la liste des utilisateur par ordre décroissante")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste est"),
            @ApiResponse(code = 400, message = "La liste est vide")
    })
    ResponseEntity<List<UtilisateurDto>> getAllUtilisateursOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/utilisateurs/searchUtilisateurByUsername", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDto> getUtilisateurByUsername(@RequestParam(value = "username") String username);

    @GetMapping(value = APP_ROOT + "/utilisateurs/avatar/{id}")
    @ApiOperation(value = "Récupérer et afficher la photo d'un utilisateur par son ID",
            notes = "Cette méthode permet de récupérer et d'afficher la photo d'un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Photo uploadé a été modifié"),
            @ApiResponse(code = 400, message = "Mot de passe non modifié")
    })
    byte[] getPhoto(@PathVariable("id") Long id) throws Exception;

    @PostMapping(value = APP_ROOT + "/utilisateurs/uploadUserPhoto/{id}")
    @ApiOperation(value = "Uploader la photo d'un utilisateur par son ID",
            notes = "Cette méthode permet d'uploader la photo d'un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Photo uploadé a été modifié"),
            @ApiResponse(code = 400, message = "Mot de passe non modifié")
    })
    void uploadUserPhoto(MultipartFile file, @PathVariable("id") Long id) throws IOException;

    @PutMapping(value = APP_ROOT + "/utilisateurs/update/{idUser}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier les informations d'un utilisateur par son ID",
            notes = "Cette méthode permet de modifier les information d'un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "les informations  a été modifié"),
            @ApiResponse(code = 400, message = "Mot de passe non modifié")
    })
    ResponseEntity<UtilisateurDto> updateUtilisateur(@PathVariable(value = "idUser") Long idUser, @RequestBody UtilisateurDto utilisateur);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updateProfil")
    @ApiOperation(value = "Modifier le nom utilisateur par son username",
            notes = "Cette méthode permet de modifier le mot de passe d'un utilisateur par son nom utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le mot de passe a été modifié"),
            @ApiResponse(code = 400, message = "Mot de passe non modifié")
    })
    ResponseEntity<Boolean> updateUserProfil(@RequestBody ObjectNode json);


    @PatchMapping(value = APP_ROOT + "/utilisateurs/updateUsernameOfUserByUsername")
    @ApiOperation(value = "Modifier le nom utilisateur par son username",
            notes = "Cette méthode permet de modifier le mot de passe d'un utilisateur par son nom utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le mot de passe a été modifié"),
            @ApiResponse(code = 400, message = "Mot de passe non modifié")
    })
    ResponseEntity<Boolean> updateUsername(@RequestBody ObjectNode json);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updateUsernameOfUserById")
    @ApiOperation(value = "Modifier le nom d'un utilisateur par son ID",
            notes = "Cette méthode permet de modifier le nom d'un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le mot de passe a été modifié"),
            @ApiResponse(code = 400, message = "Mot de passe non modifié")
    })
    ResponseEntity<Boolean> updateUsernameByUserId(@RequestBody ObjectNode json);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updatePasswordByUsername")
    @ApiOperation(value = "Modifier le mot de passe d'un utilisateur par son username",
            notes = "Cette méthode permet de modifier le mot de passe d'un utilisateur par son nom utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le mot de passe a été modifié"),
            @ApiResponse(code = 400, message = "Mot de passe non modifié")
    })
    ResponseEntity<Boolean> updatePasswordByUsername(@RequestBody ObjectNode json);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updatePasswordByUserId")
    @ApiOperation(value = "Modifier le mot de passe d'un utilisateur par son ID",
            notes = "Cette méthode permet de modifier le mot de passe d'un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le mot de passe a été modifié"),
            @ApiResponse(code = 400, message = "Mot de passe non modifié")
    })
    ResponseEntity<Boolean> updatePasswordByUserId(@RequestBody ObjectNode json);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updateCustomerProfileByUsername")
    @ApiOperation(value = "Modifier le profil d'un utilisateur",
            notes = "Cette méthode permet de modifier le profil d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le profil de l'utlisateur a été modifié"),
            @ApiResponse(code = 400, message = "Profil non modifié")
    })
    ResponseEntity<Boolean> updateCustomerProfileByUsername(@RequestBody ObjectNode json);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/activatedUser/{id}")
    @ApiOperation(value = "Activer un utilisateur",
            notes = "Cette méthode permet d'activer le compter d'un utilisateur pour se connecter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le compte utlisateur a été été activer"),
            @ApiResponse(code = 400, message = "Aucun compte utilisateur activer")
    })
    ResponseEntity<?> activatedUser(@RequestParam("isActive") String isActive, @PathVariable("id") String id);

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{idUtilisateur}")
    @ApiOperation(value = "Supprimer un utilisateur par son ID",
            notes = "Cette méthode permet de supprimer un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilisateur supprimé"),
            @ApiResponse(code = 400, message = "Utilisateur avec cette Id n'existe pas")
    })
    void delete(@PathVariable("idUtilisateur") Long id);

}
