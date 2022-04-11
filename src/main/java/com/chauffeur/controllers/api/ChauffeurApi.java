package com.chauffeur.controllers.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chauffeur.dto.ChauffeurDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface ChauffeurApi {
	    
    @PostMapping(value = APP_ROOT + "/chauffeurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Chauffeur",
    notes = "Cette méthode permet d'ajouter un chauffeur", response = ChauffeurDto.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Le Chauffeur a été crée"),
	    @ApiResponse(code = 400, message = "Aucun Chauffeur  crée / modifié")
	
	})
    ResponseEntity<ChauffeurDto> save(@RequestBody ChauffeurDto chauffeurDto);
    
    @PostMapping(value = APP_ROOT + "/chauffeurs/createWithFiles")
    @ApiOperation(value = "Enregistrer un Chauffeur avec une photo et un cv",
    notes = "Cette méthode permet d'ajouter un chauffeur avec sa photo et son cv", response = ChauffeurDto.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Le Chauffeur a été crée"),
	    @ApiResponse(code = 400, message = "Aucun Chauffeur  crée / modifié")
	
	})
    ResponseEntity<ChauffeurDto> saveChauffeurWithFiles(
    		@RequestParam(name = "chauffeur") String chauffeurDto,
            @RequestParam(name = "photoChauffeur") MultipartFile photoChauffeur,
            @RequestParam(name = "cvChauffeur") MultipartFile cvChauffeur) throws IOException;
    
    @PostMapping(value = APP_ROOT + "/chauffeurs/createWithFilesInFolder")
    @ApiOperation(value = "Enregistrer un Chauffeur avec une photo et un cv",
    notes = "Cette méthode permet d'ajouter un chauffeur avec sa photo et son cv", response = ChauffeurDto.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Le Chauffeur a été crée"),
	    @ApiResponse(code = 400, message = "Aucun Chauffeur  crée / modifié")
	
	})
    ResponseEntity<ChauffeurDto> saveChauffeurWithFilesInFolder(
    		@RequestParam(name = "chauffeur") String chauffeurDto,
            @RequestParam(name = "photoChauffeur") MultipartFile photoChauffeur,
            @RequestParam(name = "cvChauffeur") MultipartFile cvChauffeur) throws IOException;
    
    
    @PutMapping(value = APP_ROOT + "/chauffeurs/update/{idChauffeur}", consumes = MediaType.APPLICATION_JSON_VALUE, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Chauffeur par son ID",
	    notes = "Cette méthode permet de modifier un Chauffeur par son ID", response = ChauffeurDto.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le Chauffeur a été modifiée"),
	    @ApiResponse(code = 400, message = "Le Chauffeur a n'est pas modifiée")
	})
    ResponseEntity<ChauffeurDto> update(@PathVariable("idChauffeur") Long id, @RequestBody ChauffeurDto chauffeurDto);

    @GetMapping(value = APP_ROOT + "/chauffeurs/findById/{idChauffeur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Chauffeur par ID",
    	notes = "Cette méthode permet de chercher un Chauffeur par son ID", response = ChauffeurDto.class
	)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le Chauffeur a été trouver"),
	    @ApiResponse(code = 404, message = "Aucun Chauffeur n'existe avec cette ID pas dans la BD")
		
	})
    ResponseEntity<ChauffeurDto> getChauffeurById(@PathVariable("idChauffeur") Long id);

    @GetMapping(value = APP_ROOT + "/chauffeurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Chauffeur",
	    notes = "Cette méthode permet de chercher et renvoyer la liste des Chauffeur", responseContainer = "List<ChauffeurDto>")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des Chauffeur / une liste vide")
	})
    ResponseEntity<List<ChauffeurDto>> getAllChauffeurs();
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Chauffeur par ordres Decroissant",
	    notes = "Cette méthode permet de chercher et renvoyer la liste des Chauffeur par ordres Decroissant", 
	    responseContainer = "List<ChauffeurDto>")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des Chauffeur / une liste vide")
	})
	ResponseEntity<List<ChauffeurDto>> getdAllChauffeursOrderByIdDesc();
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurBySelectedIsTrue", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste des chauffeurs selectionnés",
	    notes = "Cette méthode permet de rechercher et d'afficher liste des chauffeurs selectionnés")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des chauffeurs selectionnés")
	
	})
    ResponseEntity<List<ChauffeurDto>> getListChauffeurBySelected();
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeursByPermis/{pId}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste des chauffeurs par permis",
	    notes = "Cette méthode permet de rechercher et d'afficher liste des chauffeurs par permis")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des chauffeurs par permis")
	
	})
    ResponseEntity<List<ChauffeurDto>> getListChauffeurByPermis(@PathVariable("pId") Long pId);
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurByKeyword", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste des chauffeurs par mot clé",
	    notes = "Cette méthode permet de rechercher et d'afficher liste des chauffeurs par mot clé")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des chauffeurs par mot clé")
	
	})
    ResponseEntity<List<ChauffeurDto>> getListChauffeurByKeyword(@RequestParam(name = "keyword") String keyword);
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurByDisponibilite", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste des chauffeurs disponible",
	    notes = "Cette méthode permet de rechercher et d'afficher liste des chauffeurs disponible")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des chauffeurs disponibles")
	
	})
    ResponseEntity<List<ChauffeurDto>> getListChauffeurByDisponibility(@RequestParam(name = "disponible") String disponibility);
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/numberOfChauffeurPeerMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Décompter les chauffeurs inscrit par mois",
	    notes = "Cette méthode permet de recuperer et d'afficher le nombre de chauffeurs inscrit par mois sur un graphe")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le nombre de chauffeur / Mois")
	
	})
	List<?> countNumberOfChauffeurByMonth();
	
	@GetMapping(value = APP_ROOT + "/chauffeurs/numberOfChauffeurPeerYeer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Décompter les chauffeurs inscrit par années",
	    notes = "Cette méthode permet de recuperer et d'afficher le nombre de chauffeurs inscrit par années sur un graphe")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le nombre de chauffeur / Années")
	
	})
	List<?> countNumberOfChauffeurByYear();
	 
	@GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurByPageables", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher les chauffeurs par pages",
	    notes = "Cette méthode permet de recuperer et d'afficher la liste des chauffeurs par pages")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Liste des chauffeurs est")
	
	})
	Page<ChauffeurDto> getListChauffeurByPageable(@RequestParam(name = "page") int page, 
			@RequestParam(name = "size") int size);
	
	@GetMapping(value = APP_ROOT + "/chauffeurs/photoChauffeurInFolder/{idChauffeur}")
	@ApiOperation(value = "Recuperer la photo d'un chauffeur",
	    notes = "Cette méthode permet de recuperer et d'afficher la photo d'un chauffeur depuis le dossier webapp")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La photo a été recuperer depuis le dossier webapp")
	
	})
    byte[] getPhotoChauffeurInFolder(@PathVariable("idChauffeur") Long id) throws Exception;
	
	@GetMapping(value = APP_ROOT + "/chauffeurs/photoChauffeur/{idChauffeur}")
	@ApiOperation(value = "Recuperer la photo d'un chauffeur",
	    notes = "Cette méthode permet de recuperer et d'afficher la photo d'un chauffeur depuis un dossier externe utilisateur")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La photo a été recuperer depuis le dossier utilisateur")
	
	})
    byte[] getPhotoChauffeur(@PathVariable("idChauffeur") Long id) throws Exception;

    @PostMapping(path = APP_ROOT + "/chauffeurs/uploadChauffeurPhotoInFolder/{id}")
    @ApiOperation(value = "Enregistrer la photo d'un chauffeur dans webapp",
	    notes = "Cette méthode permet d'enregistrer la photo d'un chauffeur dans un dossier webapp")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La photo a été enregistré dans le dossier webapp")
	
	})
    void uploadPhotoChauffeurInFolder(MultipartFile file, @PathVariable("id") Long id) throws IOException;
    
    @PostMapping(path = APP_ROOT + "/chauffeurs/uploadChauffeurPhoto/{id}")
    @ApiOperation(value = "Enregistrer la photo d'un chauffeur dans un dossier",
	    notes = "Cette méthode permet d'enregistrer la photo d'un chauffeur dans un dossier externe utilisateur")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La photo a été enregistré dans le dossier utilisateur")
	
	})
    void uploadPhotoChauffeur(MultipartFile file, @PathVariable("id") Long id) throws IOException;
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/cvChauffeurInFolder/{idChauffeur}")
    @ApiOperation(value = "Recuperer le cv d'un chauffeur",
	    notes = "Cette méthode permet de recuperer et d'afficher le cv d'un chauffeur depuis un dossier wabapp")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le cv a été recuperer depuis le dossier wabapp")
	
	})
    byte[] getCvChauffeurInFolder(@PathVariable("idChauffeur") Long id) throws Exception;
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/cvChauffeur/{idChauffeur}")
    @ApiOperation(value = "Recuperer le cv d'un chauffeur",
	    notes = "Cette méthode permet de recuperer et d'afficher le cv d'un chauffeur depuis un dossier externe utilisateur")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le cv a été recuperer depuis le dossier utilisateur")
	
	})
    byte[] getCvChauffeur(@PathVariable("idChauffeur") Long id) throws Exception;

    @PostMapping(path = APP_ROOT + "/chauffeurs/uploadChauffeurCvInFolder/{id}")
    @ApiOperation(value = "Enregistrer le cv d'un chauffeur dans un dossier webapp",
	    notes = "Cette méthode permet d'enregistrer le cv d'un chauffeur dans un dossier webapp")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le cv a été enregistré dans le dossier webapp")
	
	})
    void uploadCvChauffeurInFolder(MultipartFile file, @PathVariable("id") Long id) throws IOException;
    
    @PostMapping(path = APP_ROOT + "/chauffeurs/uploadChauffeurCv/{id}")
    @ApiOperation(value = "Enregistrer le cv d'un chauffeur dans un dossier",
	    notes = "Cette méthode permet d'enregistrer le cv d'un chauffeur dans un dossier externe utilisateur")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le cv a été enregistré dans le dossier utilisateur")
	
	})
    void uploadCvChauffeur(MultipartFile file, @PathVariable("id") Long id) throws IOException;
    
    @RequestMapping(value = APP_ROOT + "/chauffeurs/downloadCvFile/{fileName:.+}")
    @ApiOperation(value = "Télécharger le cv d'un chauffeur",
	    notes = "Cette méthode permet de télécharger le cv d'un chauffeur depuis un dossier webapp")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le cv a été télécharger depuis le dossier webapp")
	
	})
    void downloadChauffeurCvFile(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("fileName") String fileName) throws IOException;
    
    @RequestMapping(value = APP_ROOT + "/chauffeurs/downloadContratFile/{fileName:.+}")
    @ApiOperation(value = "Télécharger le cv d'un chauffeur",
	    notes = "Cette méthode permet de télécharger le cv d'un chauffeur depuis un dossier externe utilisateur")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le cv a été télécharger depuis le dossier utilisateur")
	
	})
    void downloadChauffeurFile(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("fileName") String fileName) throws IOException;
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/NumbersOfChauffeurs")
    @ApiOperation(value = "Décompter le nombre total de chauffeurs",
	    notes = "Cette méthode permet de compter le nombre total de chauffeurs inscrits sur la plateforme")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le nombre total de chauffeur est")
	
	})
    BigDecimal getNumbersOfChauffeurs();
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurByDisponibityByPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une liste de chauffeurs disponible sur des pages",
	    notes = "Cette méthode permet de rechercher une liste de chauffeurs par mot clé sur la plateforme")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des chauffeurs est")
	
	})
    Page<ChauffeurDto> getChauffeurByKeywordByPageable(@RequestParam(name = "dispo") String mc,
													    	  @RequestParam(name = "page") int page,
															  @RequestParam(name = "size") int size);
													    		
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurByLocalityPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher des chauffeurs par localité",
	    notes = "Cette méthode permet de rechercher et d'afficher des chauffeurs par localité sur la plateforme")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des chauffeurs est")
	
	})
    Page<ChauffeurDto> getChauffeurByLocalityPageables(@RequestParam("id") Long addId, 
    														 @RequestParam(name = "page") int page,
    														 @RequestParam(name = "size") int size);
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurByPermisPageables", 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher des chauffeurs par permis",
	    notes = "Cette méthode permet de rechercher et d'afficher des chauffeurs par type de permis sur la plateforme")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des chauffeurs est")
	
	})
    Page<ChauffeurDto> getChauffeurByPermisPageables(
    		@RequestParam("id") Long permisId, 
    		@RequestParam(name = "page") int page,
    		@RequestParam(name = "size") int size);
    
    @DeleteMapping(value = APP_ROOT + "/chauffeurs/delete/{idChauffeur}")
    @ApiOperation(value = "Supprimer un chauffeur par ID",
	    notes = "Cette méthode permet de supprimer un chauffeur par son identifiant")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Le chauffeur a été supprimé")
	
	})
    void delete(@PathVariable("idChauffeur") Long id);
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/allChauffeurs")
    @ApiOperation(value = "Afficher la listes des Chauffeur par pages",
	    notes = "Cette méthode permet d'afficher des Chauffeurs par pages")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des Chauffeur est")
	
	})
    ResponseEntity<List<ChauffeurDto>> getAllChauffeurDtos(@RequestParam int page,@RequestParam int size);

    @GetMapping(value = APP_ROOT + "/chauffeurs/address")
    @ApiOperation(value = "Afficher la listes des Annonces par adresse par pages",
	    notes = "Cette méthode permet d'afficher des Annonces par address par pages")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des Chauffeurs est")

	})
    ResponseEntity<List<ChauffeurDto>> getAllChauffeurDtosByAddressId(@RequestParam Long id,@RequestParam int page,@RequestParam int size);

    @GetMapping(value = APP_ROOT + "/chauffeurs/chauffeurkey")
    @ApiOperation(value = "Afficher la listes des Chauffeurs par mot clé par pages",
	    notes = "Cette méthode permet d'afficher des Chauffeurs par mot clé par pages")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La liste des Chauffeurs est")
	
	})
    ResponseEntity<List<ChauffeurDto>> getChauffeurDtosByKeyWord(@RequestParam String disponibility,@RequestParam int page,@RequestParam int size);

    @GetMapping(value = APP_ROOT + "/chauffeurs/chauffeurDtoSize")
    @ApiOperation(value = "Calculer la longueur des Chauffeurs",
	    notes = "Cette méthode permet de calculer la taille des Chauffeurs")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La taille des Chauffeurs")
	
	})
    long chauffeurSize();

    @GetMapping(value = APP_ROOT + "/chauffeurs/ctaddressIdSize")
    @ApiOperation(value = "Calculer la longueur des Chauffeurs par Id addresse",
	    notes = "Cette méthode permet de calculer la taille des Chauffeurs par Id addresse")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La taille est")
	
	})
    long getChauffeursByIdAddressSize(@RequestParam Long id);

    @GetMapping(value = APP_ROOT + "/chauffeurs/keySize")
    @ApiOperation(value = "Calculer la longueur des Chauffeurs par mot clé",
	    notes = "Cette méthode permet de calculer la taille des Chauffeurs par mot clé")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La taille des Chauffeurs par mot clé est")
	
	})
    long sizeOfChauffeursByKey(@RequestParam String disponibility);


}
