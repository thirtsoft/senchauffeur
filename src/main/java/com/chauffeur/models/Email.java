package com.chauffeur.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "email")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message")
    @Lob
    private String message;

    @Column(name = "createDate")
    private Date createDate;

    @ManyToOne
    @Column(name = "userId")
    private Utilisateur utilisateur;
    
    @ManyToOne
    @Column(name = "chauffId")
    private Chauffeur chauffeur;

    @ManyToOne
    @Column(name = "visitorId")
    private Newsleter newsleter;

   
}
