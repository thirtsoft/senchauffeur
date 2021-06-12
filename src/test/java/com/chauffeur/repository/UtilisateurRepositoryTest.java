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
import com.chauffeur.models.Utilisateur;

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

    	Utilisateur utilisateurDto = new Utilisateur();
    	utilisateurDto.setName(name); utilisateurDto.setUsername(lastName);
    	utilisateurDto.setEmail(email); utilisateurDto.setPassword(password);
    	Utilisateur utilisateurDtoResult = utilisateurRepository.save(utilisateurDto);
    
        assertNotNull(utilisateurDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateUtilisateur() {
    	String name = "Tairou"; String lastName = "username";
        String email = "thirdiallo@gmail.com"; String password = "passer1234";

    	Utilisateur utilisateurDto = new Utilisateur();
    	utilisateurDto.setName(name); utilisateurDto.setUsername(lastName);
    	utilisateurDto.setEmail(email); utilisateurDto.setPassword(password);
    	utilisateurRepository.save(utilisateurDto);
    
        String firstNameRecruteur = "Poids Leger";
        utilisateurDto.setName(firstNameRecruteur);
//        utilisateurDto.setId(2L);
        Utilisateur utilisateurUpdate = utilisateurRepository.save(utilisateurDto);
       
        assertThat(utilisateurUpdate.getName()).isEqualTo(firstNameRecruteur);

    }

    @Test
    public void testFindById() {
    	String name = "Tairou"; String lastName = "username";
        String email = "thirdiallo@gmail.com"; String password = "passer1234";

    	Utilisateur utilisateurDto = new Utilisateur();
    	utilisateurDto.setName(name); utilisateurDto.setUsername(lastName);
    	utilisateurDto.setEmail(email); utilisateurDto.setPassword(password);
    	Utilisateur UtilisateurDtoResult = utilisateurRepository.save(utilisateurDto);
    
    	 boolean isExistUser = utilisateurRepository.findById(UtilisateurDtoResult.getId()).isPresent();

         assertTrue(isExistUser);
   
    }
   
    @Test
    public void testFindAll() {
    	String name = "Tairou"; String lastName = "username";
        String email = "thirdiallo@gmail.com"; String password = "passer1234";

    	Utilisateur utilisateurDto = new Utilisateur();
    	utilisateurDto.setName(name); utilisateurDto.setUsername(lastName);
    	utilisateurDto.setEmail(email); utilisateurDto.setPassword(password);
    	utilisateurRepository.save(utilisateurDto);
    
    	
    	String firstName2 = "Tairou"; String lastName2 = "Diallo";
    	Utilisateur utilisateurDto2 = new Utilisateur();
    	utilisateurDto2.setName(firstName2); utilisateurDto2.setUsername(lastName2);
    	utilisateurRepository.save(utilisateurDto2);
    	
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();

        assertThat(utilisateurs).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
    	String name = "Tairou"; String lastName = "username";
        String email = "thirdiallo@gmail.com"; String password = "passer1234";

    	Utilisateur utilisateurDto = new Utilisateur();
    	utilisateurDto.setName(name); utilisateurDto.setUsername(lastName);
    	utilisateurDto.setEmail(email); utilisateurDto.setPassword(password);
    	Utilisateur utilisateurDtoResult = utilisateurRepository.save(utilisateurDto);
    
        boolean isExistBeforeDelete = utilisateurRepository.findById(utilisateurDtoResult.getId()).isPresent();

        utilisateurRepository.deleteById(utilisateurDtoResult.getId());

        boolean notExistAfterDelete = utilisateurRepository.findById(utilisateurDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }


}
