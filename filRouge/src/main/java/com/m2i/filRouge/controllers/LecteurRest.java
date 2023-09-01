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
import com.m2i.filRouge.dto.DtoLecteur;
import com.m2i.filRouge.dto.DtoPersonne;
import com.m2i.filRouge.entities.Lecteur;
import com.m2i.filRouge.service.ServiceLecteur;

@RestController
@RequestMapping(value="/api-bibliotheque/lecteur", headers="Accept=application/json")
public class LecteurRest {
	
	@Autowired
	private ServiceLecteur serviceLecteur;
	
	@GetMapping("/{idLecteur}")
	public DtoLecteur getLecteurById(@PathVariable("idLecteur") Long idLecteur) {
	    	return serviceLecteur.findDtoById(idLecteur);
	}
	
	// book list
	@GetMapping("")
	public List<DtoLecteur> getLecteurs(){
		return serviceLecteur.findAllDto();
	}
	
	//exemple de fin d'URL: ./api-bibliotheque/lecteur
	//appelé en mode POST avec dans la partie invisible "body" de la requête:
	// { "idPersonne" : null , "titre" : "titreRest" , "auteur" : "auteurRest" , "editeur" : "editeurRest" , "dispo" : "true" , "etat": "BON_ETAT"  , "domaine" : 1 }
	
	@PostMapping("")
	public DtoLecteur postLecteur(@RequestBody DtoLecteur nouveauLecteur) {
		Lecteur lecteur = serviceLecteur.save(GenericConverter.map(nouveauLecteur, Lecteur.class));
		return GenericConverter.map(lecteur, DtoLecteur.class); //on retourne le personne avec clef primaire auto_incrémentée
	}
	
	
	@PutMapping({"", "/{idLecteur}" })
	public ResponseEntity<?> putLecteur(@RequestBody DtoLecteur dtoLecteur, 
			      @PathVariable(value="idLecteur",required = false ) Long idLecteur) {
		
		    Long idLecteurToUpdate = idLecteur!=null ? idLecteur : dtoLecteur.getIdPersonne();
		   
		    if(!serviceLecteur.existById(idLecteurToUpdate))
		    	return new ResponseEntity<String>("{ \"err\" : \"Lecteur not found\"}" ,
 			           HttpStatus.NOT_FOUND); //NOT_FOUND = code http 404
		    
		    if(dtoLecteur.getIdPersonne()==null)
		    	dtoLecteur.setIdPersonne(idLecteurToUpdate);
		    serviceLecteur.save(GenericConverter.map(dtoLecteur, Lecteur.class));
			return new ResponseEntity<DtoPersonne>(dtoLecteur , HttpStatus.OK);
	}
	
	@DeleteMapping("/{idLecteur}")
	public ResponseEntity<?> deleteLecteur(@PathVariable("idLecteur") Long idLecteur) {
		    serviceLecteur.deleteById(idLecteur);
		    return new ResponseEntity<String>("{ \"done\" : \"Lecteur deleted\"}" ,HttpStatus.OK);
		}
}
