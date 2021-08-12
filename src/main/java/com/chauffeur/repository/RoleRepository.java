package com.chauffeur.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chauffeur.enumeration.RoleName;
import com.chauffeur.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	 Optional<Role> findByName(RoleName roleName);

}
