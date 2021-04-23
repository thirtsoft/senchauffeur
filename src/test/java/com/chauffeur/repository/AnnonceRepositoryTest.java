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

import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.dto.PermisDto;
import com.chauffeur.dto.RecruteurDto;
import com.chauffeur.enumeration.StatusAnnonce;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AnnonceRepositoryTest {
	
	@Autowired
    private AnnonceRepository annonceRepository;

    @Test
    @Rollback(false)
    public void testCreateAnnonce() {
    	String typePermis = "A"; String designation = "Poids Legers";
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	
    	String firstName = "Tairou"; String lastName = "Diallo";
    	RecruteurDto recruteurDto = new RecruteurDto();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	String reference = "CH1"; String lieuPoste = "Tairou"; String salaire = "120000fcfa";
    	StatusAnnonce statusAnnonce = StatusAnnonce.ENCOURS;
    	AnnonceDto annonceDto = new AnnonceDto();
    	annonceDto.setReference(reference); annonceDto.setLieuPoste(lieuPoste);
    	annonceDto.setSalaire(salaire); annonceDto.setStatusAnnonce(statusAnnonce);
    	annonceDto.setPermisDto(permisDto); annonceDto.setRecruteurDto(recruteurDto);
    	
    	AnnonceDto annonceDtoResult = AnnonceDto.fromEntityToDto(
    			annonceRepository.save(
    					AnnonceDto.fromDtoToEntity(annonceDto)
                )
        );
    	
        assertNotNull(annonceDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateAnnonce() {
    	String typePermis = "A"; String designation = "Poids Legers";
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	
    	String firstName = "Tairou"; String lastName = "Diallo";
    	RecruteurDto recruteurDto = new RecruteurDto();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	String reference = "CH1"; String lieuPoste = "Tairou"; String salaire = "120000fcfa";
    	StatusAnnonce statusAnnonce = StatusAnnonce.ENCOURS;
    	AnnonceDto annonceDto = new AnnonceDto();
    	annonceDto.setReference(reference); annonceDto.setLieuPoste(lieuPoste);
    	annonceDto.setSalaire(salaire); annonceDto.setStatusAnnonce(statusAnnonce);
    	annonceDto.setPermisDto(permisDto); annonceDto.setRecruteurDto(recruteurDto);
    	
    	AnnonceDto.fromEntityToDto(
    			annonceRepository.save(
    					AnnonceDto.fromDtoToEntity(annonceDto)
                )
        );
    	
        String referenceChauffeur = "DialloDiallo";
        annonceDto.setReference(referenceChauffeur);
        annonceDto.setId((long) 1);
        AnnonceDto.fromEntityToDto(annonceRepository.save(AnnonceDto.fromDtoToEntity(annonceDto)));

        assertThat(annonceDto.getReference()).isEqualTo(referenceChauffeur);

    }

    @Test
    public void testFindById() {
    	String typePermis = "A"; String designation = "Poids Legers";
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	
    	String firstName = "Tairou"; String lastName = "Diallo";
    	RecruteurDto recruteurDto = new RecruteurDto();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	String reference = "CH1"; String lieuPoste = "Tairou"; String salaire = "120000fcfa";
    	StatusAnnonce statusAnnonce = StatusAnnonce.ENCOURS;
    	AnnonceDto annonceDto = new AnnonceDto();
    	annonceDto.setReference(reference); annonceDto.setLieuPoste(lieuPoste);
    	annonceDto.setSalaire(salaire); annonceDto.setStatusAnnonce(statusAnnonce);
    	annonceDto.setPermisDto(permisDto); annonceDto.setRecruteurDto(recruteurDto);
    	
    	AnnonceDto annonceDtoResult = AnnonceDto.fromEntityToDto(
    			annonceRepository.save(
    					AnnonceDto.fromDtoToEntity(annonceDto)
                )
        );
    	
    	boolean isExistAnnonce = annonceRepository.findById(annonceDtoResult.getId()).isPresent();

        assertTrue(isExistAnnonce);
        
    }

    @Test
    public void testFindAll() {
    	String typePermis = "A"; String designation = "Poids Legers";
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	
    	String firstName = "Tairou"; String lastName = "Diallo";
    	RecruteurDto recruteurDto = new RecruteurDto();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	String reference = "CH1"; String lieuPoste = "Tairou"; String salaire = "120000fcfa";
    	StatusAnnonce statusAnnonce = StatusAnnonce.ENCOURS;
    	AnnonceDto annonceDto = new AnnonceDto();
    	annonceDto.setReference(reference); annonceDto.setLieuPoste(lieuPoste);
    	annonceDto.setSalaire(salaire); annonceDto.setStatusAnnonce(statusAnnonce);
    	annonceDto.setPermisDto(permisDto); annonceDto.setRecruteurDto(recruteurDto);
    	
    	AnnonceDto.fromEntityToDto(
    			annonceRepository.save(
    					AnnonceDto.fromDtoToEntity(annonceDto)
                )
        );
    	
    	String reference2 = "CH1"; StatusAnnonce statusAnnonce2 = StatusAnnonce.VALIDEE;
    	AnnonceDto annonceDto2 = new AnnonceDto();
    	annonceDto2.setReference(reference2); annonceDto2.setStatusAnnonce(statusAnnonce2);
    	annonceDto.setPermisDto(permisDto); annonceDto.setRecruteurDto(recruteurDto);
    	
    	AnnonceDto.fromEntityToDto(
    			annonceRepository.save(
    					AnnonceDto.fromDtoToEntity(annonceDto)
                )
        );
    	
        List<?> notifications = annonceRepository.findAll();

        assertThat(notifications).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
    	String typePermis = "A"; String designation = "Poids Legers";
    	PermisDto permisDto = new PermisDto();
    	permisDto.setTypePermis(typePermis); permisDto.setDesignation(designation);
    	
    	String firstName = "Tairou"; String lastName = "Diallo";
    	RecruteurDto recruteurDto = new RecruteurDto();
    	recruteurDto.setFirstName(firstName); recruteurDto.setLastName(lastName);
    	
    	String reference = "CH1"; String lieuPoste = "Tairou"; String salaire = "120000fcfa";
    	StatusAnnonce statusAnnonce = StatusAnnonce.ENCOURS;
    	AnnonceDto annonceDto = new AnnonceDto();
    	annonceDto.setReference(reference); annonceDto.setLieuPoste(lieuPoste);
    	annonceDto.setSalaire(salaire); annonceDto.setStatusAnnonce(statusAnnonce);
    	annonceDto.setPermisDto(permisDto); annonceDto.setRecruteurDto(recruteurDto);
    	
    	AnnonceDto annonceDtoResult = AnnonceDto.fromEntityToDto(
    			annonceRepository.save(
    					AnnonceDto.fromDtoToEntity(annonceDto)
                )
        );
    	
        boolean isExistBeforeDelete = annonceRepository.findById(annonceDtoResult.getId()).isPresent();

        annonceRepository.deleteById(annonceDtoResult.getId());

        boolean notExistAfterDelete = annonceRepository.findById(annonceDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }


}
