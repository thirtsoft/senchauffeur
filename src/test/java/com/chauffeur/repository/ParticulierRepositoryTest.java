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
import com.chauffeur.models.Particulier;


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
    	Particulier particulierDto = new Particulier();
    	particulierDto.setFirstName(firstName); particulierDto.setLastName(lastName);
    	particulierDto.setAddressParticulier(addressParticulier); 
    	particulierDto.setPhoneParticulier(phoneParticulier);
    	
    	Particulier particulierDtoResult = particulierRepository.save(particulierDto);

        assertNotNull(particulierDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateParticulier() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	String phoneParticulier = "56789087"; String addressParticulier = "P1";
    	Particulier particulierDto = new Particulier();
    	particulierDto.setFirstName(firstName); particulierDto.setLastName(lastName);
    	particulierDto.setAddressParticulier(addressParticulier); 
    	particulierDto.setPhoneParticulier(phoneParticulier);
    	
    	particulierRepository.save(particulierDto);

    	
        String firstNameParticulier = "Poids Leger";
        particulierDto.setFirstName(firstNameParticulier);
        particulierDto.setId(3L);
        Particulier particulierUpdate  = particulierRepository.save(particulierDto);
        
        assertThat(particulierUpdate.getFirstName()).isEqualTo(firstNameParticulier);

    }

    @Test
    public void testFindById() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	String phoneParticulier = "56789087"; String addressParticulier = "P1";
    	Particulier particulierDto = new Particulier();
    	particulierDto.setFirstName(firstName); particulierDto.setLastName(lastName);
    	particulierDto.setAddressParticulier(addressParticulier); 
    	particulierDto.setPhoneParticulier(phoneParticulier);
    	
    	Particulier particulierDtoResult = particulierRepository.save(particulierDto);
    	
        
    	 boolean isExistParticulier = particulierRepository.findById(particulierDtoResult.getId()).isPresent();

         assertTrue(isExistParticulier);

        
    }

   
    @Test
    public void testFindAll() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	String phoneParticulier = "56789087"; String addressParticulier = "P1";
    	Particulier particulierDto = new Particulier();
    	particulierDto.setFirstName(firstName); particulierDto.setLastName(lastName);
    	particulierDto.setAddressParticulier(addressParticulier); 
    	particulierDto.setPhoneParticulier(phoneParticulier);
    	
    	particulierRepository.save(particulierDto);
    	
    	String firstName2 = "Tairou"; String lastName2 = "Diallo";
    	Particulier particulierDto2 = new Particulier();
    	particulierDto2.setFirstName(firstName2); particulierDto2.setLastName(lastName2);
    	
    	particulierRepository.save(particulierDto2);
    	
    
        List<Particulier> particuliers = particulierRepository.findAll();

        assertThat(particuliers).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	String phoneParticulier = "56789087"; String addressParticulier = "P1";
    	Particulier particulierDto = new Particulier();
    	particulierDto.setFirstName(firstName); particulierDto.setLastName(lastName);
    	particulierDto.setAddressParticulier(addressParticulier); 
    	particulierDto.setPhoneParticulier(phoneParticulier);
    	
    	Particulier particulierDtoResult = particulierRepository.save(particulierDto);
    
        boolean isExistBeforeDelete = particulierRepository.findById(particulierDtoResult.getId()).isPresent();

        particulierRepository.deleteById(particulierDtoResult.getId());

        boolean notExistAfterDelete = particulierRepository.findById(particulierDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
