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
import com.chauffeur.models.Recruteur;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecruteurRepositoryTest {
	
	@Autowired
    private RecruteurRepository recruteurRepository;

    @Test
    @Rollback(false)
    public void testCreateRecruteur() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	Recruteur recruteurDto = new Recruteur();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	Recruteur recruteurDtoResult = recruteurRepository.save(recruteurDto);
    
        assertNotNull(recruteurDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateRecruteur() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	Recruteur recruteurDto = new Recruteur();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	recruteurRepository.save(recruteurDto);
    	  	
        String firstNameRecruteur = "Poids Leger";
        recruteurDto.setId(3L);
        recruteurDto.setFirstName(firstNameRecruteur);
        
        Recruteur recruteurUpdate = recruteurRepository.save(recruteurDto);
  
        assertThat(recruteurUpdate.getFirstName()).isEqualTo(firstNameRecruteur);

    }

    @Test
    public void testFindById() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	Recruteur recruteurDto = new Recruteur();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	Recruteur recruteurDtoResult = recruteurRepository.save(recruteurDto);
    	
    	
    	 boolean isExistRecruteur = recruteurRepository.findById(recruteurDtoResult.getId()).isPresent();

         assertTrue(isExistRecruteur);

        
    }

   
    @Test
    public void testFindAll() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	Recruteur recruteurDto = new Recruteur();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	recruteurRepository.save(recruteurDto);
    	
    	String firstName2 = "Tairou"; String lastName2 = "Diallo";
    	Recruteur recruteurDto2 = new Recruteur();
    	recruteurDto.setFirstName(firstName2); recruteurDto.setLastName(lastName2);
    	
    	recruteurRepository.save(recruteurDto2);
    	
 
        List<Recruteur> recruteurs = recruteurRepository.findAll();

        assertThat(recruteurs).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	Recruteur recruteurDto = new Recruteur();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	Recruteur recruteurDtoResult = recruteurRepository.save(recruteurDto);
    
        boolean isExistBeforeDelete = recruteurRepository.findById(recruteurDtoResult.getId()).isPresent();

        recruteurRepository.deleteById(recruteurDtoResult.getId());

        boolean notExistAfterDelete = recruteurRepository.findById(recruteurDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }


}
