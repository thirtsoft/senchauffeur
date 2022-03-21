package com.chauffeur.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarif")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarif implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "reference", length = 50)
	private String reference;
	
	@Column(name = "montant", length = 50)
	private String montantTarif;
	
	@Column(name = "description")
	@Lob
	private String description;
	
	/*
	@ManyToOne
	@JoinColumn(name = "annonceId")
	private Annonce annonce; */
	
	@ManyToOne
	@JoinColumn(name = "typeAnnonceId")
	private TypeAnnonce typeAnnonce;

}
