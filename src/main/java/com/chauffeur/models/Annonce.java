package com.chauffeur.models;

import java.io.Serializable;
import java.time.LocalDate;

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

import org.hibernate.annotations.NaturalId;
import com.chauffeur.enumeration.StatusAnnonce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "annonce")
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
	
	@Column(name = "lieuPoste", length = 90)
	private String lieuPoste;

	@Column(name = "salaire", length = 90)
	private String salaire;

	@Column(name = "dateCandidature", length = 70)
	private LocalDate dateCandidature;
	
	@Column(name = "dateCloture", length = 70)
	private LocalDate dateCloture;

	@Column(name = "modeCandidature", length = 50)
	private String modeCandidature;
	
	@Column(name = "time", length = 50)
	private String time;
	
	@Column(name = "anneeExperience")
	private String anneeExperience;
	
	@Column(name = "description")
	@Lob
	private String description;
	
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
	
	

}
