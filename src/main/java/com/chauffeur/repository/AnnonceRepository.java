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

import com.chauffeur.models.Annonce;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
	
	Optional<Annonce> findAnnonceByReference(String reference);
	
	@Query("select count(p) from Annonce p ")
	BigDecimal countNumberOfAnnonces();
		
    @Query("select count(c) from Annonce c where month(c.dateCandidature) = month(current_date)")
    BigDecimal countNumberOfAnnoncesInMonth();
    
    @Query("select count(c) from Annonce c where year(c.dateCandidature) = year(current_date)")
    BigDecimal countNumberOfAnnoncesInYear();

    @Query("select count(c) from Annonce c where c.status = 'ENCOURS' ")
    BigDecimal countNumberOfAnnonceByStatusPending();

    @Query("select p from Annonce p where p.utilisateur.id =:user")
	Annonce FindAnnonceByCustomerId(@Param("user") Long userId);
    
	List<Annonce> findByOrderByIdDesc();
	
	@Query("select art from Annonce art where art.selected = true")
    List<Annonce> findAnnonceBySelected();
	 
	@Query("select art from Annonce art where art.reference like :x")
	List<Annonce> findAnnonceByKeyword(@Param("x") String mc);
	 
	@Query("select art from Annonce art where art.libelle like :y")
	List<Annonce> findListAnnonceByLibelle(@Param("y") String libelle);
	 
	@Query("select p from Annonce p where p.permis.id =:pId")
	List<Annonce> findAnnonceByPermis(@Param("pId") Long permisId);
	
	List<Annonce> findTop5ByOrderByIdDesc();
	
	@Query("select c from Annonce c where c.status = 'VALIDEE' order by id Desc")
	List<Annonce> findTop6ValidatedByOrderByIdDesc();
	
	@Query("select c from Annonce c where c.status = 'ENCOURS' order by id Desc ")
    List<Annonce> findListAnnonceByStatusPending();

    @Query("select c from Annonce c where c.status = 'VALIDEE' order by id Desc ")
    List<Annonce> findListAnnonceByStatusValidated();
    
    @Query("select c from Annonce c where c.status = 'REJETEE' order by id Desc ")
    List<Annonce> findListAnnonceByStatusRejected();

	@Query("select p from Annonce p where p.utilisateur.id =:user order by id Desc")
	List<Annonce> FindListAnnonceByCustomerId(@Param("user") Long userId);
	    
    @Query("select EXTRACT(month from(c.dateCandidature)), count(c) from Annonce c group by EXTRACT(month from(c.dateCandidature))")
    List<?> countNumberOfAnnonceByMonth();
    
    @Query("select EXTRACT(year from(c.dateCandidature)), count(c) from Annonce c group by EXTRACT(year from(c.dateCandidature))")
    List<?> countNumberOfAnnonceByYear();
	 
	@Query("select p from Annonce p")
	Page<Annonce> findAnnonce(Pageable pageable);
	 
	@Query("select annonce from Annonce annonce where annonce.permis.id =:permId")
	Page<Annonce> findAnnonceByPermisPageables(@Param("permId") Long permisId, Pageable pageable);
	
	Page<Annonce> findByPermisId(Long id, Pageable pageable);
	
	// Like  key%  %key  %key%
    Page<Annonce> findByLibelleContaining(String libelle, Pageable pageable);

    @Query("select count(id) from Annonce where permis.id = ?1")
    long getAnnonceLengthByPermisId(long id);

    @Query("select count(id) from Annonce where libelle LIKE %?1%")
    long getAnnonceSizeByKey(String libelle);
				
		

}
