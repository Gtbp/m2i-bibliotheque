package com.m2i.filRouge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.filRouge.converter.GenericConverter;
import com.m2i.filRouge.dto.DtoPersonne;
import com.m2i.filRouge.entities.Personne;
import com.m2i.filRouge.service.ServicePersonne;

@RestController
@RequestMapping(value="/api-bibliotheque/personne", headers="Accept=application/json")
public class PersonneRest {
	
	@Autowired
	private ServicePersonne servicePersonne;
	
	@GetMapping("/{idPersonne}")
	public DtoPersonne getPersonneById(@PathVariable("idPersonne") Long idPersonne) {
	    	return servicePersonne.findDtoById(idPersonne);
	}
	
	// book list
	@GetMapping("")
	public List<DtoPersonne> getPersonnes(){
		return servicePersonne.findAllDto();
	}
	
	//exemple de fin d'URL: ./api-biblio/personne
	//appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "idPersonne" : null , "titre" : "titreRest" , "auteur" : "auteurRest" , "editeur" : "editeurRest" , "dispo" : "true" , "etat": "BON_ETAT"  , "domaine" : 1 }
	
	@PostMapping("")
	public DtoPersonne postPersonne(@RequestBody DtoPersonne nouveauPersonne) {
		Personne personne = servicePersonne.save(GenericConverter.map(nouveauPersonne, Personne.class));
		return GenericConverter.map(personne, DtoPersonne.class); //on retourne le personne avec clef primaire auto_incrémentée
	}
	
	
	@PutMapping({"", "/{idPersonne}" })
	public ResponseEntity<?> putPersonne(@RequestBody DtoPersonne dtoPersonne, 
			      @PathVariable(value="idPersonne",required = false ) Long idPersonne) {
		
		    Long idPersonneToUpdate = idPersonne!= null ? idPersonne : dtoPersonne.getIdPersonne();
		    
		    if(!servicePersonne.existById(idPersonneToUpdate)) 

		    	return new ResponseEntity<String>("{ \"err\" : \"Personne not found\"}" ,
 			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
		    if(dtoPersonne.getIdPersonne()==null)
		    	dtoPersonne.setIdPersonne(idPersonneToUpdate);
		    	servicePersonne.save(GenericConverter.map(dtoPersonne, Personne.class));
		    	
			return new ResponseEntity<DtoPersonne>(dtoPersonne , HttpStatus.OK);
	}
	
	@DeleteMapping("/{idPersonne}")
	public ResponseEntity<?> deletePersonne(@PathVariable("idPersonne") Long idPersonne) {
		    servicePersonne.deleteById(idPersonne);
		    return new ResponseEntity<String>("{ \"done\" : \"Personne deleted\"}" ,HttpStatus.OK);
		}
}
