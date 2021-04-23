package com.chauffeur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Emploi;

@Repository
public interface EmploiRepository extends JpaRepository<Emploi, Long> {

}
