package com.chauffeur;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.datetime.joda.LocalDateParser;

import com.chauffeur.enumeration.StatusAnnonce;
import com.chauffeur.models.Addresse;
import com.chauffeur.models.Annonce;
import com.chauffeur.models.Chauffeur;
import com.chauffeur.models.Permis;
import com.chauffeur.models.Recruteur;
import com.chauffeur.models.Ville;
import com.chauffeur.repository.AddresseRepository;
import com.chauffeur.repository.AnnonceRepository;
import com.chauffeur.repository.ChauffeurRepository;

import com.chauffeur.repository.PermisRepository;
import com.chauffeur.repository.RecruteurRepository;
import com.chauffeur.repository.VilleRepository;

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
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private AddresseRepository addresseRepository;
	
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
		Permis p1 = permisRepository.save(new Permis(1L, "P1","Permis Poids Legere",10));
		Permis p2 = permisRepository.save(new Permis(2L, "P2","Permis Professionnel",5));
		Permis p3 = permisRepository.save(new Permis(3L, "P3","Permis Tracteur",15));
		Permis p4 = permisRepository.save(new Permis(4L, "P4","Permis Transport",20));
		
		Ville v1 = villeRepository.save(new Ville(1L, "v1","Ziguinchor, Casamance","Senegal"));
		Ville v2 = villeRepository.save(new Ville(2L, "v2","Hann-Mariste 2, Dakar","Senegal"));
		Ville v3 = villeRepository.save(new Ville(3L, "v3","California","USA"));
		Ville v4 = villeRepository.save(new Ville(4L, "v4","Bignona, Casamance","Senegal"));
		
		Addresse ad1 = addresseRepository.save(new Addresse(1L, "Hann","Hann-Mariste2","Rue 254","254H","Dakar","SEN"));
		Addresse ad2 = addresseRepository.save(new Addresse(2L, "PA","Parcelle-Assenie","Rue 54","54PA","Dakar","SEN"));
		Addresse ad3 = addresseRepository.save(new Addresse(3L, "Sebi","Sebikotane","Rue 4","25S","Dakar","SEN"));
		Addresse ad4 = addresseRepository.save(new Addresse(4L, "Mer","Mermoz","Rue 4","4M","Dakar","SEN"));
		
		Chauffeur ch1 = new Chauffeur(1L, "ch1","tairou","diallo","M","Mariste","thirdiallo@gmail.com","779440310",3,
				60.0000,"Dk-Zig-Thies", "Full-Time", "cv1.pdf","photo1.jpg",p1, ad1);
		Chauffeur ch2 = new Chauffeur(2L, "ch2","Ablaye","Kanel","M","Rufisque","ablaye@gmail.com","777440310",7,
				60.0000,"Dk-TAMBA", "Full-Time","cv2.pdf","photo2.jpg",p2, ad2);
		Chauffeur ch3 = new Chauffeur(3L, "ch3","Kalidou","Badji","M","Parcelle","kalidou@gmail.com","776440310",10,
				60.0000,"Dk-Zig","Partial-Time","cv3.pdf","photo3.jpg",p3, ad1);
		Chauffeur ch4 = new Chauffeur(4L, "ch4","Fatou","diop","F","Mariste","fatou@gmail.com","774560310",5,
				60.0000,"Dk-Thies", "Partial-Time", "cv4.pdf","photo4.jpg",p4, ad3);
		Chauffeur ch5 = new Chauffeur(5L, "ch1","Adama","diallo","M","Mariste","thirdiallo@gmail.com","779440310",3,
				60.0000,"Dk-Zig-Thies","Full-Time","cv5.pdf","photo5.jpg",p1, ad2);
		Chauffeur ch6 = new Chauffeur(6L, "ch6","Sofie","Gaye","F","Rufisque","ablaye@gmail.com","777440310",7,
				60.0000,"Dk-TAMBA","Partail-Time","cv6.pdf","photo6.jpg",p2, ad1);
		Chauffeur ch7 = new Chauffeur(7L, "ch7","Marie","Sane","M","Parcelle","kalidou@gmail.com","776440310",10,
				60.0000,"Dk-Zig","Full-Time","cv7.pdf","photo7.jpg",p3, ad4);
		Chauffeur ch8 = new Chauffeur(8L, "ch8","Safi","diop","F","Mariste","fatou@gmail.com","774560310",5,
				60.0000,"Dk-Thies","Partial-Time","cv8.pdf","photo1.jpg",p4, ad3);
		Chauffeur ch9 = new Chauffeur(8L, "ch8","Safi","diop","F","Mariste","fatou@gmail.com","774560310",5,
				60.0000,"Dk-Thies","Full-Time","cv3.pdf","photo3.jpg",p4, ad4);
		Chauffeur ch10 = new Chauffeur(8L, "ch8","Safi","diop","F","Mariste","fatou@gmail.com","774560310",5,
				60.0000,"Dk-Thies","Partial-Time","cv5.pdf","photo4.jpg",p4, ad1);
		
		chauffeurRepository.save(ch1); chauffeurRepository.save(ch2);
		chauffeurRepository.save(ch3); chauffeurRepository.save(ch4);
		chauffeurRepository.save(ch5); chauffeurRepository.save(ch6);
		chauffeurRepository.save(ch7); chauffeurRepository.save(ch8);
		chauffeurRepository.save(ch9); chauffeurRepository.save(ch10);
		
		Recruteur r1 = new Recruteur(1L, "tairou","diallo","wokite","www.wokite.com","Ingénierie","thirdiallo@gmail.com","779440310","Mariste","Dakar","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		Recruteur r2 = new Recruteur(2L, "Kalidou","Badji","SenInnov","www.SenInnov.com","Multimedia","kalidou@gmail.com","779440310","Mariste","Dakar","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		Recruteur r3 = new Recruteur(3L, "Ablaye","kanel","Expat","www.Expat.com","RH","ablaye@gmail.com","779440310","Mariste","Dakar","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		recruteurRepository.save(r1); recruteurRepository.save(r2);
		recruteurRepository.save(r3);
		
		Annonce a1 = new Annonce();Annonce a2 = new Annonce();Annonce a3 = new Annonce();
		Annonce a4 = new Annonce();Annonce a5 = new Annonce();Annonce a6 = new Annonce();
		Annonce a7 = new Annonce(); 
		Annonce a8 = new Annonce();
		
		a1.setId(1L); a1.setLieuPoste("Hann-Mariste 2, Dakar"); a1.setModeCandidature("Email");
		a1.setSalaire("$1200 - $ 2500"); a1.setReference("Digital Marketing Executive"); a1.setStatusAnnonce(StatusAnnonce.ENCOURS);
		a1.setPermis(p1); a1.setRecruteur(r1); a1.setVille(v1); a1.setTime("Full-Time");
		a1.setDateCandidature(new Date());a1.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		annonceRepository.save(a1);
		
		a2.setId(2L); a2.setLieuPoste("Ziguinchor, Casamance"); a2.setModeCandidature("Email");
		a2.setSalaire("200M"); a2.setReference("Chauffeur Particulier"); a2.setStatusAnnonce(StatusAnnonce.REJETEE);
		a2.setPermis(p2); a2.setRecruteur(r2);a2.setVille(v2); a2.setTime("Partial-Time");
		a2.setDateCandidature(new Date()); a2.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		annonceRepository.save(a2);
		
		a3.setId(3L); a3.setLieuPoste("Parcelle, Dakar"); a3.setModeCandidature("Email");
		a3.setSalaire("$1800 - $3500"); a3.setReference("Conducteur Poids Lours"); a3.setStatusAnnonce(StatusAnnonce.VALIDEE);
		a3.setPermis(p3); a3.setRecruteur(r3); a3.setVille(v3); a3.setTime("Full-Time");
		a3.setDateCandidature(new Date()); a3.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		annonceRepository.save(a3);
		
		a4.setId(4L); a4.setLieuPoste("Parcelle, Dakar"); a4.setModeCandidature("Email");
		a4.setSalaire("$1800 - $3500"); a4.setReference("Conducteur Poids Legere");
		a4.setPermis(p3); a4.setRecruteur(r3); a4.setVille(v4); a4.setTime("Partial-Time");
		a4.setDateCandidature(new Date());
		annonceRepository.save(a4); a4.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		a8.setId(8L); a8.setLieuPoste("Sebikhotane, Dakar"); a8.setModeCandidature("Email");
		a8.setSalaire("$1500 - $ 3000"); a8.setReference("Chauffeur Saisonniere"); 
		a8.setPermis(p4); a8.setRecruteur(r2);
		a8.setTime("Full-Time");a8.setDateCandidature(new Date());
		a8.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		a5.setId(5L); a5.setLieuPoste("Ziguinchor, Casamance"); a5.setModeCandidature("Email");
		a5.setSalaire("$1500 - $3000"); a5.setReference("Chauffeur ONG"); 
		a5.setPermis(p1); a5.setRecruteur(r2);
		a5.setTime("Partial-Time");a5.setDateCandidature(new Date());
		a5.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		a6.setId(6L); a6.setLieuPoste("Thies, Senegal"); a6.setModeCandidature("Email");
		a6.setSalaire("$1500 - $3000"); a6.setReference("Chauffeur camion"); 
		a6.setPermis(p3); a6.setRecruteur(r3);
		a6.setTime("Full-Time");
		a6.setDateCandidature(new Date());
		a6.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		a7.setId(7L); a7.setLieuPoste("DK"); a7.setModeCandidature("Email");
		a7.setSalaire("200M"); a7.setReference("Chauffeur Taxi");
		a7.setPermis(p3); a7.setRecruteur(r3);
		a7.setTime("Full-Time");
		a7.setDateCandidature(new Date());
		a7.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		annonceRepository.save(a4); annonceRepository.save(a5);
		annonceRepository.save(a6);annonceRepository.save(a7);
		annonceRepository.save(a8);
		
		
		
		
		
	}

}
