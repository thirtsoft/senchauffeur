package com.chauffeur.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.ReservationDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Reservation;
import com.chauffeur.repository.ReservationRepository;
import com.chauffeur.services.ReservationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private final ReservationRepository reservationRepository;

	public ReservationServiceImpl(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Override
	public ReservationDto save(ReservationDto reservationDto) {
		
		reservationDto.setStatus("ENCOURS");
		
		return ReservationDto.fromEntityToDto(
				reservationRepository.save(
						ReservationDto.fromDtoToEntity(reservationDto)
				)
					
		);
	}

	@Override
	public ReservationDto update(Long idReservation, ReservationDto reservationDto) {
		if (!reservationRepository.existsById(idReservation)) {
			throw new ResourceNotFoundException("Reservation not found");
		}
		
		Optional<Reservation> reservationOptional = reservationRepository.findById(idReservation);
		
		if (!reservationOptional.isPresent()) {
	            throw new ResourceNotFoundException("Reservation not found");
	    }
		
		ReservationDto reservationDtoResult = ReservationDto.fromEntityToDto(reservationOptional.get());
		reservationDtoResult.setDescription(reservationDto.getDescription());
		reservationDtoResult.setStatus(reservationDto.getStatus());
		reservationDtoResult.setCreatedDate(reservationDto.getCreatedDate());
		reservationDtoResult.setDateDemarrage(reservationDto.getDateDemarrage());
		reservationDtoResult.setChauffeurDto(reservationDto.getChauffeurDto());
		reservationDtoResult.setUtilisateurDto(reservationDto.getUtilisateurDto());
		
		return ReservationDto.fromEntityToDto(
				reservationRepository.save(
						ReservationDto.fromDtoToEntity(reservationDtoResult)
				)
					
		);
		
	}
	
	@Override
	public ReservationDto updateStatusOfReservation(String status, String id) {
		
		Optional<Reservation> reservationOptional = reservationRepository.findById(Long.valueOf(id));

		ReservationDto reservationDtoResult = ReservationDto.fromEntityToDto(reservationOptional.get());

		reservationDtoResult.setStatus(status);

        return ReservationDto.fromEntityToDto(
        		reservationRepository.save(
        				ReservationDto.fromDtoToEntity(reservationDtoResult)
                )
        );
	}

	@Override
	public ReservationDto findById(Long id) {
		if (id == null) {
            log.error("Reservation Id is null");
            return null;
        }

		Optional<Reservation> reservationOptional = reservationRepository.findById(id);

        return Optional.of(ReservationDto.fromEntityToDto(reservationOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Reservation avec l'Id = " + id + "n'a été trouvé")
        );
	}

	@Override
	public BigDecimal countNumberOfReservationsInYear() {
		return reservationRepository.countNumberOfReservationsInYear();
	}

	@Override
	public BigDecimal countNumberOfReservationByStatusPending() {
		return reservationRepository.countNumberOfReservationByStatusPending();
	}
	
	@Override
	public List<ReservationDto> findAll() {
		return reservationRepository.findAll().stream()
				.map(ReservationDto::fromEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<ReservationDto> findByTypeReservationByIdDesc() {
		return reservationRepository.findReservationByOrderByIdDesc().stream()
				.map(ReservationDto::fromEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<ReservationDto> findListReservationByStatusPending() {
		return reservationRepository.findListReservationByStatusPending().stream()
				.map(ReservationDto::fromEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<ReservationDto> findListReservationByStatusValidated() {
		return reservationRepository.findListReservationByStatusValidated().stream()
				.map(ReservationDto::fromEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<ReservationDto> FindListReservationByCustomerId(Long userId) {
		return reservationRepository.FindListReservationByCustomerId(userId).stream()
				.map(ReservationDto::fromEntityToDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<ReservationDto> FindListReservationByChauffeurId(Long chauffId) {
		return reservationRepository.FindListReservationByChauffeurId(chauffId).stream()
				.map(ReservationDto::fromEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<?> countNumberOfReservationsPeerMonth() {
		return reservationRepository.countNumberOfReservationsPeerMonth();
	}

	@Override
	public List<?> countNumberOfReservationsPeerYear() {
		return reservationRepository.countNumberOfReservationsPeerYear();
	}
	
	@Override
	public void delete(Long id) {
		if (id == null) {
            log.error("Reservation Id is null");
            return;
        }
		reservationRepository.deleteById(id);
		
	}


}
