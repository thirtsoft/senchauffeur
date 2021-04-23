package com.chauffeur.models;

import java.io.Serializable;

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
@Table(name = "notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reference", length = 60)
    private String reference;

    @Column(name = "nbreEtoile", length = 60)
    private String nbreEtoile;

    @Column(name = "observation", length = 200)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "prodId")
    private Chauffeur chauffeur;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Utilisateur utilisateur;
}
