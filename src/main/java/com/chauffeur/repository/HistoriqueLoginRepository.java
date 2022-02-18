package com.chauffeur.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.HistoriqueLogin;

@Repository
public interface HistoriqueLoginRepository extends JpaRepository<HistoriqueLogin, Long> {
	
	@Query("select count(p) from HistoriqueLogin p ")
	BigDecimal countNumberOfHistoriqueLogins();
	
	List<HistoriqueLogin> findHistoriqueLoginByOrderByIdDesc();

}
