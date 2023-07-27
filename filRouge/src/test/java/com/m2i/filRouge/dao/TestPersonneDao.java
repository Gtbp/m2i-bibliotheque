package com.m2i.filRouge.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.m2i.filRouge.entities.Personne;
import com.m2i.filRouge.idao.IDaoLecteur;
import com.m2i.filRouge.idao.IDaoPersonne;

@SpringBootTest
// @ActiveProfiles({"oracle"})
public class TestPersonneDao {
	
	Logger logger = LoggerFactory.getLogger(TestPersonneDao.class);
	
	@Autowired
	private IDaoLecteur iDaoLecteur;
	
	@Autowired
	private IDaoPersonne iDaoPersonne;

	
	// Tous les tests Personne
	@Test
	public void testCreatePersonne() {
		Personne personneTestCreate = new Personne(null, "prenomTest", "nomTest", "emailTest", 
				"telephoneTest","adresseTest");
		iDaoPersonne.create(personneTestCreate);
		logger.debug("personneTestCreate " + personneTestCreate);
		
		assertTrue(personneTestCreate.getIdPersonne() > 0
				&& personneTestCreate.getPrenom() == "prenomTest"
				&& personneTestCreate.getNom() == "nomTest"
				&& personneTestCreate.getEmail() == "emailTest"
				&& personneTestCreate.getTelephone() == "telephoneTest"
				&& personneTestCreate.getAdresse() == "adresseTest");
	}
	
	@Test
	public void testFindPersonneById() {
		 Personne personneTestFindById = iDaoPersonne.findById((long) 1);
		 logger.debug("personneTestFindById " + personneTestFindById.getIdPersonne());
		 //assertTrue(personneTestFindById.getIdPersonne() == 1);
	}
	 
	@Test
	public void testFindAllPersonnes() {
		 List<Personne> personnes = iDaoPersonne.findAll();
		 assertTrue(personnes.size() > 0);
	}
	 
	@Test
	public void testUpdatePersonne() {
		 Personne personneTestUpdate = iDaoPersonne.findById((long) 1);
		 personneTestUpdate.setPrenom("prenomUpdateTest");
		 iDaoPersonne.update(personneTestUpdate);
		 
		 logger.debug("personneTestUpdate " + personneTestUpdate);
		// assertTrue(personneTestUpdate.getPrenom() == "prenomUpdateTest");
		 assertEquals("prenomUpdateTest", personneTestUpdate.getPrenom());
	}
	 
	/*@Test
	public void testDeletePersonne() {
		 
	}
	
	
	// Tous les tests lecteurs	
	@Test
	public void testCreateLecteur() {
		Personne personneTestCreate = new Personne(null, "T","nom","","","");
		Lecteur lecteur1 = new Lecteur();
		
	}
	
	@Test
	public void testFindLecteurById() {
		 
	}
	 
	@Test
	public void testFindAllLecteurs() {
		 
	}
	 
	@Test
	public void testUpdateLecteur() {
		 
	}
	 
	@Test
	public void testDeleteLecteur() {
		 
	}*/
}


