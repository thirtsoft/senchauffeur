package com.chauffeur.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.HistoriqueAnnonce;


@Repository
public interface HistoriqueAnnonceRepository extends JpaRepository<HistoriqueAnnonce, Long> {
	
	@Query("select count(p) from HistoriqueAnnonce p ")
	BigDecimal countNumberOfHistoriqueAnnonces();
	
	List<HistoriqueAnnonce> findHistoriqueAnnonceByOrderByIdDesc();

}
