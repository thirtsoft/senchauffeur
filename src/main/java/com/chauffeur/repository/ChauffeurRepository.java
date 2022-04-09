package com.chauffeur.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Chauffeur;

@Repository
public interface ChauffeurRepository extends JpaRepository<Chauffeur, Long> {
	
	Optional<Chauffeur> findChauffeurByReference(String reference);
	
	@Query("select count(p) from Chauffeur p ")
	BigDecimal countNumberOfChauffeurs();
	
	List<Chauffeur> findChauffeurByOrderByIdDesc();
	
	@Query("select art from Chauffeur art where art.selected = true")
    List<Chauffeur> findChauffeurBySelected();
	
	@Query("select p from Chauffeur p where p.permis.id =:pId")
	List<Chauffeur> findChauffeurByPermis(@Param("pId") Long permisId);
	 
	@Query("select art from Chauffeur art where art.reference like :x")
	List<Chauffeur> findChauffeurByKeyword(@Param("x") String mc);
	
	@Query("select chauff from Chauffeur chauff where chauff.disponibity like :z")
	List<Chauffeur> findChauffeurByDisponibility(@Param("z") String disponility);
	
	@Query("select EXTRACT(month from(c.dateInscription)), count(c) from Chauffeur c group by EXTRACT(month from(c.dateInscription))")
	List<?> countNumberOfChauffeurByMonth();
	    
	@Query("select EXTRACT(year from(c.dateInscription)), count(c) from Chauffeur c group by EXTRACT(year from(c.dateInscription))")
	List<?> countNumberOfChauffeurByYear();
	
	@Query("select p from Chauffeur p")
	Page<Chauffeur> findAllChauffeurByPageable(Pageable pageable);
	

    @Query("select chauff from Chauffeur chauff where chauff.disponibity like :dispo")
    Page<Chauffeur> findChauffeurByKeywordByPageable(@Param("dispo") String mc, Pageable pageable);


    @Query("select chauff from Chauffeur chauff where chauff.addresse.id =:add")
    Page<Chauffeur> findChauffeurByLocalityPageables(@Param("add") Long addId, Pageable pageable);
    
    @Query("select chauff from Chauffeur chauff where chauff.permis.id =:permId")
    Page<Chauffeur> findChauffeurByPermisPageables(@Param("permId") Long permisId, Pageable pageable);
    
    Page<Chauffeur> findByAddresseId(Long id, Pageable pageable);
    
 // Like  key%  %key  %key%
    Page<Chauffeur> findByReferenceContaining(String reference, Pageable pageable);

    @Query("select count (id) from Chauffeur where addresse.id = ?1")
    long getChauffeurLengthByAddressId(long id);

    @Query("select count (id) from Chauffeur where libelle LIKE %?1%")
    long getChauffeurSizeByKey(String key);
	
	

}
