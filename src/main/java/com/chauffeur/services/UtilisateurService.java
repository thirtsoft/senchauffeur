package com.chauffeur.services;

import java.util.List;

import com.chauffeur.dto.UtilisateurDto;
import com.chauffeur.enumeration.RoleName;

public interface UtilisateurService {
	
	UtilisateurDto save(UtilisateurDto utilisateurDto);
	
	UtilisateurDto update(Long id, UtilisateurDto utilisateurDto);
	
	void addRoleToUser(String username, RoleName roleName);

	UtilisateurDto findById(Long id);

    UtilisateurDto findByUsername(String username);

    boolean updateUsernameOfUtilisateurByUsername(String username, String newUsername);

    boolean updateUsernameOfUtilisateurByUserId(String id, String newUsername);

    boolean updateCustomerPasswordByUsername(String username, String oldPass, String newPass);

    boolean updateCustomerPasswordByUserId(String id, String oldPass, String newPass);

    boolean updateCustomerProfileByUsername(String username, String name, String newUsername, String email, String mobile);

    List<UtilisateurDto> findAll();
    
    List<UtilisateurDto> findByOrderByIdDesc();

    void delete(Long id);


}
