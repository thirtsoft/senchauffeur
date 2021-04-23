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
@Table(name = "permis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permis implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "typePermis", length = 50)
	private String typePermis;

	@Column(name = "designation", length = 90)
	private String designation;
	
	@Column(name = "validite", length = 90)
	private int validite;

	

}
