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

import com.m2i.filRouge.entities.Emprunt;
import com.m2i.filRouge.idao.IDaoEmprunt;

@RestController
@RequestMapping(value="/api-bibliotheque/emprunt", headers="Accept=application/json")
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST})
public class EmpruntRest {

	@Autowired
	private IDaoEmprunt iDaoEmprunt; 
	
	// display one book
	
	@GetMapping("/{idEmprunt}" )
	public ResponseEntity<?> getEmpruntById(@PathVariable("idEmprunt") Long idEmprunt) {
	    Emprunt emprunt = iDaoEmprunt.findById(idEmprunt).orElse(null);
	    if(emprunt!=null)
	    	return new ResponseEntity<Emprunt>(emprunt, HttpStatus.OK);
	    else
	    	return new ResponseEntity<String>("{ \"err\" : \"emprunt not found\"}" ,
	    			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
	}
	
	// book list
	@GetMapping("")
	public List<Emprunt> getEmprunts(){
		return iDaoEmprunt.findAll();
	}
	
	//exemple de fin d'URL: ./api-biblio/emprunt
	//appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "idEmprunt" : null , "titre" : "titreRest" , "auteur" : "auteurRest" , "editeur" : "editeurRest" , "dispo" : "true" , "etat": "BON_ETAT"  , "domaine" : 1 }
	
	@PostMapping("")
	public Emprunt postEmprunt(@RequestBody Emprunt nouveauEmprunt) {
		Emprunt emprunt = iDaoEmprunt.save(nouveauEmprunt);
		return emprunt; //on retourne le emprunt avec clef primaire auto_incrémentée
	}
	
	
	@PutMapping({"", "/{idEmprunt}" })
	public ResponseEntity<?> putEmprunt(@RequestBody Emprunt emprunt , 
			      @PathVariable(value="idEmprunt",required = false ) Long idEmprunt) {
		
		    Long idEmpruntToUpdate = idEmprunt!=null ? idEmprunt : emprunt.getIdEmprunt();
		   
		    Emprunt empruntToUpdate = 
		    		idEmpruntToUpdate!=null ? iDaoEmprunt.findById(idEmpruntToUpdate).orElse(null) : null;
		    
		    if(empruntToUpdate==null)
		    	return new ResponseEntity<String>("{ \"err\" : \"Emprunt not found\"}" ,
 			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
		    
		    if(emprunt.getIdEmprunt()==null)
		    	emprunt.setIdEmprunt(idEmpruntToUpdate);
		    iDaoEmprunt.save(emprunt);
			return new ResponseEntity<Emprunt>(emprunt , HttpStatus.OK);
	}
	
	@DeleteMapping("/{idEmprunt}")
	public ResponseEntity<?> deleteEmprunt(@PathVariable("idEmprunt") Long idEmprunt) {
		    Emprunt empruntToDelete = iDaoEmprunt.findById(idEmprunt).orElse(null);
		    if(empruntToDelete==null)
		    	return new ResponseEntity<String>("{ \"err\" : \"Emprunt not found\"}" ,
		    			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
		    
		    iDaoEmprunt.deleteById(idEmprunt);
		    return new ResponseEntity<String>("{ \"done\" : \"Emprunt deleted\"}" ,HttpStatus.OK); 
		    
		}
	
}
