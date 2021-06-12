package com.chauffeur.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recruteur")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruteur implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "firstName", length = 90)
	private String firstName;

	@Column(name = "lastName", length = 70)
	private String lastName;

	@Column(name = "nomEntreprise", length = 90)
	private String nomEntreprise;
	
	@Column(name = "website", length = 90)
	private String website;

	@Column(name = "secteurActivite", length = 100)
	private String secteurActivite;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "phoneRecruteur", length = 30)
	private String phoneRecruteur;
	
	@Column(name = "addressRecruteur", length = 30)
	private String addressRecruteur;
	
	@Column(name = "villeRecruteur", length = 30)
	private String villeRecruteur;

}
