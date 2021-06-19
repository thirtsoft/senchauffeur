package com.chauffeur.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Annonce;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
	
	 @Query("select p from Annonce p")
	 Page<Annonce> findAnnonce(Pageable pageable);
	 
	 @Query("select art from Annonce art where art.reference like :x")
	 List<Annonce> findAnnonceByKeyword(@Param("x") String mc);

}
