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
@Table(name = "chauffeur")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Addresse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "reference", length = 50)
	private String reference;

	@Column(name = "quartier", length = 90)
	private String quartier;

	@Column(name = "rue", length = 70)
	private String rue;
	
	@Column(name = "codePostal", length = 70)
	private String codePostal;

	@Column(name = "ville", length = 50)
	private String ville;

	@Column(name = "pays", length = 30)
	private String pays;
	

}
