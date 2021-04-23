package com.chauffeur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Annonce;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

}
