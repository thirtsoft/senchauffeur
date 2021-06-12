package com.chauffeur.controllers.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chauffeur.dto.ChauffeurDto;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface ChauffeurApi {
	    
    @PostMapping(value = APP_ROOT + "/chauffeurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ChauffeurDto> save(@RequestBody ChauffeurDto chauffeurDto);
    
    @PutMapping(value = APP_ROOT + "/chauffeurs/update/{idChauffeur}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ChauffeurDto> update(@PathVariable("idChauffeur") Long id, @RequestBody ChauffeurDto chauffeurDto);

    @GetMapping(value = APP_ROOT + "/chauffeurs/{idChauffeur}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ChauffeurDto> findById(@PathVariable("idChauffeur") Long id);

    @GetMapping(value = APP_ROOT + "/chauffeurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ChauffeurDto> findAll();
   
    @DeleteMapping(value = APP_ROOT + "/chauffeurs/delete/{idChauffeur}")
    void delete(@PathVariable("idChauffeur") Long id);

}
