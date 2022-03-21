package com.chauffeur.services;

import java.math.BigDecimal;
import java.util.List;

import com.chauffeur.dto.ReservationDto;

public interface ReservationService {
	
	ReservationDto save(ReservationDto reservationDto);
	
	ReservationDto update(Long idReservation, ReservationDto reservationDto);
	
	ReservationDto updateStatusOfReservation(String status, String id);

	ReservationDto findById(Long id);

    BigDecimal countNumberOfReservationsInYear();

	BigDecimal countNumberOfReservationByStatusPending();

	List<ReservationDto> findAll();
    
    List<ReservationDto> findByTypeReservationByIdDesc();
	
    List<ReservationDto> findListReservationByStatusPending();

    List<ReservationDto> findListReservationByStatusValidated();
  
	List<ReservationDto> FindListReservationByCustomerId(Long userId);
	
	List<ReservationDto> FindListReservationByChauffeurId(Long chauffId);
	
    List<?> countNumberOfReservationsPeerMonth();
    
    List<?> countNumberOfReservationsPeerYear();

    void delete(Long id);

}
