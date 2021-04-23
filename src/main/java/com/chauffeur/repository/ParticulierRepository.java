package com.chauffeur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Particulier;

@Repository
public interface ParticulierRepository extends JpaRepository<Particulier, Long> {

}
