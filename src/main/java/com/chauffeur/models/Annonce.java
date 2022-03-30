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
	
	@Column(name = "libelle", length = 200)
	private String libelle;
	
	@Column(name = "lieuPoste", length = 150)
	private String lieuPoste;

	@Column(name = "salaire", length = 150)
	private String salaire;
	
	@Column(name = "emailPoste", length = 150)
	private String emailPoste;
	
	@Column(name = "time", length = 200)
	private String time;
	
	@Column(name = "proExperience", length = 150)
	private String proExperience;
	
	@Column(name = "typeContrat", length = 100)
	private String typeContrat;
	
	@Column(name = "isSelected", length = 5)
	private boolean selected;
	
	@Column(name = "status", length = 60)
	private String status;
	
	@Column(name = "description")
	@Lob
	private String description;
	
	@Column(name = "dateCandidature", length = 150)
	private Date dateCandidature;
	
	@Column(name = "dateCloture", length = 150)
	private Date dateCloture;

	@ManyToOne
	@JoinColumn(name = "permId")
	private Permis permis;
	 
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Utilisateur utilisateur;
	
	
	@ManyToOne
	@JoinColumn(name = "addresseId")
	private Addresse addresse;
	
	

}
