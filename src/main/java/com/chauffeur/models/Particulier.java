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
@Table(name = "particulier")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Particulier implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "firstName", length = 90)
	private String firstName;

	@Column(name = "lastName", length = 70)
	private String lastName;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "phoneParticulier", length = 30)
	private String phoneParticulier;
	
	@Column(name = "mobileParticulier", length = 30)
	private String mobileParticulier;
	
	@Column(name = "addressParticulier", length = 90)
	private String addressParticulier;
	
	@Column(name = "villeParticulier", length = 90)
	private String villeParticulier;

}
