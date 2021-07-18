package com.chauffeur.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Recruteur;

@Repository
public interface RecruteurRepository extends JpaRepository<Recruteur, Long> {
	
	@Query("select count(p) from Recruteur p ")
	BigDecimal countNumberOfRecruteurs();

}
