package com.chauffeur.models;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createdDate")
    private Date createdDate;
    
    @Column(name = "description")
	@Lob
	private String description;
    
    @Column(name = "status", length = 60)
	private String status;
	
	@Column(name = "dateDemarrage")
	private Date dateDemarrage;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private Utilisateur utilisateur;
    
    @ManyToOne
    @JoinColumn(name = "chauffId")
    private Chauffeur chauffeur;

}
