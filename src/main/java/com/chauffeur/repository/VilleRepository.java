package com.chauffeur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Tarif;
import com.chauffeur.models.Ville;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {
	
	List<Ville> findVilleByOrderByIdDesc();

}
