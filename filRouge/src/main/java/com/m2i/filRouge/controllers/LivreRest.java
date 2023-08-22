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
import com.m2i.filRouge.dto.DtoLivre;
import com.m2i.filRouge.entities.Livre;
import com.m2i.filRouge.service.ServiceLivre;

@RestController
@RequestMapping(value="/api-bibliotheque/livre", headers="Accept=application/json")
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST})
public class LivreRest {

	@Autowired
	private ServiceLivre serviceLivre; 
	
	
	// display one book (with ExceptionHandler)
	
	
	@GetMapping("/{idLivre}" )
	public DtoLivre getLivreById(@PathVariable("idLivre") Long idLivre) {
	  return serviceLivre.findDtoById(idLivre);  
	}
	
// display one book without ExceptionHandler	
//	
//	@GetMapping("/{idLivre}" )
//	public ResponseEntity<?> getLivreById(@PathVariable("idLivre") Long idLivre) {
//	    DtoLivre dtoLivre = serviceLivre.findDtoById(idLivre);
//	    if(dtoLivre!=null)
//	    	return new ResponseEntity<DtoLivre>(dtoLivre, HttpStatus.OK);
//	    else
//	    	return new ResponseEntity<String>("{ \"err\" : \"livre not found\"}" ,
//	    			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
//	}
	
	// book list
	@GetMapping("")
	public List<DtoLivre> getLivres(){
		return serviceLivre.findAllDto();
	}
	
	//exemple de fin d'URL: ./api-biblio/livre
	//appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "idLivre" : null , "titre" : "titreRest" , "auteur" : "auteurRest" , "editeur" : "editeurRest" , "dispo" : "true" , "etat": "BON_ETAT"  , "domaine" : 1 }
	
	@PostMapping("")	
	public DtoLivre postLivre(@RequestBody DtoLivre nouveauLivre) {
		Livre livre = serviceLivre.save(GenericConverter.map(nouveauLivre, Livre.class));
		return GenericConverter.map(livre, DtoLivre.class); //on retourne le livre avec clef primaire auto_incrémentée
	}
	
	// Update
	
	
	@PutMapping({"", "/{idLivre}" })
	public ResponseEntity<?> putLivre(@RequestBody DtoLivre dtoLivre , 
			      @PathVariable(value="idLivre",required = false ) Long idLivre) {
		
		    Long idLivreToUpdate = idLivre!=null ? idLivre : dtoLivre.getIdLivre();
		   
		    
		    if(!serviceLivre.existById(idLivreToUpdate))
		    	return new ResponseEntity<String>("{ \"err\" : \"Livre not found\"}" ,
 			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
		    
		    if(dtoLivre.getIdLivre()==null)
		    	dtoLivre.setIdLivre(idLivreToUpdate);
		    serviceLivre.save(GenericConverter.map(dtoLivre, Livre.class));
			return new ResponseEntity<DtoLivre>(dtoLivre , HttpStatus.OK);
	}
	
	
	// Delete
	
	@DeleteMapping("/{idLivre}")
	public ResponseEntity<?> deleteLivre(@PathVariable("idLivre") Long idLivre) {
		    serviceLivre.deleteById(idLivre);
		    return new ResponseEntity<String>("{ \"done\" : \"Livre deleted\"}" ,HttpStatus.OK); 
		    
		}
	
}
