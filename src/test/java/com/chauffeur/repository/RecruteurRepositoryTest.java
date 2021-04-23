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

import com.chauffeur.dto.RecruteurDto;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecruteurRepositoryTest {
	
	@Autowired
    private RecruteurRepository recruteurRepository;

    @Test
    @Rollback(false)
    public void testCreateRecruteur() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	RecruteurDto recruteurDto = new RecruteurDto();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    
    	RecruteurDto recruteurDtoResult = RecruteurDto.fromEntityToDto(
                recruteurRepository.save(
                		RecruteurDto.fromDtoToEntity(recruteurDto)
                )
        );

        assertNotNull(recruteurDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateRecruteur() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	RecruteurDto recruteurDto = new RecruteurDto();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	RecruteurDto.fromEntityToDto(
                recruteurRepository.save(
                		RecruteurDto.fromDtoToEntity(recruteurDto)
                )
        );
    	
        String firstNameRecruteur = "Poids Leger";
        recruteurDto.setFirstName(firstNameRecruteur);
        recruteurDto.setId((long) 1);
        
        RecruteurDto.fromEntityToDto(recruteurRepository.save(RecruteurDto.fromDtoToEntity(recruteurDto)));

        assertThat(recruteurDto.getFirstName()).isEqualTo(firstNameRecruteur);

    }

    @Test
    public void testFindById() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	RecruteurDto recruteurDto = new RecruteurDto();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	RecruteurDto recruteurDtoResult = RecruteurDto.fromEntityToDto(
                recruteurRepository.save(
                		RecruteurDto.fromDtoToEntity(recruteurDto)
                )
        );
        
    	 boolean isExistRecruteur = recruteurRepository.findById(recruteurDtoResult.getId()).isPresent();

         assertTrue(isExistRecruteur);

        
    }

   
    @Test
    public void testFindAll() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	RecruteurDto recruteurDto = new RecruteurDto();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	RecruteurDto.fromEntityToDto(
                recruteurRepository.save(
                		RecruteurDto.fromDtoToEntity(recruteurDto)
                )
        );
    	
    	String firstName2 = "Tairou"; String lastName2 = "Diallo";
    	RecruteurDto recruteurDto2 = new RecruteurDto();
    	recruteurDto2.setFirstName(firstName2); recruteurDto2.setLastName(lastName2);
    	
    	RecruteurDto.fromEntityToDto(
                recruteurRepository.save(
                		RecruteurDto.fromDtoToEntity(recruteurDto2)
                )
        );

        List<?> recruteurs = recruteurRepository.findAll();

        assertThat(recruteurs).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	RecruteurDto recruteurDto = new RecruteurDto();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	
    	RecruteurDto recruteurDtoResult = RecruteurDto.fromEntityToDto(
                recruteurRepository.save(
                		RecruteurDto.fromDtoToEntity(recruteurDto)
                )
        );
        boolean isExistBeforeDelete = recruteurRepository.findById(recruteurDtoResult.getId()).isPresent();

        recruteurRepository.deleteById(recruteurDtoResult.getId());

        boolean notExistAfterDelete = recruteurRepository.findById(recruteurDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }


}
