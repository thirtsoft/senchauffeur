package com.chauffeur.controllers.api;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chauffeur.message.request.LoginForm;
import com.chauffeur.message.request.SignUpForm;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface AuthApi {
	
	@PostMapping(value = APP_ROOT + "/auth/authenticated", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm);


    @PostMapping(value = APP_ROOT + "/auth/signUp", 
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> signUp(@Valid @RequestBody SignUpForm signUpForm);


    @PostMapping(value = APP_ROOT + "/auth/registerUser", 
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpForm);

}
