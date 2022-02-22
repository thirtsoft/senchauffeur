package com.chauffeur.services;

import java.util.List;

import com.chauffeur.dto.AddresseDto;

public interface AddressService {
	
	AddresseDto save(AddresseDto addresseDto);
	
	AddresseDto update(Long idAddress, AddresseDto addresseDto);

	AddresseDto findById(Long id);

    List<AddresseDto> findAll();
    
    List<AddresseDto> findByAddresseByIdDesc();

    void delete(Long id);

}
