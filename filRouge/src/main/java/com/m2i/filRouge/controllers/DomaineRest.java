package com.m2i.filRouge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.filRouge.converter.GenericConverter;
import com.m2i.filRouge.dto.DtoDomaine;
import com.m2i.filRouge.entities.Domaine;
import com.m2i.filRouge.service.ServiceDomaine;

@RestController
@RequestMapping(value="/api-bibliotheque/domaine", headers="Accept=application/json")
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST})
public class DomaineRest {

	@Autowired
	private ServiceDomaine serviceDomaine; 
	
	
	// display one domaine (with ExceptionHandler)
	
	@GetMapping("/{idDomaine}" )
	public DtoDomaine getDomaineByid(@PathVariable("idDomaine") Long idDomaine) {
	  return serviceDomaine.findDtoById(idDomaine);  
	}
	
	
	// book list
	@GetMapping("")
	public List<DtoDomaine> getDomaines(){
		return serviceDomaine.findAllDto();
	}
	
	//exemple de fin d'URL: ./api-biblio/livre
	//appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "idLivre" : null , "titre" : "titreRest" , "auteur" : "auteurRest" , "editeur" : "editeurRest" , "dispo" : "true" , "etat": "BON_ETAT"  , "domaine" : 1 }
	
	@PostMapping("")	
	public DtoDomaine postDomaine(@RequestBody DtoDomaine nouveauDomaine) {
		Domaine domaine = serviceDomaine.save(GenericConverter.map(nouveauDomaine, Domaine.class));
		return GenericConverter.map(domaine, DtoDomaine.class); //on retourne le livre avec clef primaire auto_incrémentée
	}
	
	// Update
	
	
	@PutMapping({"", "/{idDomaine}" })
	public ResponseEntity<?> putDomaine(@RequestBody DtoDomaine dtoDomaine , 
			      @PathVariable(value="idDomaine",required = false ) Long idDomaine) {
		
		    Long idDomaineToUpdate = idDomaine!=null ? idDomaine : dtoDomaine.getIdDomaine();
		   
		    
		    if(!serviceDomaine.existById(idDomaineToUpdate))
		    	return new ResponseEntity<String>("{ \"err\" : \"Domaine not found\"}" ,
 			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
		    
		    if(dtoDomaine.getIdDomaine()==null)
		    	dtoDomaine.setIdDomaine(idDomaineToUpdate);
		    serviceDomaine.save(GenericConverter.map(dtoDomaine, Domaine.class));
			return new ResponseEntity<DtoDomaine>(dtoDomaine , HttpStatus.OK);
	}
	
	
	// Delete
	
	@DeleteMapping("/{idDomaine}")
	public ResponseEntity<?> deleteDomaine(@PathVariable("idDomaine") Long idDomaine) {
		    serviceDomaine.deleteById(idDomaine);
		    return new ResponseEntity<String>("{ \"done\" : \"Domaine deleted\"}" ,HttpStatus.OK); 
		    
		}
	
}
