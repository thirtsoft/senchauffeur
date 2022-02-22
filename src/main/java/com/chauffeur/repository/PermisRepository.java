package com.chauffeur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.HistoriqueAnnonce;
import com.chauffeur.models.Permis;

@Repository
public interface PermisRepository extends JpaRepository<Permis, Long> {
	
	List<Permis> findPermisByOrderByIdDesc();

}
