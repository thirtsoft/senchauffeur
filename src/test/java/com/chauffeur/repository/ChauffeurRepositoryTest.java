package com.chauffeur.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.chauffeur.models.Chauffeur;
import com.chauffeur.models.Permis;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChauffeurRepositoryTest {
	
	@Autowired
    private ChauffeurRepository chauffeurRepository;
	
	@Autowired
    private PermisRepository permisRepository;
	
    @Test
    @Rollback(false)
    public void testCreateChauffeur() {
    	Long catId = (long) 1;
        Permis permis = permisRepository.findById(catId).orElse(null);
    
    	String reference = "CH1"; String firstName = "Tairou"; String lastName = "Diallo";
    	String sexe = "M"; String addressActuel = "ADD1"; String mobilite = "Dk - Thies - Kaolack";
    	Chauffeur chauffeurDto = new Chauffeur();
    	chauffeurDto.setReference(reference); chauffeurDto.setFirstName(firstName);
    	chauffeurDto.setLastName(lastName); chauffeurDto.setSexe(sexe);
    	chauffeurDto.setMobilite(mobilite);
    	chauffeurDto.setPermis(permis);
    	
    	Chauffeur chauffeurDtoResult = chauffeurRepository.save(chauffeurDto);
    	
     
        assertNotNull(chauffeurDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateChauffeur() {
    	Long catId = (long) 1;
        Permis permis = permisRepository.findById(catId).orElse(null);
    
    	String reference = "CH1"; String firstName = "Tairou"; String lastName = "Diallo";
    	String sexe = "M"; String mobilite = "Dk - Thies - Kaolack";
    	Chauffeur chauffeurDto = new Chauffeur();
    	chauffeurDto.setReference(reference); chauffeurDto.setFirstName(firstName);
    	chauffeurDto.setLastName(lastName); chauffeurDto.setSexe(sexe);
    	chauffeurDto.setMobilite(mobilite);
    	chauffeurDto.setPermis(permis);
    	
    	chauffeurRepository.save(chauffeurDto);
    
        String firstNameChauffeur = "DialloDiallo";
        chauffeurDto.setFirstName(firstNameChauffeur);
        chauffeurDto.setId(2L);
        
        Chauffeur chauffeurUpdate = chauffeurRepository.save(chauffeurDto);
      
        assertThat(chauffeurUpdate.getFirstName()).isEqualTo(firstNameChauffeur);

    }

    @Test
    public void testFindById() {
    	Long catId = (long) 1;
        Permis permis = permisRepository.findById(catId).orElse(null);
    
    	String reference = "CH1"; String firstName = "Tairou"; String lastName = "Diallo";
    	String sexe = "M"; String mobilite = "Dk - Thies - Kaolack";
    	Chauffeur chauffeurDto = new Chauffeur();
    	chauffeurDto.setReference(reference); chauffeurDto.setFirstName(firstName);
    	chauffeurDto.setLastName(lastName); chauffeurDto.setSexe(sexe);
    	chauffeurDto.setMobilite(mobilite);
    	chauffeurDto.setPermis(permis);
    	
    	Chauffeur chauffeurDtoResult = chauffeurRepository.save(chauffeurDto);
    	
    	boolean isExistChauffeur = chauffeurRepository.findById(chauffeurDtoResult.getId()).isPresent();
    	
    	assertTrue(isExistChauffeur);
    }

    @Test
    public void testFindAll() {
    	Long catId = (long) 1;
        Permis permis = permisRepository.findById(catId).orElse(null);
    
    	String reference = "CH1"; String firstName = "Tairou"; String lastName = "Diallo";
    	String sexe = "M"; String mobilite = "Dk - Thies - Kaolack";
    	Chauffeur chauffeurDto = new Chauffeur();
    	chauffeurDto.setReference(reference); chauffeurDto.setFirstName(firstName);
    	chauffeurDto.setLastName(lastName); chauffeurDto.setSexe(sexe);
    	chauffeurDto.setMobilite(mobilite);
    	chauffeurDto.setPermis(permis);
    	
    	chauffeurRepository.save(chauffeurDto);
    	
  
    	String reference2 = "CH1"; String firstName2 = "Tairou"; String lastName2 = "Diallo";
    	Chauffeur chauffeurDto2 = new Chauffeur();
    	chauffeurDto2.setReference(reference2); chauffeurDto2.setFirstName(firstName2);
    	chauffeurDto2.setLastName(lastName2); chauffeurDto.setSexe(sexe);
    	chauffeurDto.setPermis(permis);
    	chauffeurRepository.save(chauffeurDto2);
    	
        List<Chauffeur> chauffeurs = chauffeurRepository.findAll();

        assertThat(chauffeurs).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
    	Long catId = (long) 1;
    	Permis permis = permisRepository.findById(catId).orElse(null);
        
    	String reference = "CH1"; String firstName = "Tairou"; String lastName = "Diallo";
    	String sexe = "M"; String mobilite = "Dk - Thies - Kaolack";
    	Chauffeur chauffeurDto = new Chauffeur();
    	chauffeurDto.setReference(reference); chauffeurDto.setFirstName(firstName);
    	chauffeurDto.setLastName(lastName); chauffeurDto.setSexe(sexe);
    	chauffeurDto.setMobilite(mobilite);
    	chauffeurDto.setPermis(permis);
    	
    	Chauffeur chauffeurDtoResult = chauffeurRepository.save(chauffeurDto);
    	
        boolean isExistBeforeDelete = chauffeurRepository.findById(chauffeurDtoResult.getId()).isPresent();

        chauffeurRepository.deleteById(chauffeurDtoResult.getId());

        boolean notExistAfterDelete = chauffeurRepository.findById(chauffeurDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
