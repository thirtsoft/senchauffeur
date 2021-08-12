package com.chauffeur.message.response;

import java.util.List;


import com.chauffeur.dto.UtilisateurDto;

public class JwtResponse extends UtilisateurDto {
	
	 private String token;
	    private final Long id;
	    private String username;
	    private String email;

	    private String type = "Bearer";

	    private final List<String> roles;
	    //  private Collection<? extends GrantedAuthority> authorities;

	    public JwtResponse(String accessToken, Long id, String username, String email,
	                        List<String> roles) {

	        this.token = accessToken;
	        this.id = id;
	        this.username = username;
	        this.email = email;
	        this.roles = roles;
	    }

	    public String getAccessToken() {
	        return token;
	    }

	    public void setAccessToken(String accessToken) {
	        this.token = accessToken;
	    }

	    public String getTokenType() {
	        return type;
	    }

	    public void setTokenType(String tokenType) {
	        this.type = tokenType;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	   
}
