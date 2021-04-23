package com.chauffeur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Recruteur;

@Repository
public interface RecruteurRepository extends JpaRepository<Recruteur, Long> {

}
