package com.chauffeur.models;

import java.util.Date;

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
@Table(name = "typeAnnonce")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeAnnonce {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;
    
    @Column(name = "createdDate")
    private Date createdDate;
    
}
