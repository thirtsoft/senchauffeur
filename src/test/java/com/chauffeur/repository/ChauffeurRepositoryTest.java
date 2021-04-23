package com.chauffeur.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.chauffeur.dto.ChauffeurDto;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChauffeurRepositoryTest {
	
	@Autowired
    private ChauffeurRepository chauffeurRepository;

    @Test
    @Rollback(false)
    public void testCreateCategory() {
    	String reference = "CH1"; String firstName = "Tairou"; String lastName = "Diallo";
    	String sexe = "M"; String addressActuel = "ADD1"; String email = "ch1@gmail.com";
    	String phoneChauffeur = "779330410"; int nbreAnneeExperience = 7; 
    	double pretentionSalaire = 200000;String cvChauffeur = "cv1"; 
    	String mobilite = "Dk - Thies - Kaolack"; String photoChauffeur = "photo1";
    	
    	ChauffeurDto chauffeurDto = new ChauffeurDto();
    	
        CategorieDto categorieDto = new CategorieDto(1,"sac", "sac a mai");
        CategorieDto categoryDtoResult = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        );

        assertNotNull(categoryDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateCategory() {
        CategorieDto categorieDto = new CategorieDto(1,"sac", "PAPIER RAM");
        CategorieDto categoryDtoResult = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        );

        String categoryDesignation = "Bureau";
        CategorieDto categorieUpdateDto = new CategorieDto(1,"Bureau", categoryDesignation);

        categorieUpdateDto.setId((long) 1);
        CategorieDto.fromEntityToDto(categorieRepository.save(CategorieDto.fromDtoToEntity(categorieUpdateDto)));

        assertThat(categorieUpdateDto.getDesignation()).isEqualTo(categoryDesignation);

    }

    @Test
    public void testFindById() {

        CategorieDto categorieDto = new CategorieDto(1,"sac", "sac a mai");
        CategorieDto categoryDtoResult = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        );

        Long cat_id = (long) 1;
        Optional<Categorie> categorie = categorieRepository.findById(categoryDtoResult.getId());

        assertNotNull(categorie);
    }

    @Test
    public void testFindByDesignation() {
        CategorieDto categorieDto = new CategorieDto(1,"Robe", "RobeMariage");
        CategorieDto categoryDtoResult = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        );
        String catDesignation = "RobeMariage";
        assertThat(categoryDtoResult.getDesignation()).isEqualTo(catDesignation);
    }

    @Test
    public void testFindAll() {
        CategorieDto categorieDto = new CategorieDto(1,"Robe", "sac a mai");
        CategorieDto categoryDtoResult = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        );
        CategorieDto categorieDto1 = new CategorieDto(1,"Panthalon", "Panthalon homme");
        CategorieDto categoryDtoResult1 = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto1)
                )
        );

        CategorieDto categorieDto2 = new CategorieDto(1,"Chemise", "Chemise Femme");
        CategorieDto categoryDtoResult2 = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto2)
                )
        );

        List<?> categories = categorieRepository.findAll();

        assertThat(categories).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {

        CategorieDto categorieDto = new CategorieDto(1,"Chemise", "Chemise Femme");
        CategorieDto categoryDtoResult2 = CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        );

        Optional<Categorie> categorie = categorieRepository.findById(categoryDtoResult2.getId());

        Long id = (long) 1;

        boolean isExistBeforeDelete = categorieRepository.findById(categoryDtoResult2.getId()).isPresent();

        categorieRepository.deleteById(categoryDtoResult2.getId());

        boolean notExistAfterDelete = categorieRepository.findById(categoryDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
