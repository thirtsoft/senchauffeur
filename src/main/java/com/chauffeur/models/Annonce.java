package com.chauffeur.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Folio9470m
 *
 */
@Entity
@Table(name = "annonce", uniqueConstraints = {
        @UniqueConstraint(columnNames = "reference")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Annonce implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "reference", length = 50)
	private String reference;
	
	@Column(name = "libelle", length = 100)
	private String libelle;
	
	@Column(name = "lieuPoste", length = 90)
	private String lieuPoste;

	@Column(name = "salaire", length = 90)
	private String salaire;
	
	@Column(name = "emailPoste", length = 100)
	private String emailPoste;
	
	@Column(name = "time", length = 100)
	private String time;
	
	@Column(name = "experience", length = 170)
	private String experience;
	
	@Column(name = "typeContrat", length = 100)
	private String typeContrat;
	
	@Column(name = "isSelected")
	private boolean selected;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "description")
	@Lob
	private String description;
	
	@Column(name = "dateCandidature", length = 150)
	private Date dateCandidature;
	
	@Column(name = "dateCloture", length = 150)
	private Date dateCloture;

//	@ManyToOne(cascade = CascadeType.PERSIST)
	@ManyToOne
	@JoinColumn(name = "permId")
	private Permis permis;
	 
	/*
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "recrId")
	private Recruteur recruteur;
	*/
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Utilisateur utilisateur;
	
	
//	@ManyToOne(cascade = CascadeType.PERSIST)
	@ManyToOne
	@JoinColumn(name = "addresseId")
	private Addresse addresse;
	
	

}
