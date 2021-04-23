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

import com.chauffeur.dto.UtilisateurDto;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UtilisateurRepositoryTest {
	
	@Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @Rollback(false)
    public void testCreateUtilisateur() {
    	String name = "Tairou"; String lastName = "username";
        String email = "thirdiallo@gmail.com"; String password = "passer1234";

    	UtilisateurDto utilisateurDto = new UtilisateurDto();
    	utilisateurDto.setName(name); utilisateurDto.setUsername(lastName);
    	utilisateurDto.setEmail(email); utilisateurDto.setPassword(password);
    
    	UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                		UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );

        assertNotNull(utilisateurDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateUtilisateur() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	UtilisateurDto utilisateurDto = new UtilisateurDto();
    	utilisateurDto.setName(firstName); utilisateurDto.setUsername(lastName);
    	
    	UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                		UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );
    	
        String firstNameRecruteur = "Poids Leger";
        utilisateurDto.setName(firstNameRecruteur);
        utilisateurDto.setId((long) 1);
        
        UtilisateurDto.fromEntityToDto(utilisateurRepository.save(UtilisateurDto.fromDtoToEntity(utilisateurDto)));

        assertThat(utilisateurDto.getName()).isEqualTo(firstNameRecruteur);

    }

    @Test
    public void testFindById() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	UtilisateurDto utilisateurDto = new UtilisateurDto();
    	utilisateurDto.setName(firstName); utilisateurDto.setUsername(lastName);
    	
    	UtilisateurDto UtilisateurDtoResult = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                		UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );
        
    	 boolean isExistRecruteur = utilisateurRepository.findById(UtilisateurDtoResult.getId()).isPresent();

         assertTrue(isExistRecruteur);
   
    }
   
    @Test
    public void testFindAll() {
    	String firstName = "Tairou"; String lastName = "Diallo";
    	UtilisateurDto utilisateurDto = new UtilisateurDto();
    	utilisateurDto.setName(firstName); utilisateurDto.setUsername(lastName);
    	
    	UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                		UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );
    	
    	String firstName2 = "Tairou"; String lastName2 = "Diallo";
    	UtilisateurDto utilisateurDto2 = new UtilisateurDto();
    	utilisateurDto2.setName(firstName2); utilisateurDto2.setUsername(lastName2);
    	
    	UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                		UtilisateurDto.fromDtoToEntity(utilisateurDto2)
                )
        );

        List<?> utilisateurs = utilisateurRepository.findAll();

        assertThat(utilisateurs).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
    	String name = "Tairou"; String username = "Diallo";
    	UtilisateurDto utilisateurDto = new UtilisateurDto();
    	utilisateurDto.setName(name); utilisateurDto.setUsername(username);
    	
    	UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                		UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );
        boolean isExistBeforeDelete = utilisateurRepository.findById(utilisateurDtoResult.getId()).isPresent();

        utilisateurRepository.deleteById(utilisateurDtoResult.getId());

        boolean notExistAfterDelete = utilisateurRepository.findById(utilisateurDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }


}
