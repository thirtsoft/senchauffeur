package com.chauffeur.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.TypeAnnonce;

@Repository
public interface TypeAnnonceRepository extends JpaRepository<TypeAnnonce, Long> {
		
	List<TypeAnnonce> findTypeAnnonceByOrderByIdDesc();
	

}
