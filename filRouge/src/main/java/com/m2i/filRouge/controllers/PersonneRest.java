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

import com.m2i.filRouge.entities.Personne;
import com.m2i.filRouge.idao.IDaoPersonne;

@RestController
@RequestMapping(value="/api-bibliotheque/personne", headers="Accept=application/json")
public class PersonneRest {
	
	@Autowired
	private IDaoPersonne iDaoPersonne;
	
	@GetMapping("/{idPersonne}")
	public ResponseEntity<?> getPersonneById(@PathVariable("idPersonne") Long idPersonne) {
	    Personne personne = iDaoPersonne.findById(idPersonne);
	    if(personne!=null)
	    	return new ResponseEntity<Personne>(personne, HttpStatus.OK);
	    else
	    	return new ResponseEntity<String>("{ \"err\" : \"personne not found\"}" ,
	    			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
	}
	
	// book list
	@GetMapping("")
	public List<Personne> getPersonnes(){
		return iDaoPersonne.findAll();
	}
	
	//exemple de fin d'URL: ./api-biblio/personne
	//appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "idPersonne" : null , "titre" : "titreRest" , "auteur" : "auteurRest" , "editeur" : "editeurRest" , "dispo" : "true" , "etat": "BON_ETAT"  , "domaine" : 1 }
	
	@PostMapping("")
	public Personne postPersonne(@RequestBody Personne nouveauPersonne) {
		Personne personne = iDaoPersonne.create(nouveauPersonne);
		return personne; //on retourne le personne avec clef primaire auto_incrémentée
	}
	
	
	@PutMapping({"", "/{idPersonne}" })
	public ResponseEntity<?> putPersonne(@RequestBody Personne personne , 
			      @PathVariable(value="idPersonne",required = false ) Long idPersonne) {
		
		    Long idPersonneToUpdate = idPersonne!=null ? idPersonne : personne.getIdPersonne();
		   
		    Personne personneToUpdate = 
		    		idPersonneToUpdate!=null ? iDaoPersonne.findById(idPersonneToUpdate) : null;
		    
		    if(personneToUpdate==null)
		    	return new ResponseEntity<String>("{ \"err\" : \"Personne not found\"}" ,
 			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
		    
		    if(personne.getIdPersonne()==null)
		    	personne.setIdPersonne(idPersonneToUpdate);
		    iDaoPersonne.update(personne);
			return new ResponseEntity<Personne>(personne , HttpStatus.OK);
	}
	
	@DeleteMapping("/{idPersonne}")
	public ResponseEntity<?> deletePersonne(@PathVariable("idPersonne") Long idPersonne) {
		    Personne personneToDelete = iDaoPersonne.findById(idPersonne);
		    if(personneToDelete==null)
		    	return new ResponseEntity<String>("{ \"err\" : \"Personne not found\"}" ,
		    			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
		    
		    iDaoPersonne.delete(idPersonne);
		    return new ResponseEntity<String>("{ \"done\" : \"Personne deleted\"}" ,HttpStatus.OK); 
		    
		}
}
