package com.chauffeur.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.AuthApi;
import com.chauffeur.enumeration.RoleName;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.message.request.LoginForm;
import com.chauffeur.message.request.SignUpForm;
import com.chauffeur.message.response.JwtResponse;
import com.chauffeur.models.Role;
import com.chauffeur.models.Utilisateur;
import com.chauffeur.repository.RoleRepository;
import com.chauffeur.repository.UtilisateurRepository;
import com.chauffeur.security.jwt.JwtsProvider;
import com.chauffeur.security.service.UserPrinciple;

@RestController
@CrossOrigin
public class AuthController implements AuthApi {
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtsProvider jwtsProvider;

   
    @Override
    public ResponseEntity<?> authenticateUser(LoginForm loginForm) {
    	
    	Authentication authentication = authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(
    					loginForm.getUsername(), loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtsProvider.generatedJwtToken(authentication);

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        List<String> roles = userPrinciple.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userPrinciple.getId(),
                userPrinciple.getUsername(),
                userPrinciple.getEmail(),
                roles));
    }
    
    @Override
    public ResponseEntity<?> registerUser(SignUpForm signUpForm) {
        if (utilisateurRepository.existsByUsername(signUpForm.getUsername())) {
            throw new ResourceNotFoundException("Fail -> Error: Username is already taken!");
        }
        if (utilisateurRepository.existsByEmail(signUpForm.getEmail())) {
            throw new ResourceNotFoundException("Error: Email is already in use!");
        }
        // Create new user's account
        Utilisateur utilisateur = new Utilisateur(
        		signUpForm.getName(),
                signUpForm.getUsername(),
                signUpForm.getEmail(),
                encoder.encode(signUpForm.getPassword()
                )
        );
        //      Set<String> strRoles = signUpForm.getRole();
        String[] strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            roles.add(roleRepository.findByName(RoleName.ROLE_USER).get());
        }

        for (String role : strRoles) {
            switch (role.toLowerCase()) {
                case "admin":
                    roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN).get());
                    break;

                case "recruteur":
                    roles.add(roleRepository.findByName(RoleName.ROLE_RECRUTEUR).get());
                    break;

                case "user":
                    roles.add(roleRepository.findByName(RoleName.ROLE_USER).get());
                    break;

                default:
                    return ResponseEntity.badRequest().body("Specified role not found");

            }
        }


        utilisateur.setRoles(roles);
        return ResponseEntity.ok(utilisateurRepository.save(utilisateur));

    }


	@Override
	public ResponseEntity<?> signUp(@Valid SignUpForm signUpForm) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
