package com.chauffeur.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Jeton;

@Repository
public interface JetonRepository extends JpaRepository<Jeton, Long> {
	
	@Query("select count(p) from Jeton p ")
	BigDecimal countNumberOfJetons();
	
	List<Jeton> findListOfJetonByOrderByIdDesc();


}
