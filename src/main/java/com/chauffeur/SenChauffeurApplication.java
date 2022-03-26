package com.chauffeur;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.chauffeur.enumeration.RoleName;
import com.chauffeur.models.Role;
import com.chauffeur.models.Utilisateur;
import com.chauffeur.repository.RoleRepository;
import com.chauffeur.repository.UtilisateurRepository;
import com.chauffeur.services.UtilisateurService;



@SpringBootApplication
public class SenChauffeurApplication implements CommandLineRunner {
	
	private static final Logger LOG = LoggerFactory.getLogger(SenChauffeurApplication.class);
	

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
