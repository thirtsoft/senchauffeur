package com.chauffeur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Addresse;

@Repository
public interface AddresseRepository extends JpaRepository<Addresse, Long> {

}
