package com.chauffeur.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chauffeur")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chauffeur implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "reference", length = 50)
	private String reference;
	
	@Column(name = "firstName", length = 90)
	private String firstName;

	@Column(name = "lastName", length = 70)
	private String lastName;
	
	@Column(name = "sexe", length = 30)
	private String sexe;
	
	@Column(name = "addressActuel", length = 90)
	private String addressActuel;

	@Column(name = "email", length = 90)
	private String email;
	
	@Column(name = "phoneChauffeur", length = 30)
	private String phoneChauffeur;
	
	@Column(name = "experience", length = 100)
	private String nbreAnneeExperience;
	
	@Column(name = "pretentionSalaire", length = 90)
	private Double pretentionSalaire;
	
	@Column(name = "mobilite", length = 100)
	private String mobilite;
	
	@Column(name = "disponibility", length = 100)
	private String disponibity;
	
	private boolean selected;

	@Column(name = "cvChauffeur", length = 30)
	private String cvChauffeur;
	
	@Column(name = "photoChauffeur", length = 30)
	private String photoChauffeur;
	
	@Column(name = "dateInscription", length = 70)
	private Date dateInscription;

//@ManyToOne
//	@ManyToOne(cascade = CascadeType.PERSIST)
	@ManyToOne
	@JoinColumn(name = "permId")
	private Permis permis;
	
//	@ManyToOne(cascade = CascadeType.PERSIST)
	@ManyToOne
	@JoinColumn(name = "addressId")
	private Addresse addresse;

	public Chauffeur(Long id, String reference, String firstName, String lastName, String sexe, String addressActuel,
			String email, String phoneChauffeur, String nbreAnneeExperience, Double pretentionSalaire, String mobilite,
			String disponibity, boolean selected, String cvChauffeur, String photoChauffeur, Permis permis,
			Addresse addresse) {
		this.id = id;
		this.reference = reference;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sexe = sexe;
		this.addressActuel = addressActuel;
		this.email = email;
		this.phoneChauffeur = phoneChauffeur;
		this.nbreAnneeExperience = nbreAnneeExperience;
		this.pretentionSalaire = pretentionSalaire;
		this.mobilite = mobilite;
		this.disponibity = disponibity;
		this.selected = selected;
		this.cvChauffeur = cvChauffeur;
		this.photoChauffeur = photoChauffeur;
		this.permis = permis;
		this.addresse = addresse;
	}
	
	

}
