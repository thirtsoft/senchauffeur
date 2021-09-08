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

import com.chauffeur.models.Annonce;
import com.chauffeur.models.Chauffeur;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
	
	Optional<Annonce> findAnnonceByReference(String reference);
	
	@Query("select count(p) from Annonce p ")
	BigDecimal countNumberOfAnnonces();
	
	@Query("select art from Annonce art where art.selected = true")
    List<Annonce> findAnnonceBySelected();
	 
	@Query("select art from Annonce art where art.reference like :x")
	List<Annonce> findAnnonceByKeyword(@Param("x") String mc);
	 
	@Query("select art from Annonce art where art.libelle like :y")
	List<Annonce> findListAnnonceByLibelle(@Param("y") String libelle);
	 
	@Query("select p from Annonce p where p.permis.id =:pId")
	List<Annonce> findAnnonceByPermis(@Param("pId") Long permisId);
	
	List<Annonce> findTop10ByOrderByIdDesc();
	

//	Page<Vinyl> findTop10ByOrderByVinylIDDescPriceAsc(Pageable pageable);
	 
	@Query("select p from Annonce p")
	Page<Annonce> findAnnonce(Pageable pageable);
	 
	@Query("select annonce from Annonce annonce where annonce.permis.id =:permId")
	Page<Annonce> findAnnonceByPermisPageables(@Param("permId") Long permisId, Pageable pageable);
		
		
		

}
