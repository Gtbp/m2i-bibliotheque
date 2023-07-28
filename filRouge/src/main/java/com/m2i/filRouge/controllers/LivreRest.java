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

import com.m2i.filRouge.entities.Livre;
import com.m2i.filRouge.idao.IDaoLivre;

@RestController
@RequestMapping(value="/api-bibliotheque/livre", headers="Accept=application/json")
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST})
public class LivreRest {

	@Autowired
	private IDaoLivre iDaoLivre; 
	
	// display one book
	
	@GetMapping("/{idLivre}" )
	public ResponseEntity<?> getLivreById(@PathVariable("idLivre") Long idLivre) {
	    Livre livre = iDaoLivre.findById(idLivre);
	    if(livre!=null)
	    	return new ResponseEntity<Livre>(livre, HttpStatus.OK);
	    else
	    	return new ResponseEntity<String>("{ \"err\" : \"livre not found\"}" ,
	    			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
	}
	
	// book list
	@GetMapping("")
	public List<Livre> getLivres(){
		return iDaoLivre.findAll();
	}
	
	//exemple de fin d'URL: ./api-biblio/livre
	//appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "idLivre" : null , "titre" : "titreRest" , "auteur" : "auteurRest" , "editeur" : "editeurRest" , "dispo" : "true" , "etat": "BON_ETAT"  , "domaine" : 1 }
	
	@PostMapping("")
	public Livre postLivre(@RequestBody Livre nouveauLivre) {
		Livre livre = iDaoLivre.create(nouveauLivre);
		return livre; //on retourne le livre avec clef primaire auto_incrémentée
	}
	
	
	@PutMapping({"", "/{idLivre}" })
	public ResponseEntity<?> putLivre(@RequestBody Livre livre , 
			      @PathVariable(value="idLivre",required = false ) Long idLivre) {
		
		    Long idLivreToUpdate = idLivre!=null ? idLivre : livre.getIdLivre();
		   
		    Livre livreToUpdate = 
		    		idLivreToUpdate!=null ? iDaoLivre.findById(idLivreToUpdate) : null;
		    
		    if(livreToUpdate==null)
		    	return new ResponseEntity<String>("{ \"err\" : \"Livre not found\"}" ,
 			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
		    
		    if(livre.getIdLivre()==null)
		    	livre.setIdLivre(idLivreToUpdate);
		    iDaoLivre.update(livre);
			return new ResponseEntity<Livre>(livre , HttpStatus.OK);
	}
	
	@DeleteMapping("/{idLivre}")
	public ResponseEntity<?> deleteLivre(@PathVariable("idLivre") Long idLivre) {
		    Livre livreToDelete = iDaoLivre.findById(idLivre);
		    if(livreToDelete==null)
		    	return new ResponseEntity<String>("{ \"err\" : \"Livre not found\"}" ,
		    			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
		    
		    iDaoLivre.delete(idLivre);
		    return new ResponseEntity<String>("{ \"done\" : \"Livre deleted\"}" ,HttpStatus.OK); 
		    
		}
	
}
