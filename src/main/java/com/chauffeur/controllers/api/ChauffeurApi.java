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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.chauffeur.dto.ChauffeurDto;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface ChauffeurApi {
	    
    @PostMapping(value = APP_ROOT + "/chauffeurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ChauffeurDto> save(@RequestBody ChauffeurDto chauffeurDto);
    
    @PostMapping(value = APP_ROOT + "/chauffeurs/createWithFiles")
    ResponseEntity<ChauffeurDto> saveChauffeurWithFiles(
    		@RequestPart(name = "chauffeur") String chauffeurDto,
            @RequestParam(name = "photoChauffeur") MultipartFile photoChauffeur,
            @RequestParam(name = "cvChauffeur") MultipartFile cvChauffeur) throws IOException;
    
    @PutMapping(value = APP_ROOT + "/chauffeurs/update/{idChauffeur}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ChauffeurDto> update(@PathVariable("idChauffeur") Long id, @RequestBody ChauffeurDto chauffeurDto);

    @GetMapping(value = APP_ROOT + "/chauffeurs/findById/{idChauffeur}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ChauffeurDto> findById(@PathVariable("idChauffeur") Long id);

    @GetMapping(value = APP_ROOT + "/chauffeurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ChauffeurDto> findAll();
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ChauffeurDto>> getdAllChauffeursOrderByIdDesc();
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurBySelectedIsTrue", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ChauffeurDto> getListChauffeurBySelected();
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeursByPermis/{pId}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	List<ChauffeurDto> getListChauffeurByPermis(@PathVariable("pId") Long pId);
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurByKeyword", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	List<ChauffeurDto> getListChauffeurByKeyword(@RequestParam(name = "keyword") String keyword);
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurByDisponibilite", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	List<ChauffeurDto> getListChauffeurByDisponibility(@RequestParam(name = "disponible") String disponibility);
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/numberOfChauffeurPeerMonth", produces = MediaType.APPLICATION_JSON_VALUE)
	List<?> countNumberOfChauffeurByMonth();
	
	@GetMapping(value = APP_ROOT + "/chauffeurs/numberOfChauffeurPeerYeer", produces = MediaType.APPLICATION_JSON_VALUE)
	List<?> countNumberOfChauffeurByYear();
	 
	@GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurByPageables", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	Page<ChauffeurDto> getListChauffeurByPageable(@RequestParam(name = "page") int page, 
			@RequestParam(name = "size") int size);
	
	
	@GetMapping(value = APP_ROOT + "/chauffeurs/photoChauffeur/{idChauffeur}")
    byte[] getPhotoChauffeur(@PathVariable("idChauffeur") Long id) throws Exception;

    @PostMapping(path = APP_ROOT + "/chauffeurs/uploadChauffeurPhoto/{idChauffeur}")
    void uploadPhotoChauffeur(@RequestParam(name = "photoArticle") MultipartFile photoChauffeur, 
    		@PathVariable("idChauffeur") Long idChauffeur) throws IOException;
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/cvChauffeur/{idChauffeur}")
    byte[] getCvChauffeur(@PathVariable("idChauffeur") Long id) throws Exception;

    @PostMapping(path = APP_ROOT + "/chauffeurs/uploadChauffeurCv/{idChauffeur}")
    void uploadCvChauffeur(@RequestParam(name = "cvChauffeur") MultipartFile cvChauffeur, 
    		@PathVariable("idChauffeur") Long idChauffeur) throws IOException;
    
    @RequestMapping(value = APP_ROOT + "/chauffeurs/downloadContratFile/{fileName:.+}")
    public void downloadChauffeurFile(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("fileName") String fileName) throws IOException;
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/NumbersOfChauffeurs")
    public BigDecimal getNumbersOfChauffeurs();
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurByDisponibityByPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ChauffeurDto> getChauffeurByKeywordByPageable(@RequestParam(name = "dispo") String mc,
													    	  @RequestParam(name = "page") int page,
															  @RequestParam(name = "size") int size);
													    		
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurByLocalityPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ChauffeurDto> getChauffeurByLocalityPageables(@RequestParam("id") Long addId, 
    														 @RequestParam(name = "page") int page,
    														 @RequestParam(name = "size") int size);
    
    @GetMapping(value = APP_ROOT + "/chauffeurs/searchChauffeurByPermisPageables", 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ChauffeurDto> getChauffeurByPermisPageables(
    		@RequestParam("id") Long permisId, 
    		@RequestParam(name = "page") int page,
    		@RequestParam(name = "size") int size);
    
    @DeleteMapping(value = APP_ROOT + "/chauffeurs/delete/{idChauffeur}")
    void delete(@PathVariable("idChauffeur") Long id);



}
