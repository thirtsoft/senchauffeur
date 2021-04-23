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

import com.chauffeur.dto.PermisDto;
import com.chauffeur.models.Permis;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PermisRepositoryTest {
	
	@Autowired
    private PermisRepository permisRepository;

    @Test
    @Rollback(false)
    public void testCreatePermis() {
    	String typePermis = "A";
    	String designation = "Poids Legers"; int validite = 10;
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	permisDto.setValidite(validite);
    	
    	PermisDto permisDtoResult = PermisDto.fromEntityToDto(
                permisRepository.save(
                		PermisDto.fromDtoToEntity(permisDto)
                )
        );

        assertNotNull(permisDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdatePermis() {
    	String typePermis = "A";
    	String designation = "Poids Legers"; int validite = 10;
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	permisDto.setValidite(validite);
    	
    	PermisDto.fromEntityToDto(
                permisRepository.save(
                		PermisDto.fromDtoToEntity(permisDto)
                )
        );
    	
        String designationPermis = "Poids Leger";
        permisDto.setDesignation(designationPermis);
        permisDto.setId((long) 1);
        
        PermisDto.fromEntityToDto(permisRepository.save(PermisDto.fromDtoToEntity(permisDto)));

        assertThat(permisDto.getDesignation()).isEqualTo(designationPermis);

    }

    @Test
    public void testFindById() {
    	String typePermis = "A";
    	String designation = "Poids Legers"; int validite = 10;
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	permisDto.setValidite(validite);
    	
    	PermisDto permisDtoResult = PermisDto.fromEntityToDto(
                permisRepository.save(
                		PermisDto.fromDtoToEntity(permisDto)
                )
        );
        
        Optional<Permis> permis = permisRepository.findById(permisDtoResult.getId());

        assertNotNull(permis);
    }

   
    @Test
    public void testFindAll() {
    	String typePermis = "A";
    	String designation = "Poids Legers"; int validite = 10;
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	permisDto.setValidite(validite);
    	
    	PermisDto.fromEntityToDto(
                permisRepository.save(
                		PermisDto.fromDtoToEntity(permisDto)
                )
        );
    	
    	String typePermis2 = "A";
    	String designation2 = "Poids Legers"; int validite2 = 10;
    	PermisDto permisDto2 = new PermisDto();
    	permisDto2.setTypePermis(typePermis2); permisDto2.setDesignation(designation2);
    	permisDto2.setValidite(validite2);
    	
    	PermisDto.fromEntityToDto(
                permisRepository.save(
                		PermisDto.fromDtoToEntity(permisDto2)
                )
        );

        List<?> permis = permisRepository.findAll();

        assertThat(permis).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
    	String typePermis = "A";
    	String designation = "Poids Legers"; int validite = 10;
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	permisDto.setValidite(validite);
    	
    	PermisDto permisDtoResult = PermisDto.fromEntityToDto(
                permisRepository.save(
                		PermisDto.fromDtoToEntity(permisDto)
                )
        );

        boolean isExistBeforeDelete = permisRepository.findById(permisDtoResult.getId()).isPresent();

        permisRepository.deleteById(permisDtoResult.getId());

        boolean notExistAfterDelete = permisRepository.findById(permisDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
