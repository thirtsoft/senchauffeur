package com.chauffeur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Permis;

@Repository
public interface PermisRepository extends JpaRepository<Permis, Long> {

}