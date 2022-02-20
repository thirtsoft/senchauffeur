package com.chauffeur.controllers.api;

import java.io.IOException;
import java.util.List;

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
	
	@PutMapping(value = APP_ROOT + "/utilisateurs/update/{idUtilisateur}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<UtilisateurDto> update(@PathVariable("idUtilisateur") Long id, @RequestBody UtilisateurDto utilisateurDto);


	@GetMapping(value = APP_ROOT + "/utilisateurs/findById/{idUtilisateur}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable("idUtilisateur") Long id);
	
	@GetMapping(value = APP_ROOT + "/utilisateurs/searchUtilisateurByUsername", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDto> getUtilisateurByUsername(@RequestParam(value = "username") String username);

	@GetMapping(value = APP_ROOT + "/utilisateurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<UtilisateurDto> getAllUtilisateurs();
	
	@GetMapping(value = APP_ROOT + "/utilisateurs/searchAllUtilisateurOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UtilisateurDto>> getAllUtilisateursOrderByIdDesc();
	
	@GetMapping(value = APP_ROOT + "/utilisateurs/avatar/{id}")
	byte[] getPhoto(@PathVariable("id") Long id) throws Exception;

	@PostMapping(value = APP_ROOT + "/utilisateurs/uploadUserPhoto/{id}")
	void uploadUserPhoto(MultipartFile file, @PathVariable("id") Long id) throws IOException;
	
	@PatchMapping(value = APP_ROOT + "/utilisateurs/updateUsernameOfUserByUsername")
    ResponseEntity<Boolean> updateUsername(@RequestBody ObjectNode json);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updateUsernameOfUserById")
    ResponseEntity<Boolean> updateUsernameByUserId(@RequestBody ObjectNode json);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updatePasswordByUsername")
    ResponseEntity<Boolean> updatePasswordByUsername(@RequestBody ObjectNode json);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updatePasswordByUserId")
    ResponseEntity<Boolean> updatePasswordByUserId(@RequestBody ObjectNode json);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updateCustomerProfileByUsername")
    ResponseEntity<Boolean> updateCustomerProfileByUsername(@RequestBody ObjectNode json);

	@DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{idUtilisateur}")
	void delete(@PathVariable("idUtilisateur") Long id);

}
