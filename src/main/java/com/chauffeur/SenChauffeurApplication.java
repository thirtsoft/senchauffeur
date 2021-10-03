package com.chauffeur;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chauffeur.enumeration.RoleName;
import com.chauffeur.enumeration.StatusAnnonce;
import com.chauffeur.models.Addresse;
import com.chauffeur.models.Annonce;
import com.chauffeur.models.Chauffeur;
import com.chauffeur.models.Permis;
import com.chauffeur.models.Recruteur;
import com.chauffeur.models.Role;
import com.chauffeur.models.Tarif;
import com.chauffeur.models.Utilisateur;
import com.chauffeur.models.Ville;
import com.chauffeur.repository.AddresseRepository;
import com.chauffeur.repository.AnnonceRepository;
import com.chauffeur.repository.ChauffeurRepository;

import com.chauffeur.repository.PermisRepository;
import com.chauffeur.repository.RecruteurRepository;
import com.chauffeur.repository.RoleRepository;
import com.chauffeur.repository.TarifRepository;
import com.chauffeur.repository.UtilisateurRepository;
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
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private TarifRepository tarifRepository;
	
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
		Permis p5 = permisRepository.save(new Permis(5L, "P5","Permis Conducteur d’Engin",20));
		
		Ville v1 = villeRepository.save(new Ville(1L, "v1","Ziguinchor, Casamance","Senegal"));
		Ville v2 = villeRepository.save(new Ville(2L, "v2","Dakar, Dakar","Senegal"));
		Ville v3 = villeRepository.save(new Ville(3L, "v3","Thies","USA"));
		Ville v4 = villeRepository.save(new Ville(4L, "v4","Bignona, Casamance","Senegal"));
		
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
		
		
		Chauffeur ch1 = new Chauffeur(1L, "Chauffeur Personnel","tairou","diallo","M","Mariste","thirdiallo@gmail.com","779440310","3ans",
				60.0000,"Dk-Zig-Thies", "Full-Time",true, "cv1.pdf","photo1.jpg",p1, ad1);
		Chauffeur ch2 = new Chauffeur(2L, "Chauffeur particulier","Ablaye","Kanel","M","Rufisque","ablaye@gmail.com","777440310","7ans",
				60.0000,"Dk-TAMBA", "Full-Time", true, "cv2.pdf","photo2.jpg",p2, ad2);
		Chauffeur ch3 = new Chauffeur(3L, "Chauffeur Privé","Kalidou","Badji","M","Parcelle","kalidou@gmail.com","776440310","10ans",
				60.0000,"Dk-Zig","Partial-Time", false, "cv3.pdf","photo3.jpg",p3, ad1);
		Chauffeur ch4 = new Chauffeur(4L, "Chauffeur Livreur","Fatou","diop","F","Mariste","fatou@gmail.com","774560310","5ans",
				60.0000,"Dk-Thies", "Partial-Time", true, "cv4.pdf","photo4.jpg",p4, ad3);
		Chauffeur ch5 = new Chauffeur(5L, "Chauffeur Touristique","Adama","diallo","M","Mariste","thirdiallo@gmail.com","779440310","3ans",
				60.0000,"Dk-Zig-Thies","Full-Time", false, "cv5.pdf","photo5.jpg",p1, ad2);
		Chauffeur ch6 = new Chauffeur(6L, "Chauffeur Poids Lourds","Sofie","Gaye","F","Rufisque","ablaye@gmail.com","777440310","7ans",
				60.0000,"Dk-TAMBA","Partail-Time", true, "cv6.pdf","photo6.jpg",p2, ad1);
		Chauffeur ch7 = new Chauffeur(7L, "Chauffeur Camion","Marie","Sane","M","Parcelle","kalidou@gmail.com","776440310","10ans",
				60.0000,"Dk-Zig","Full-Time", true,"cv7.pdf","photo7.jpg",p5, ad4);
		Chauffeur ch8 = new Chauffeur(8L, "Chauffeur Bus","Safi","diop","F","Mariste","fatou@gmail.com","774560310","5ans",
				60.0000,"Dk-Thies","Partial-Time", false,"cv8.pdf","photo1.jpg",p4, ad3);
		Chauffeur ch9 = new Chauffeur(9L, "Chauffeur Taxi","Safi","diop","F","Mariste","fatou@gmail.com","774560310","5ans",
				60.0000,"Dk-Thies","Full-Time", false,"cv3.pdf","photo3.jpg",p4, ad4);
		Chauffeur ch10 = new Chauffeur(10L, "Chauffeur Transport","Safi","diop","F","Mariste","fatou@gmail.com","774560310","5ans",
				60.0000,"Dk-Thies","Partial-Time", true, "cv5.pdf","photo4.jpg",p4, ad1);
		
		Chauffeur ch11 = new Chauffeur(11L, "Chauffeur Engin","Saliou","diallo","Homme","Mariste 1","s.diallo@gmail.com","779440310","3ans",
				60.0000,"Dk-Zig-Thies", "Full-Time", false, "cv1.pdf","photo1.jpg",p1, ad11);
		Chauffeur ch12 = new Chauffeur(12L, "Chauffeur particulier","Chauffeur12","Chauff12","M","Rufisque","ch@gmail.com","777440310","7ans",
				60.0000,"Dk-TAMBA", "Full-Time", true, "cv2.pdf","photo2.jpg",p2, ad12);
		Chauffeur ch13 = new Chauffeur(13L, "Chauffeur Taxi","Chauffeur13","Chauffeur13","M","Parcelle","Chauffeur12@gmail.com","776440310","10ans",
				60.0000,"Dk-Zig","Partial-Time", false,"cv3.pdf","photo3.jpg",p3, ad11);
		Chauffeur ch14 = new Chauffeur(14L, "Chauffeur Camion","Fatou","diop","F","Mariste","fatou@gmail.com","774560310","5ans",
				60.0000,"Dk-Thies", "Partial-Time", false, "cv4.pdf","photo4.jpg",p4, ad13);
		Chauffeur ch15 = new Chauffeur(15L, "Chauffeur privé","Adama","diallo","M","Mariste","thirdiallo@gmail.com","779440310","3ans",
				60.0000,"Dk-Zig-Thies","Full-Time", true, "cv5.pdf","photo5.jpg",p1, ad2);
		Chauffeur ch16 = new Chauffeur(16L, "Chauffeur bus","Sofie","Gaye","F","Rufisque","ablaye@gmail.com","777440310","7ans",
				60.0000,"Dk-TAMBA","Partail-Time", true, "cv6.pdf","photo6.jpg",p2, ad1);
		Chauffeur ch17 = new Chauffeur(17L, "Chauffeur livreur","Marie","Sane","M","Parcelle","mari@gmail.com","776440310","10ans",
				60.0000,"Dk-Zig","Full-Time", true, "cv7.pdf","photo7.jpg",p3, ad14);
		Chauffeur ch18 = new Chauffeur(18L, "Chauffeur transport","Saly","diop","F","Mariste","sly@gmail.com","774560310","5ans",
				60.0000,"Dk-Thies","Partial-Time", false, "cv8.pdf","photo1.jpg",p4, ad1);
		Chauffeur ch19 = new Chauffeur(19L, "Chauffeur particulier","Asma","diop","F","Mariste","fatou@gmail.com","774560310","5ans",
				60.0000,"Dk-Thies","Full-Time", true, "cv3.pdf","photo3.jpg",p4, ad10);
		Chauffeur ch20 = new Chauffeur(20L, "Chauffeur taxi","Salif","diop","Femme","Mariste","fatou@gmail.com","774560310","5ans",
				60.0000,"Dk-Thies","Partial-Time", false, "cv5.pdf","photo4.jpg",p4, ad9);
		
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
		
		Recruteur r1 = new Recruteur(1L, "tairou","diallo","wokite","www.wokite.com","Ingénierie","thirdiallo@gmail.com","779440310","Mariste","Dakar","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		Recruteur r2 = new Recruteur(2L, "Kalidou","Badji","SenInnov","www.SenInnov.com","Multimedia","kalidou@gmail.com","779440310","Mariste","Dakar","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		Recruteur r3 = new Recruteur(3L, "Ablaye","kanel","Expat","www.Expat.com","RH","ablaye@gmail.com","779440310","Mariste","Dakar","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		recruteurRepository.save(r1); recruteurRepository.save(r2);
		recruteurRepository.save(r3);
		
		Annonce a1 = new Annonce();Annonce a2 = new Annonce();Annonce a3 = new Annonce();
		Annonce a4 = new Annonce();Annonce a5 = new Annonce();Annonce a6 = new Annonce();
		Annonce a7 = new Annonce(); Annonce a8 = new Annonce(); Annonce a9 = new Annonce();
		Annonce a10 = new Annonce();  Annonce a11 = new Annonce(); Annonce a12 = new Annonce();
		
		a1.setId(1L); a1.setLieuPoste("Hann-Mariste 2, Dakar"); a1.setSelected(true);;
		a1.setSalaire("$1200 - $ 2500"); a1.setReference("digit");a1.setLibelle("Digital Marketing Executive"); 
		a1.setStatusAnnonce(StatusAnnonce.ENCOURS);
		a1.setPermis(p1); a1.setRecruteur(r1); a1.setAddresse(ad1); a1.setTime("Full-Time");
		a1.setDateCandidature(new Date()); a1.setDateCloture(new Date());
		a1.setTypeContrat("Stage");
		a1.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		annonceRepository.save(a1);
		
		a2.setId(2L); a2.setLieuPoste("Ziguinchor, Casamance"); a2.setSelected(true);;
		a2.setSalaire("200M"); a2.setReference("part"); a2.setLibelle("Chauffeur Particulier");
		a2.setStatusAnnonce(StatusAnnonce.REJETEE);
		a2.setPermis(p2); a2.setRecruteur(r2);a2.setAddresse(ad2); a2.setTime("Partial-Time");
		a2.setDateCandidature(new Date()); a2.setDateCloture(new Date());
		a2.setTypeContrat("CDD");
		a2.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		annonceRepository.save(a2);
		
		a3.setId(3L); a3.setLieuPoste("Parcelle, Dakar"); a3.setSelected(false);;
		a3.setSalaire("$1800 - $3500"); a3.setReference("lourd");  a3.setLibelle("Conducteur Poids Lours");
		a3.setStatusAnnonce(StatusAnnonce.VALIDEE);
		a3.setPermis(p3); a3.setRecruteur(r3); a3.setAddresse(ad3); a3.setTime("Full-Time");
		a3.setDateCandidature(new Date()); a3.setDateCloture(new Date());
		a3.setTypeContrat("CDI");
		a3.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		annonceRepository.save(a3);
		
		a4.setId(4L); a4.setLieuPoste("Parcelle, Dakar"); a4.setSelected(false);;
		a4.setSalaire("$1800 - $3500"); a4.setReference("leger"); a4.setLibelle("Conducteur Poids Legere");
		a4.setPermis(p3); a4.setRecruteur(r3); a4.setAddresse(ad4); a4.setTime("Partial-Time");
		a4.setDateCandidature(new Date()); a4.setDateCloture(new Date());
		a4.setStatusAnnonce(StatusAnnonce.ENCOURS);
		a4.setTypeContrat("Stage");
		annonceRepository.save(a4); a4.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		a5.setId(5L); a5.setLieuPoste("Ziguinchor, Casamance"); a5.setSelected(true);;
		a5.setSalaire("$1500 - $3000"); a5.setReference("pro"); a5.setLibelle("Chauffeur Professionnel"); 
		a5.setPermis(p1); a5.setRecruteur(r2); a5.setAddresse(ad5);
		a5.setTime("Partial-Time");a5.setDateCandidature(new Date()); a5.setDateCloture(new Date());
		a5.setStatusAnnonce(StatusAnnonce.ENCOURS);
		a5.setTypeContrat("CDD");
		a5.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		a6.setId(6L); a6.setLieuPoste("Thies, Senegal"); a6.setSelected(false);;
		a6.setSalaire("$1500 - $3000"); a6.setReference("Conducteur Poids Lours"); 
		a6.setPermis(p3); a6.setRecruteur(r3); a6.setAddresse(ad6);
		a6.setTime("Full-Time");
		a6.setDateCandidature(new Date()); a6.setDateCloture(new Date());
		a6.setStatusAnnonce(StatusAnnonce.ENCOURS);
		a6.setTypeContrat("CDI");
		a6.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		a7.setId(7L); a7.setLieuPoste("DK"); a7.setSelected(false);;
		a7.setSalaire("200M"); a7.setReference("cam");a7.setLibelle("Conducteur de Camion");
		a7.setPermis(p3); a7.setRecruteur(r3);
		a7.setTime("Full-Time"); a7.setAddresse(ad7);
		a7.setDateCandidature(new Date()); a7.setDateCloture(new Date());
		a7.setTypeContrat("Stage");
		a7.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		a8.setId(8L); a8.setLieuPoste("Sebikhotane, Dakar"); a8.setSelected(true);;
		a8.setSalaire("$1500 - $ 3000"); a8.setReference("hum"); a8.setLibelle("Humain Resource Executive"); 
		a8.setPermis(p4); a8.setRecruteur(r2); a8.setAddresse(ad8);
		a8.setTime("Full-Time");a8.setDateCandidature(new Date()); a8.setDateCloture(new Date());
		a8.setTypeContrat("CDD");
		a8.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		a9.setId(9L); a9.setLieuPoste("DK"); a9.setSelected(false);;
		a9.setSalaire("900M"); a9.setReference("tax"); a9.setLibelle("Chauffeur de Taxi");
		a9.setPermis(p1); a9.setRecruteur(r1);
		a9.setTime("Partial-Time"); a9.setAddresse(ad9);
		a9.setDateCandidature(new Date()); a9.setDateCloture(new Date());
		a9.setTypeContrat("CDI");
		a9.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		a10.setId(10L); a10.setLieuPoste("DK"); a10.setSelected(false);;
		a10.setSalaire("1000M"); a10.setReference("trans"); a10.setLibelle("Conducteur de Transport");
		a10.setPermis(p2); a10.setRecruteur(r2);
		a10.setTime("Full-Time"); a10.setAddresse(ad10);
		a10.setDateCandidature(new Date()); a10.setDateCloture(new Date());
		a10.setTypeContrat("Stage");
		a10.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		a11.setId(11L); a11.setLieuPoste("DK"); a11.setSelected(true);;
		a11.setSalaire("1100M"); a11.setReference("moto"); a11.setLibelle("Chauffeur de Moto");
		a11.setPermis(p2); a11.setRecruteur(r2);
		a11.setTime("Full-Time"); a11.setAddresse(ad11);
		a11.setDateCandidature(new Date()); a11.setDateCloture(new Date());
		a11.setTypeContrat("CDD");
		a11.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		a12.setId(12L); a12.setLieuPoste("DK"); a12.setSelected(false);;
		a12.setSalaire("1200M"); a12.setReference("bag"); a12.setLibelle("Chauffeur de Taxi Bagage");
		a12.setPermis(p2); a12.setRecruteur(r2);
		a12.setTime("Full-Time"); a12.setAddresse(ad12);
		a12.setDateCandidature(new Date()); a12.setDateCloture(new Date());
		a12.setTypeContrat("CDI");
		a12.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		
		annonceRepository.save(a4); annonceRepository.save(a5);
		annonceRepository.save(a6);annonceRepository.save(a7);
		annonceRepository.save(a8);annonceRepository.save(a9);
		annonceRepository.save(a10);annonceRepository.save(a11);
		annonceRepository.save(a12);
		
		Tarif t1 = tarifRepository.save(new Tarif(1L, "OFFRE SIMPLE", "25000f cfa", "Assistance, votre publication sera en ligne 24/24 pendans 1 mois et mise en vedette.", a1));
		Tarif t2 = tarifRepository.save(new Tarif(1L, "OFFRE GOLDEN", "30000f cfa", "Assistance, votre publication sera en ligne 24/24 pendans 1/2 mois et mise en vedette..", a2));
		Tarif t3 = tarifRepository.save(new Tarif(1L, "OFFRE SPECIALISEE", "40000f cfa", "Assistance, votre publication sera en ligne 24/24 pendans 2 mois, mise en vedette, booster sur les réseaux sociaux", a3));
		
		Role useRole = new Role(RoleName.ROLE_USER);
        Role recruteurRole = new Role(RoleName.ROLE_RECRUTEUR);
        Role adminRole = new Role(RoleName.ROLE_ADMIN);
        roleRepository.save(useRole);
        roleRepository.save(recruteurRole);
        roleRepository.save(adminRole);
        Utilisateur user = new Utilisateur();
        user.setId(1L);
        user.setUsername("User");
        user.setName("User");
        user.setPassword("user1234");
    //    user.setRoles((Set<Role>) useRole);
        Utilisateur manager = new Utilisateur();
        manager.setId(2L);
        manager.setUsername("Manager");
        manager.setName("Manager");
        manager.setPassword("manager1234");
 //       manager.setRoles((Set<Role>) managerRole);
        Utilisateur admin = new Utilisateur();
        admin.setId(3L);
        admin.setUsername("Admin");
        admin.setName("Admin");
        admin.setPassword("admin1234");
 //       admin.setRoles((Set<Role>) adminRole);
        utilisateurRepository.save(user);
        utilisateurRepository.save(manager);
        utilisateurRepository.save(admin);
		
		
		
		
		
	}

}
