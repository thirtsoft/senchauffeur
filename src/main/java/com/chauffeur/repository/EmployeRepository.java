package com.chauffeur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Employe;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

}
