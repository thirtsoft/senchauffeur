package com.chauffeur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.LocationVehicule;

@Repository
public interface LocationVehiculeRepository extends JpaRepository<LocationVehicule, Long> {

}
