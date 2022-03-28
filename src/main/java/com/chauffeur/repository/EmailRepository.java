package com.chauffeur.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
	
	@Query("select count(p) from Email p where month(p.createDate) = month(current_date)")
    BigDecimal countNumberOfEmail();

    List<Email> findByOrderByIdDesc();

}
