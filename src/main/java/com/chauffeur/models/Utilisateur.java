package com.chauffeur.models;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "utilisateur", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class Utilisateur implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "username", length = 60)
    private String username;

    @Column(name = "mobile", length = 60)
    private String mobile;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 70)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    
    public Utilisateur(String username,
            String email,
            String password) {
			this.username = username;
			this.email = email;
			this.password = password;
	}
    
    public Utilisateur(String name,
            String username,
            String email,
            String password) {
    	this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
    }
    
    public Utilisateur(String name,
            String username,
            String mobile,
            String email,
            String password) {
			this.name = name;
			this.username = username;
			this.mobile = mobile;
			this.email = email;
			this.password = password;
	}
    
    public Utilisateur() {
    	
    }

	public Utilisateur(Long id, String name, String username, String mobile,
	            String email, String password, Set<Role> roles) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
	

}
