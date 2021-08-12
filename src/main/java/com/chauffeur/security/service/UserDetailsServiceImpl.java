package com.chauffeur.security.service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chauffeur.models.Utilisateur;
import com.chauffeur.repository.UtilisateurRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	UtilisateurRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisateur utilisateur = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

        return UserPrinciple.build(utilisateur);
//               return UserPrinciple.build(UtilisateurPOSTDto.fromEntityToDto(utilisateur));
    }


}
