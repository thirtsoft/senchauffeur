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

import com.chauffeur.enumeration.StatusAnnonce;
import com.chauffeur.models.Annonce;
import com.chauffeur.models.Chauffeur;
import com.chauffeur.models.Permis;
import com.chauffeur.models.Recruteur;
import com.chauffeur.repository.AnnonceRepository;
import com.chauffeur.repository.ChauffeurRepository;

import com.chauffeur.repository.PermisRepository;
import com.chauffeur.repository.RecruteurRepository;

@SpringBootApplication
public class SenChauffeurApplication implements CommandLineRunner {
	
	 private static final Logger LOG = LoggerFactory.getLogger(SenChauffeurApplication.class);
	
	@Autowired
	private PermisRepository permisRepository;
	@Autowired
	private ChauffeurRepository chauffeurRepository;
	@Autowired
	private RecruteurRepository recruteurRepository;
	@Autowired
	private AnnonceRepository annonceRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SenChauffeurApplication.class, args);
		
		createChauffeursDirectoryIfItDoesntExist();
		
		createAnnonceDirectoryIfItDoesntExist();
	}
	
	 private static void createChauffeursDirectoryIfItDoesntExist() {
	        Path path = Paths.get(System.getProperty("user.home") + "/senchauffeur/chauffeurphotos/");

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
		Permis p1 = permisRepository.save(new Permis(1L, "P1","P1",10));
		Permis p2 = permisRepository.save(new Permis(2L, "P2","P2",5));
		Permis p3 = permisRepository.save(new Permis(3L, "P3","P3",15));
		Permis p4 = permisRepository.save(new Permis(4L, "P4","P4",20));
		
		Chauffeur ch1 = new Chauffeur(1L, "ch1","tairou","diallo","M","Mariste","thirdiallo@gmail.com","779440310",3,
				600000,"Dk-Zig-Thies","cv1","photo1",p1);
		Chauffeur ch2 = new Chauffeur(2L, "ch2","Ablaye","Kanel","M","Rufisque","ablaye@gmail.com","777440310",7,
				600000,"Dk-TAMBA","cv2","photo2",p2);
		Chauffeur ch3 = new Chauffeur(3L, "ch3","Kalidou","Badji","M","Parcelle","kalidou@gmail.com","776440310",10,
				600000,"Dk-Zig","cv3","photo3",p3);
		Chauffeur ch4 = new Chauffeur(4L, "ch4","Fatou","diop","F","Mariste","fatou@gmail.com","774560310",5,
				600000,"Dk-Thies","cv4","photo4",p4);
		Chauffeur ch5 = new Chauffeur(5L, "ch1","Adama","diallo","M","Mariste","thirdiallo@gmail.com","779440310",3,
				600000,"Dk-Zig-Thies","cv5","photo5",p1);
		Chauffeur ch6 = new Chauffeur(6L, "ch6","Sofie","Gaye","F","Rufisque","ablaye@gmail.com","777440310",7,
				600000,"Dk-TAMBA","cv6","photo2",p2);
		Chauffeur ch7 = new Chauffeur(7L, "ch7","Marie","Sane","M","Parcelle","kalidou@gmail.com","776440310",10,
				600000,"Dk-Zig","cv3","photo7",p3);
		Chauffeur ch8 = new Chauffeur(8L, "ch8","Safi","diop","F","Mariste","fatou@gmail.com","774560310",5,
				600000,"Dk-Thies","cv8","photo8",p4);
		Chauffeur ch9 = new Chauffeur(8L, "ch8","Safi","diop","F","Mariste","fatou@gmail.com","774560310",5,
				600000,"Dk-Thies","cv8","photo8",p4);
		Chauffeur ch10 = new Chauffeur(8L, "ch8","Safi","diop","F","Mariste","fatou@gmail.com","774560310",5,
				600000,"Dk-Thies","cv8","photo8",p4);
		
		chauffeurRepository.save(ch1); chauffeurRepository.save(ch2);
		chauffeurRepository.save(ch3); chauffeurRepository.save(ch4);
		chauffeurRepository.save(ch5); chauffeurRepository.save(ch6);
		chauffeurRepository.save(ch7); chauffeurRepository.save(ch8);
		chauffeurRepository.save(ch9); chauffeurRepository.save(ch10);
		
		Recruteur r1 = new Recruteur(1L, "tairou","diallo","wokite","www.wokite.com","Ingénierie","thirdiallo@gmail.com","779440310","Mariste","Dakar");
		Recruteur r2 = new Recruteur(2L, "Kalidou","Badji","SenInnov","www.SenInnov.com","Multimedia","kalidou@gmail.com","779440310","Mariste","Dakar");
		Recruteur r3 = new Recruteur(3L, "Ablaye","kanel","Expat","www.Expat.com","RH","ablaye@gmail.com","779440310","Mariste","Dakar");
		recruteurRepository.save(r1); recruteurRepository.save(r2);
		recruteurRepository.save(r3);
		
		Annonce a1 = new Annonce();Annonce a2 = new Annonce();Annonce a3 = new Annonce();
		Annonce a4 = new Annonce();Annonce a5 = new Annonce();Annonce a6 = new Annonce();
		Annonce a7 = new Annonce();
		
		a1.setId(1L); a1.setLieuPoste("Hann-Mariste 2, Dakar"); a1.setModeCandidature("Email");
		a1.setSalaire("$1200 - $ 2500"); a1.setReference("Digital Marketing Executive"); a1.setStatusAnnonce(StatusAnnonce.ENCOURS);
		a1.setPermis(p1); a1.setRecruteur(r1);
		annonceRepository.save(a1);
		
		a2.setId(2L); a2.setLieuPoste("Ziguinchor, Casamance"); a2.setModeCandidature("Email");
		a2.setSalaire("200M"); a2.setReference("Chauffeur Particulier"); a2.setStatusAnnonce(StatusAnnonce.REJETEE);
		a2.setPermis(p2); a2.setRecruteur(r2);
		annonceRepository.save(a2);
		
		a3.setId(3L); a3.setLieuPoste("Parcelle, Dakar"); a3.setModeCandidature("Email");
		a3.setSalaire("$1800 - $3500"); a3.setReference("Conducteur Poids Lours"); a3.setStatusAnnonce(StatusAnnonce.VALIDEE);
		a3.setPermis(p3); a3.setRecruteur(r3);
		annonceRepository.save(a3);
	/*	
		a4.setId(4L); a4.setLieuPoste("Sebikhotane, Dakar"); a4.setModeCandidature("Email");
		a4.setSalaire("$1500 - $ 3000"); a4.setReference("Chauffeur Particulier"); a4.setStatusAnnonce(StatusAnnonce.ENCOURS);
		a4.setPermis(p4); a4.setRecruteur(r2);
		
		a5.setId(5L); a5.setLieuPoste("Ziguinchor, Casamance"); a5.setModeCandidature("Email");
		a5.setSalaire("$1500 - $3000"); a5.setReference("Chauffeur ONG"); a5.setStatusAnnonce(StatusAnnonce.VALIDEE);
		a5.setPermis(p1); a5.setRecruteur(r2);
		
		a6.setId(6L); a6.setLieuPoste("Thies, Senegal"); a6.setModeCandidature("Email");
		a6.setSalaire("$1500 - $3000"); a6.setReference("Chauffeur camion"); a6.setStatusAnnonce(StatusAnnonce.REJETEE);
		a6.setPermis(p3); a6.setRecruteur(r3);
		
		a7.setId(7L); a7.setLieuPoste("DK"); a7.setModeCandidature("Email");
		a7.setSalaire("200M"); a7.setReference("a1"); a7.setStatusAnnonce(StatusAnnonce.VALIDEE);
		a7.setPermis(p3); a7.setRecruteur(r3);
		
		annonceRepository.save(a4); annonceRepository.save(a5);
		annonceRepository.save(a6);annonceRepository.save(a7);
		
		*/
		
		
		
	}

}
