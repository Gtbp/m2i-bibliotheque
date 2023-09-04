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
import com.m2i.filRouge.dto.DtoEmprunt;
import com.m2i.filRouge.entities.Emprunt;
import com.m2i.filRouge.entities.Livre;
import com.m2i.filRouge.service.ServiceEmprunt;

@RestController
@RequestMapping(value="/api-bibliotheque/emprunt", headers="Accept=application/json")
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST})
public class EmpruntRest {

	@Autowired
	private ServiceEmprunt serviceEmprunt; 
	

	
	// display one emprunt
	
	@GetMapping("/{idEmprunt}" )
	public DtoEmprunt getEmpruntById(@PathVariable("idEmprunt") Long idEmprunt) {
	   return serviceEmprunt.findDtoById(idEmprunt);
	   }
	
	// emprunt list
	@GetMapping("")
	public List<DtoEmprunt> getEmprunts(){
		return serviceEmprunt.findAllDto();
	}
	
	//exemple de fin d'URL: ./api-biblio/emprunt
	//appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "idEmprunt" : null , "titre" : "titreRest" , "auteur" : "auteurRest" , "editeur" : "editeurRest" , "dispo" : "true" , "etat": "BON_ETAT"  , "domaine" : 1 }
	
	// Create
	
	@PostMapping("")
	
	public DtoEmprunt postEmprunt(@RequestBody DtoEmprunt nouveauEmprunt) {
		DtoEmprunt emprunt = serviceEmprunt.saveOrUpdateDtoEmprunt(nouveauEmprunt);
		Long idEmprunt = emprunt.getIdEmprunt();
		
		Livre livre =serviceEmprunt.findLivreByIdEmprunt(idEmprunt);
		livre.setDispo(false);
		return GenericConverter.map(emprunt, DtoEmprunt.class); //on retourne le emprunt avec clef primaire auto_incrémentée
		
		 
	}
	
	// Update
	
	@PutMapping({"", "/{idEmprunt}" })
	public ResponseEntity<?> putEmprunt(@RequestBody DtoEmprunt dtoEmprunt , 
			      @PathVariable(value="idEmprunt",required = false ) Long idEmprunt) {
		
		    Long idEmpruntToUpdate = idEmprunt!=null ? idEmprunt : dtoEmprunt.getIdEmprunt();
		   
		    if(!serviceEmprunt.existById(idEmpruntToUpdate))
		    	return new ResponseEntity<String>("{ \"err\" : \"Emprunt not found\"}" ,
 			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
		    
		    if(dtoEmprunt.getIdEmprunt()==null)
		    	dtoEmprunt.setIdEmprunt(idEmpruntToUpdate);
		    serviceEmprunt.save(GenericConverter.map(dtoEmprunt, Emprunt.class));
			return new ResponseEntity<DtoEmprunt>(dtoEmprunt , HttpStatus.OK);
	}
	
	// Delete
	
	@DeleteMapping("/{idEmprunt}")
	public ResponseEntity<?> deleteEmprunt(@PathVariable("idEmprunt") Long idEmprunt) {
		   serviceEmprunt.deleteById(idEmprunt);
		    return new ResponseEntity<String>("{ \"done\" : \"Emprunt deleted\"}" ,HttpStatus.OK); 
		    
		}
	
}
