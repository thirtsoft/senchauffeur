package com.chauffeur.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Chauffeur;

@Repository
public interface ChauffeurRepository extends JpaRepository<Chauffeur, Long> {
	
	Optional<Chauffeur> findChauffeurByReference(String reference);
	
	@Query("select count(p) from Chauffeur p ")
	BigDecimal countNumberOfChauffeurs();
	
	@Query("select p from Chauffeur p where p.permis.id =:pId")
	List<Chauffeur> findChauffeurByPermis(@Param("pId") Long permisId);
	 
	@Query("select art from Chauffeur art where art.reference like :x")
	List<Chauffeur> findChauffeurByKeyword(@Param("x") String mc);
	
	@Query("select p from Chauffeur p")
	Page<Chauffeur> findAllChauffeurByPageable(Pageable pageable);
	

}
