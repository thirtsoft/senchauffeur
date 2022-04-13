package com.chauffeur.security.service;

import com.chauffeur.models.Utilisateur;
import com.chauffeur.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UtilisateurRepository userRepository;

	/*@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur utilisateur = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
		return UserPrinciple.build(utilisateur);
	}*/

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findByUsername(username).get();
        if (user != null && user.isActive()) {
            return UserPrinciple.build(user);
        } else {
            throw new UsernameNotFoundException("User Not Found with -> username or email : " + username);
        }
    }


}
