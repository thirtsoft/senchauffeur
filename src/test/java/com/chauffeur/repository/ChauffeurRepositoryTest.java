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

import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.PermisDto;
import com.chauffeur.models.Chauffeur;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChauffeurRepositoryTest {
	
	@Autowired
    private ChauffeurRepository chauffeurRepository;

    @Test
    @Rollback(false)
    public void testCreateChauffeur() {
    	String typePermis = "A";
    	String designation = "Poids Legers"; int validite = 10;
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	permisDto.setValidite(validite);
    	
    	String reference = "CH1"; String firstName = "Tairou"; String lastName = "Diallo";
    	String sexe = "M"; String addressActuel = "ADD1"; String mobilite = "Dk - Thies - Kaolack";
    	ChauffeurDto chauffeurDto = new ChauffeurDto();
    	chauffeurDto.setReference(reference); chauffeurDto.setFirstName(firstName);
    	chauffeurDto.setLastName(lastName); chauffeurDto.setSexe(sexe);
    	chauffeurDto.setAddressActuel(addressActuel); chauffeurDto.setMobilite(mobilite);
    	chauffeurDto.setPermisDto(permisDto);
    	
     
    	ChauffeurDto chauffeurDtoResult = ChauffeurDto.fromEntityToDto(
                chauffeurRepository.save(
                		ChauffeurDto.fromDtoToEntity(chauffeurDto)
                )
        );

        assertNotNull(chauffeurDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateChauffeur() {
    	String typePermis = "A";
    	String designation = "Poids Legers"; int validite = 10;
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	permisDto.setValidite(validite);
    	
    	String reference = "CH1"; String firstName = "Tairou"; String lastName = "Diallo";
    	String sexe = "M"; String addressActuel = "ADD1"; String mobilite = "Dk - Thies - Kaolack";
    	ChauffeurDto chauffeurDto = new ChauffeurDto();
    	chauffeurDto.setReference(reference); chauffeurDto.setFirstName(firstName);
    	chauffeurDto.setLastName(lastName); chauffeurDto.setSexe(sexe);
    	chauffeurDto.setAddressActuel(addressActuel); chauffeurDto.setMobilite(mobilite);
    	chauffeurDto.setPermisDto(permisDto);
    	
    	ChauffeurDto.fromEntityToDto(
                chauffeurRepository.save(
                		ChauffeurDto.fromDtoToEntity(chauffeurDto)
                )
        );

        String firstNameChauffeur = "DialloDiallo";
        chauffeurDto.setFirstName(firstNameChauffeur);
        chauffeurDto.setId((long) 1);
        ChauffeurDto.fromEntityToDto(chauffeurRepository.save(ChauffeurDto.fromDtoToEntity(chauffeurDto)));

        assertThat(chauffeurDto.getFirstName()).isEqualTo(firstNameChauffeur);

    }

    @Test
    public void testFindById() {
    	String typePermis = "A";
    	String designation = "Poids Legers";
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	
    	String reference = "CH1"; String firstName = "Tairou"; String lastName = "Diallo";
    	String sexe = "M"; String addressActuel = "ADD1"; String mobilite = "Dk - Thies - Kaolack";
    	ChauffeurDto chauffeurDto = new ChauffeurDto();
    	chauffeurDto.setReference(reference); chauffeurDto.setFirstName(firstName);
    	chauffeurDto.setLastName(lastName); chauffeurDto.setSexe(sexe);
    	chauffeurDto.setAddressActuel(addressActuel); chauffeurDto.setMobilite(mobilite);
    	chauffeurDto.setPermisDto(permisDto);
    	
    	ChauffeurDto chauffeurDtoResult = ChauffeurDto.fromEntityToDto(
                chauffeurRepository.save(
                		ChauffeurDto.fromDtoToEntity(chauffeurDto)
                )
        );

        Optional<Chauffeur> chauffeur = chauffeurRepository.findById(chauffeurDtoResult.getId());

        assertNotNull(chauffeur);
    }

    @Test
    public void testFindAll() {
    	String typePermis = "A";
    	String designation = "Poids Legers";
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	
    	String reference = "CH1"; String firstName = "Tairou"; String lastName = "Diallo";
    	String sexe = "M"; String addressActuel = "ADD1"; String mobilite = "Dk - Thies - Kaolack";
    	ChauffeurDto chauffeurDto = new ChauffeurDto();
    	chauffeurDto.setReference(reference); chauffeurDto.setFirstName(firstName);
    	chauffeurDto.setLastName(lastName); chauffeurDto.setSexe(sexe);
    	chauffeurDto.setAddressActuel(addressActuel); chauffeurDto.setMobilite(mobilite);
    	chauffeurDto.setPermisDto(permisDto);
    	
    	ChauffeurDto.fromEntityToDto(
                chauffeurRepository.save(
                		ChauffeurDto.fromDtoToEntity(chauffeurDto)
                )
        );
    	
    	String reference2 = "CH1"; String firstName2 = "Tairou"; String lastName2 = "Diallo";
    	ChauffeurDto chauffeurDto2 = new ChauffeurDto();
    	chauffeurDto2.setReference(reference2); chauffeurDto2.setFirstName(firstName2);
    	chauffeurDto2.setLastName(lastName2); chauffeurDto.setSexe(sexe);
    	chauffeurDto2.setPermisDto(permisDto);
    	
    	ChauffeurDto.fromEntityToDto(
                chauffeurRepository.save(
                		ChauffeurDto.fromDtoToEntity(chauffeurDto2)
                )
        );

        List<?> chauffeurs = chauffeurRepository.findAll();

        assertThat(chauffeurs).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
    	String typePermis = "A";
    	String designation = "Poids Legers";
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	
    	String reference = "CH1"; String firstName = "Tairou"; String lastName = "Diallo";
    	String sexe = "M"; String addressActuel = "ADD1"; String mobilite = "Dk - Thies - Kaolack";
    	ChauffeurDto chauffeurDto = new ChauffeurDto();
    	chauffeurDto.setReference(reference); chauffeurDto.setFirstName(firstName);
    	chauffeurDto.setLastName(lastName); chauffeurDto.setSexe(sexe);
    	chauffeurDto.setAddressActuel(addressActuel); chauffeurDto.setMobilite(mobilite);
    	chauffeurDto.setPermisDto(permisDto);
    	
    	ChauffeurDto chauffeurDtoResult = ChauffeurDto.fromEntityToDto(
                chauffeurRepository.save(
                		ChauffeurDto.fromDtoToEntity(chauffeurDto)
                )
        );

        boolean isExistBeforeDelete = chauffeurRepository.findById(chauffeurDtoResult.getId()).isPresent();

        chauffeurRepository.deleteById(chauffeurDtoResult.getId());

        boolean notExistAfterDelete = chauffeurRepository.findById(chauffeurDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
