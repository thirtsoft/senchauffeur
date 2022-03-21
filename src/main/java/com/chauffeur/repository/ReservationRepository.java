package com.chauffeur.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	List<Reservation> findReservationByOrderByIdDesc();
	    
	@Query("select count(c) from Reservation c where year(c.createdDate) = year(current_date)")
	BigDecimal countNumberOfReservationsInYear();

	@Query("select count(c) from Reservation c where c.status = 'ENCOURS' ")
	BigDecimal countNumberOfReservationByStatusPending();

	@Query("select c from Reservation c where c.status = 'ENCOURS' order by id Desc ")
    List<Reservation> findListReservationByStatusPending();

    @Query("select c from Reservation c where c.status = 'VALIDEE' order by id Desc ")
    List<Reservation> findListReservationByStatusValidated();
  
	@Query("select p from Reservation p where p.utilisateur.id =:user order by id Desc")
	List<Reservation> FindListReservationByCustomerId(@Param("user") Long userId);
	
	@Query("select p from Reservation p where p.chauffeur.id =:chauff order by id Desc")
	List<Reservation> FindListReservationByChauffeurId(@Param("chauff") Long chauffId);
	
	@Query("select EXTRACT(month from(c.createdDate)), count(c) from Reservation c group by EXTRACT(month from(c.createdDate))")
    List<?> countNumberOfReservationsPeerMonth();
    
    @Query("select EXTRACT(year from(c.createdDate)), count(c) from Reservation c group by EXTRACT(year from(c.createdDate))")
    List<?> countNumberOfReservationsPeerYear();

}
