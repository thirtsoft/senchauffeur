package com.chauffeur.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.chauffeur.dto.ParticulierDto;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParticulierRepositoryTest {
	
	@Autowired
    private ParticulierRepository particulierRepository;

    @Test
    @Rollback(false)
    public void testCreateParticulier() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	String phoneParticulier = "56789087"; String addressParticulier = "P1";
    	ParticulierDto particulierDto = new ParticulierDto();
    	particulierDto.setFirstName(firstName); particulierDto.setLastName(lastName);
    	particulierDto.setAddressParticulier(addressParticulier); 
    	particulierDto.setPhoneParticulier(phoneParticulier);
    	
    	ParticulierDto particulierDtoResult = ParticulierDto.fromEntityToDto(
                particulierRepository.save(
                		ParticulierDto.fromDtoToEntity(particulierDto)
                )
        );

        assertNotNull(particulierDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateParticulier() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	String phoneParticulier = "56789087"; String addressParticulier = "P1";
    	ParticulierDto particulierDto = new ParticulierDto();
    	particulierDto.setFirstName(firstName); particulierDto.setLastName(lastName);
    	particulierDto.setAddressParticulier(addressParticulier); 
    	particulierDto.setPhoneParticulier(phoneParticulier);
    	
    	ParticulierDto.fromEntityToDto(
                particulierRepository.save(
                		ParticulierDto.fromDtoToEntity(particulierDto)
                )
        );
    	
        String firstNameParticulier = "Poids Leger";
        particulierDto.setFirstName(firstNameParticulier);
        particulierDto.setId((long) 1);
        
        ParticulierDto.fromEntityToDto(particulierRepository.save(ParticulierDto.fromDtoToEntity(particulierDto)));

        assertThat(particulierDto.getFirstName()).isEqualTo(firstNameParticulier);

    }

    @Test
    public void testFindById() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	String phoneParticulier = "56789087"; String addressParticulier = "P1";
    	ParticulierDto particulierDto = new ParticulierDto();
    	particulierDto.setFirstName(firstName); particulierDto.setLastName(lastName);
    	particulierDto.setAddressParticulier(addressParticulier); 
    	particulierDto.setPhoneParticulier(phoneParticulier);
    	
    	ParticulierDto particulierDtoResult = ParticulierDto.fromEntityToDto(
                particulierRepository.save(
                		ParticulierDto.fromDtoToEntity(particulierDto)
                )
        );
        
    	 boolean isExistParticulier = particulierRepository.findById(particulierDtoResult.getId()).isPresent();

         assertTrue(isExistParticulier);

        
    }

   
    @Test
    public void testFindAll() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	String phoneParticulier = "56789087"; String addressParticulier = "P1";
    	ParticulierDto particulierDto = new ParticulierDto();
    	particulierDto.setFirstName(firstName); particulierDto.setLastName(lastName);
    	particulierDto.setAddressParticulier(addressParticulier); 
    	particulierDto.setPhoneParticulier(phoneParticulier);
    	
    	ParticulierDto.fromEntityToDto(
                particulierRepository.save(
                		ParticulierDto.fromDtoToEntity(particulierDto)
                )
        );
    	
    	String firstName2 = "Tairou"; String lastName2 = "Diallo";
    	ParticulierDto particulierDto2 = new ParticulierDto();
    	particulierDto2.setFirstName(firstName2); particulierDto2.setLastName(lastName2);
    	
    	ParticulierDto.fromEntityToDto(
                particulierRepository.save(
                		ParticulierDto.fromDtoToEntity(particulierDto)
                )
        );

        List<?> particuliers = particulierRepository.findAll();

        assertThat(particuliers).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	String phoneParticulier = "56789087"; String addressParticulier = "P1";
    	ParticulierDto particulierDto = new ParticulierDto();
    	particulierDto.setFirstName(firstName); particulierDto.setLastName(lastName);
    	particulierDto.setAddressParticulier(addressParticulier); 
    	particulierDto.setPhoneParticulier(phoneParticulier);
    	
    	ParticulierDto particulierDtoResult = ParticulierDto.fromEntityToDto(
                particulierRepository.save(
                		ParticulierDto.fromDtoToEntity(particulierDto)
                )
        );
        boolean isExistBeforeDelete = particulierRepository.findById(particulierDtoResult.getId()).isPresent();

        particulierRepository.deleteById(particulierDtoResult.getId());

        boolean notExistAfterDelete = particulierRepository.findById(particulierDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
