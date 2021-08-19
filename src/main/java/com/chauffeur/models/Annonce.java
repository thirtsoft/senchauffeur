package com.chauffeur.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;
import com.chauffeur.enumeration.StatusAnnonce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	@Column(name = "emailPoste", length = 90)
	private String emailPoste;

	@Column(name = "modeCandidature", length = 50)
	private String modeCandidature;
	
	@Column(name = "time", length = 50)
	private String time;
	
	@Column(name = "anneeExperience")
	private String anneeExperience;
	
	@Column(name = "description")
	@Lob
	private String description;
	
	@Column(name = "dateCandidature", length = 70)
	private Date dateCandidature;
	
	@Column(name = "dateCloture", length = 70)
	private Date dateCloture;

	
	@Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 90)
    private StatusAnnonce statusAnnonce;

//	@ManyToOne
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "permId")
	private Permis permis;
	 
//	@ManyToOne
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "recrId")
	private Recruteur recruteur;
	
	/*
	 * @ManyToOne(cascade = CascadeType.PERSIST)
	 * 
	 * @JoinColumn(name = "villeId") private Ville ville;
	 */
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "addresseId")
	private Addresse addresse;
	
	

}
