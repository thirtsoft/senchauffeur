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
@Table(name = "locationvehicule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationVehicule implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "reference", length = 50)
	private String reference;

	@Column(name = "firstName", length = 90)
	private String firstName;

	@Column(name = "lastName", length = 70)
	private String lastName;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "phoneChauffeur", length = 30)
	private String phoneChauffeur;
	
	@Column(name = "cvChauffeur", length = 30)
	private String cvChauffeur;
	
	@Column(name = "photoChauffeur", length = 30)
	private String photoChauffeur;

}
