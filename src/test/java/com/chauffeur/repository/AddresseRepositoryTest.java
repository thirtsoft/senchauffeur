package com.chauffeur.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.chauffeur.models.Addresse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddresseRepositoryTest {
	
	@Autowired
	private AddresseRepository addresseRepository;
	
	@Test
	@Rollback(false)
	public void testCreateAddresse() {
		
		String reference = "SEN";
		Addresse address = new Addresse();
		address.setReference(reference); address.setPays("SENENAL");
		address.setVille("Dakar");address.setCodePostal("23345");
		
	    Addresse addressResult = addresseRepository.save(address);
	    
	    assertNotNull(addressResult);

	}


}
