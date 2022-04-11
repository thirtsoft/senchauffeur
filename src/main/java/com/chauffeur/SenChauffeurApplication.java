package com.chauffeur;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.chauffeur.models.*;
import com.chauffeur.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.chauffeur.enumeration.RoleName;
import com.chauffeur.services.UtilisateurService;



@SpringBootApplication
public class SenChauffeurApplication implements CommandLineRunner {
	
	private static final Logger LOG = LoggerFactory.getLogger(SenChauffeurApplication.class);

	@Autowired
	private PermisRepository permisRepository;
	@Autowired
	private ChauffeurRepository chauffeurRepository;

	@Autowired
	private AddresseRepository addresseRepository;

	@Autowired
	private TarifRepository tarifRepository;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SenChauffeurApplication.class, args);
		
		createChauffeursDirectoryPhotoIfItDoesntExist();
		
		createChauffeursDirectoryCvIfItDoesntExist();
		
		createAnnonceDirectoryIfItDoesntExist();
	}
	
	 private static void createChauffeursDirectoryPhotoIfItDoesntExist() {
	        Path path = Paths.get(System.getProperty("user.home") + "/senchauffeur/chauffeur/photos/");

	        if (Files.notExists(path)) {
	            try {
	                Files.createDirectories(path);
	            } catch (IOException ie) {
	                LOG.error(String.format("Problem creating directory %s", path));
	            }
	        }
	   }
	 
	 private static void createChauffeursDirectoryCvIfItDoesntExist() {
	        Path path = Paths.get(System.getProperty("user.home") + "/senchauffeur/chauffeur/cvs/");

	        if (Files.notExists(path)) {
	            try {
	                Files.createDirectories(path);
	            } catch (IOException ie) {
	                LOG.error(String.format("Problem creating directory %s", path));
	            }
	        }
	    }
	 
	 private static void createAnnonceDirectoryIfItDoesntExist() {
	        Path path = Paths.get(System.getProperty("user.home") + "/senchauffeur/annoncephotos/");

	        if (Files.notExists(path)) {
	            try {
	                Files.createDirectories(path);
	            } catch (IOException ie) {
	                LOG.error(String.format("Problem creating directory %s", path));
	            }
	        }
	    }

	@Override
	public void run(String... args) throws Exception {

		/*
		Permis p1 = permisRepository.save(new Permis(1L, "P1","Permis Poids Legere",10));
		Permis p2 = permisRepository.save(new Permis(2L, "P2","Permis Professionnel",5));
		Permis p3 = permisRepository.save(new Permis(3L, "P3","Permis Tracteur",15));
		Permis p4 = permisRepository.save(new Permis(4L, "P4","Permis Transport",20));
		Permis p5 = permisRepository.save(new Permis(5L, "P5","Permis Conducteur d’Engin",20));

		Addresse ad1 = addresseRepository.save(new Addresse(1L, "Zig","Ziguinchor","Rue 254","254H","Sud","SENEGAL"));
		Addresse ad2 = addresseRepository.save(new Addresse(2L, "Dk","Dakar","Rue 54","54PA","Dakar","SENEGAL"));
		Addresse ad3 = addresseRepository.save(new Addresse(3L, "Th","Thies","Rue 4","25S","Dakar","SENEGAL"));
		Addresse ad4 = addresseRepository.save(new Addresse(4L, "Kd","Kolda","Rue 4","4M","Dakar","SENEGAL"));
		Addresse ad5 = addresseRepository.save(new Addresse(5L, "Sed","Sedhiou","Rue 254","254H","Sud","SENEGAL"));
		Addresse ad6 = addresseRepository.save(new Addresse(6L, "Kl","Kaolack","Rue 54","54PA","Nord","SENEGAL"));
		Addresse ad7 = addresseRepository.save(new Addresse(7L, "Lg","Louga","Rue 4","25S","Nord","SENEGAL"));
		Addresse ad8 = addresseRepository.save(new Addresse(8L, "St","Saint-Louis","Rue 4","4M","Nord","SENEGAL"));

		Addresse ad9 = addresseRepository.save(new Addresse(9L, "Tb","Tambacounda","Rue 254","254H","Sud","SENEGAL"));
		Addresse ad10 = addresseRepository.save(new Addresse(10L, "Ft","Fatick","Rue 54","54PA","Facick","SENEGAL"));
		Addresse ad11 = addresseRepository.save(new Addresse(11L, "Kaf","Kaffrine","Rue 4","25S","Kaffrine","SENEGAL"));
		Addresse ad12 = addresseRepository.save(new Addresse(12L, "Ked","Kedougou","Rue 4","4M","Kedougou","SENEGAL"));
		Addresse ad13 = addresseRepository.save(new Addresse(13L, "Mat","Matam","Rue 4","25S","Dakar","SENEGAL"));
		Addresse ad14 = addresseRepository.save(new Addresse(14L, "Diour","Diourbel","Rue 4","4M","Dakar","SENEGAL"));


		Chauffeur ch1 = new Chauffeur(1L, "Chauffeur Personnel","tairou","diallo","M","thirdiallo@gmail.com","779440310","3ans",600000,
				"Dk-Zig-Thies","Full-Time", true,"cv1.pdf", "photo1.jpg",p1, ad1);
		Chauffeur ch2 = new Chauffeur(2L, "Chauffeur particulier","Ablaye","Kanel","M","ablaye@gmail.com","777440310","7ans",600000,
				"Dk-TAMBA","Full-Time", true,"cv2.pdf", "photo2.jpg",p2, ad2);
		Chauffeur ch3 = new Chauffeur(3L, "Chauffeur Livreur","Kalidou","Badji","M","kalidou@gmail.com","776440310","10ans",600000,
				"Dk-TAMBA","Full-Time", true,"cv3.pdf", "photo3.jpg",p3, ad1);
		Chauffeur ch4 = new Chauffeur(4L, "Chauffeur Touristique","Fatou","diop","F","fatou@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Partial-Time", true,"cv4.pdf", "photo4.jpg",p4, ad3);
		Chauffeur ch5 = new Chauffeur(5L, "Chauffeur Touristique","Adama","diallo","F","adama@gmail.com","776440310","10ans",600000,
				"Dk-Zig-Thies","Full-Time", false,"cv5.pdf", "photo5.jpg",p1, ad1);
		Chauffeur ch6 = new Chauffeur(6L, "Chauffeur Poids Lourds","Sofie","Gaye","F","sofie@gmail.com","776440310","10ans",600000,
				"Dk-Zig-TAMBA","Partail-Time", true,"cv6.pdf", "photo6.jpg",p2, ad1);
		Chauffeur ch7 = new Chauffeur(7L, "Chauffeur Camion","Marie","Sane","F","marie@gmail.com","776440310","10ans",600000,
				"Dk-Zig","Full-Time", true,"cv7.pdf", "photo7.jpg",p5, ad4);
		Chauffeur ch8 = new Chauffeur(8L, "Chauffeur Camion","Safi","diop","F","fatou@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Partial-Time", false,"cv8.pdf", "photo1.jpg",p4, ad3);
		Chauffeur ch9 = new Chauffeur(9L, "Chauffeur Taxi","Safi","diop","F","safie@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Full-Time", false,"cv3.pdf", "photo3.jpg",p4, ad4);
		Chauffeur ch10 = new Chauffeur(10L, "Chauffeur Transport","Safi","diop","F","safie@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Full-Time", true,"cv5.pdf", "photo4.jpg",p4, ad4);
		Chauffeur ch11 = new Chauffeur(11L, "Chauffeur Engin","Saliou","diallo","M","saliou@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Full-Time", false,"cv5.pdf", "photo4.jpg",p1, ad11);
		Chauffeur ch12 = new Chauffeur(12L, "Chauffeur particulier","Saliou","diallo","M","saliou@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Full-Time", true,"cv2.pdf", "photo2.jpg",p2, ad12);
		Chauffeur ch13 = new Chauffeur(13L, "Chauffeur Taxi","Chauffeur13","Chauffeur13","M","saliou@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Full-Time", false,"cv2.pdf", "photo3.jpg",p3, ad11);
		Chauffeur ch14 = new Chauffeur(14L, "Chauffeur Camion","Chauffeur14","Chauffeur14","M","saliou@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Full-Time", false,"cv2.pdf", "photo3.jpg",p4, ad13);
		Chauffeur ch15 = new Chauffeur(15L, "Chauffeur privé","Chauffeur15","Chauffeur15","M","saliou@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Full-Time", true,"cv5.pdf", "photo5.jpg",p1, ad2);
		Chauffeur ch16 = new Chauffeur(16L, "Chauffeur bus","Fatim","Fatim","M","saliou@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Partail-Time", true,"cv6.pdf", "photo6.jpg",p2, ad2);
		Chauffeur ch17 = new Chauffeur(17L, "Chauffeur livreur","Fatim","Fatim","M","saliou@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Full-Time", true,"cv7.pdf", "photo7.jpg",p3, ad14);
		Chauffeur ch18 = new Chauffeur(18L, "Chauffeur transport","Saly","diop","M","saly@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Full-Partial", false,"cv8.pdf", "photo1.jpg",p4, ad1);
		Chauffeur ch19 = new Chauffeur(19L, "Chauffeur particulier","Asma","diop","M","Asma@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Full-Partial", false,"cv8.pdf", "photo3.jpg",p4, ad10);
		Chauffeur ch20 = new Chauffeur(20L, "Chauffeur taxi","Salif","diop","M","salif@gmail.com","776440310","10ans",600000,
				"Dk-Thies","Partial-Partial", false,"cv5.pdf", "photo4.jpg",p4, ad9);

		chauffeurRepository.save(ch1); chauffeurRepository.save(ch2);
		chauffeurRepository.save(ch3); chauffeurRepository.save(ch4);

		chauffeurRepository.save(ch5); chauffeurRepository.save(ch6);
		chauffeurRepository.save(ch7); chauffeurRepository.save(ch8);
		chauffeurRepository.save(ch9); chauffeurRepository.save(ch10);

		chauffeurRepository.save(ch11); chauffeurRepository.save(ch12);
		chauffeurRepository.save(ch13); chauffeurRepository.save(ch14);
		chauffeurRepository.save(ch15); chauffeurRepository.save(ch16);
		chauffeurRepository.save(ch17); chauffeurRepository.save(ch18);
		chauffeurRepository.save(ch19); chauffeurRepository.save(ch20);

		Role useRole = new Role(RoleName.ROLE_USER);
        Role roleGestionnaire = new Role(RoleName.ROLE_GESTIONNAIRE);
        Role roleManager = new Role(RoleName.ROLE_MANAGER);
        Role adminRole = new Role(RoleName.ROLE_ADMIN);
        roleRepository.save(useRole);
        roleRepository.save(roleGestionnaire);
        roleRepository.save(roleManager);
        roleRepository.save(adminRole);
        Utilisateur user = new Utilisateur();
        user.setId(1L);
        user.setUsername("User");
        user.setName("User");
        user.setPassword("user1234");
        user.setPassword(bCryptPasswordEncoder.encode("user1234"));
        Utilisateur manager = new Utilisateur();
        manager.setId(2L);
        manager.setUsername("Manager");
        manager.setName("Manager");
        manager.setPassword("manager1234");
        manager.setPassword(bCryptPasswordEncoder.encode("manager1234"));
        Utilisateur admin = new Utilisateur();
        admin.setId(3L);
        admin.setUsername("Admin");
        admin.setName("Admin");
        admin.setPassword(bCryptPasswordEncoder.encode("admin1234"));

        utilisateurRepository.save(user);
        utilisateurRepository.save(manager);
        utilisateurRepository.save(admin);
        
        utilisateurService.addRoleToUser("Admin", RoleName.ROLE_ADMIN);
        utilisateurService.addRoleToUser("User", RoleName.ROLE_USER);

*/
		
		
		
		
		
	}

}
