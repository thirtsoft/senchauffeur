package com.chauffeur.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Annonce;
import com.chauffeur.models.Jeton;

@Repository
public interface JetonRepository extends JpaRepository<Jeton, Long> {
	
	@Query("select count(p) from Jeton p ")
	BigDecimal countNumberOfJetons();
	
	List<Jeton> findListOfJetonByOrderByIdDesc();
	
	@Query("select p from Jeton p where p.utilisateur.id =:user order by id Desc")
	List<Jeton> FindListJetonByCustomerId(@Param("user") Long userId);


}
