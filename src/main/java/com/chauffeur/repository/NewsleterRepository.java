package com.chauffeur.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Jeton;
import com.chauffeur.models.Newsleter;

@Repository
public interface NewsleterRepository extends JpaRepository<Newsleter, Long> {
	
	@Query("select count(p) from Newsleter p ")
	BigDecimal countNumberOfNewsleters();
	
	List<Newsleter> findListOfNewsletersByOrderByIdDesc();

}
