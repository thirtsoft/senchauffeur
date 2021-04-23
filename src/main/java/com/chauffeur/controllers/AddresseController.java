package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.AddresseApi;
import com.chauffeur.dto.AddresseDto;
import com.chauffeur.services.AddressService;

@RestController
public class AddresseController implements AddresseApi {
	
	private AddressService addressService;
	
	@Autowired
	public AddresseController(AddressService addressService) {
		this.addressService = addressService;
	}

	@Override
	public ResponseEntity<AddresseDto> save(AddresseDto addresseDto) {
		return ResponseEntity.ok(addressService.save(addresseDto));
	}

	@Override
	public ResponseEntity<AddresseDto> findById(Long id) {
		return ResponseEntity.ok(addressService.findById(id));
	}

	@Override
	public List<AddresseDto> findAll() {
		return addressService.findAll();
	}

	@Override
	public void delete(Long id) {
		addressService.delete(id);
		
	}

}
