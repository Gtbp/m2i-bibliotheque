package com.m2i.filRouge.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.m2i.filRouge.entities.Administrateur;
import com.m2i.filRouge.entities.Lecteur;
import com.m2i.filRouge.entities.Personne;
import com.m2i.filRouge.idao.IDaoAdmin;
import com.m2i.filRouge.idao.IDaoLecteur;
import com.m2i.filRouge.idao.IDaoPersonne;

@SpringBootTest
// @ActiveProfiles({"oracle"})
public class TestPersonneDao {

	Logger logger = LoggerFactory.getLogger(TestPersonneDao.class);

	@Autowired
	private IDaoPersonne iDaoPersonne;

	@Autowired
	private IDaoLecteur iDaoLecteur;

	@Autowired
	private IDaoAdmin iDaoAdmin;

	// Tests Personne
	@Test
	public void testCreatePersonne() {
		Personne personneTestCreate = new Personne(null, "prenomTest", "nomTest", "emailTest", "telephoneTest",
				"adresseTest");
		iDaoPersonne.create(personneTestCreate);
		logger.debug("personneTestCreate = " + personneTestCreate.getPrenom());

		assertTrue(personneTestCreate.getIdPersonne() > 0 && personneTestCreate.getPrenom() == "prenomTest"
				&& personneTestCreate.getNom() == "nomTest" && personneTestCreate.getEmail() == "emailTest"
				&& personneTestCreate.getTelephone() == "telephoneTest"
				&& personneTestCreate.getAdresse() == "adresseTest");
	}

	@Test
	public void testFindPersonneById() {
		Personne personneTestFindById = iDaoPersonne.findById((long) 1);
		logger.debug("personneTestFindById = " + personneTestFindById.getIdPersonne());
		assertTrue(personneTestFindById.getIdPersonne() == 1);
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

		logger.debug("personneTestUpdate = " + personneTestUpdate.getPrenom());
		assertEquals("prenomUpdateTest", personneTestUpdate.getPrenom());
	}

	@Test
	public void testDeletePersonne() {
		Personne personne = new Personne(null, "", "", "", "", "");
		Personne personneTestDelete = iDaoPersonne.findById((long) 1);
		iDaoPersonne.delete(personneTestDelete.getIdPersonne());

		personneTestDelete = iDaoPersonne.findById(personneTestDelete.getIdPersonne());
		assertTrue(personneTestDelete == null);

	}

	// Tests Lecteur
	@Test
	public void testCreateLecteur() {
		Lecteur lecteurTestCreate = new Lecteur(null, "prenomLecteurTest", "nomLecteurTest", "emailLecteurTest",
				"telephoneLecteurTest", "adresseLecteurTest");
		iDaoLecteur.create(lecteurTestCreate);
		logger.debug("lecteurTestCreate = " + lecteurTestCreate.getPrenom());

		assertTrue(lecteurTestCreate.getIdPersonne() > 0 && lecteurTestCreate.getPrenom() == "prenomLecteurTest"
				&& lecteurTestCreate.getNom() == "nomLecteurTest" && lecteurTestCreate.getEmail() == "emailLecteurTest"
				&& lecteurTestCreate.getTelephone() == "telephoneLecteurTest"
				&& lecteurTestCreate.getAdresse() == "adresseLecteurTest");

	}

	@Test
	public void testFindLecteurById() {
		Lecteur lecteurTestId = new Lecteur(null, "prenomLecteurTestId", "nomLecteurTestId", "emailLecteurTestId",
				"telephoneLecteurTestId", "adresseLecteurTestId");
		iDaoLecteur.create(lecteurTestId);
		Lecteur lecteurTestFindById = iDaoLecteur.findById(lecteurTestId.getIdPersonne());
		logger.debug("lecteurTestFindById = " + lecteurTestFindById.getIdPersonne());
		assertEquals( "prenomLecteurTestId",lecteurTestFindById.getPrenom());

	}

	@Test
	public void testFindAllLecteurs() {
		List<Lecteur> lecteurs = iDaoLecteur.findAll();
		iDaoLecteur.findAll();
		assertTrue(lecteurs.size() > 0);
	}

	@Test
	public void testUpdateLecteur() {
		Lecteur lecteurTestAUpdate = new Lecteur(null, "prenomLecteurTestUpdate", "nomLecteurTestUpdate", "emailLecteurTestUpdate",
				"telephoneLecteurTestUpdate", "adresseLecteurTestUpdate");
		iDaoLecteur.create(lecteurTestAUpdate);
		
		Lecteur lecteurTestUpdate = iDaoLecteur.findById(lecteurTestAUpdate.getIdPersonne());
		lecteurTestUpdate.setPrenom("lecteurUpdateTest");
		iDaoLecteur.update(lecteurTestUpdate);

		logger.debug("lecteurTestUpdate = " + lecteurTestUpdate.getPrenom());
		assertEquals("lecteurUpdateTest", lecteurTestUpdate.getPrenom());

	}

	@Test
	public void testDeleteLecteur() {
		Lecteur lecteur = new Lecteur(null, "", "", "", "", "");
		iDaoLecteur.create(lecteur);
		Lecteur lecteurTestDelete = iDaoLecteur.findById(lecteur.getIdPersonne());

		iDaoLecteur.delete(lecteurTestDelete.getIdPersonne());
		lecteurTestDelete = iDaoLecteur.findById(lecteurTestDelete.getIdPersonne());
		assertTrue(lecteurTestDelete == null);

	}

	// Tests Administrateur
	@Test
	public void testCreateAdmin() {

		Administrateur adminTestCreate = new Administrateur(null, "prenomAdminTest",
				"nomAdminTest",
				"emailAdminTest",
				"telephoneAdminTest",
				"adresseAdminTest",
				"usernameAdminTest",
				"passwordAdminTest");
		iDaoAdmin.create(adminTestCreate);
		logger.debug("adminTestCreate = " + adminTestCreate.getPrenom());

		assertTrue(adminTestCreate.getIdPersonne() > 0 && adminTestCreate.getUsername() == "usernameAdminTest"
				&& adminTestCreate.getPassword() == "passwordAdminTest");
	}

	@Test
	public void testFindAdminById() {

		Administrateur TestFindId = new Administrateur(null, "prenomAdminTest",
				"nomAdminTestId",
				"emailAdminTestId",
				"telephoneAdminTestId",
				"adresseAdminTestId",
				"usernameAdminTestId",
				"passwordAdminTestId");
		iDaoAdmin.create(TestFindId);
		
		Administrateur adminTestFindById = iDaoAdmin.findById(TestFindId.getIdPersonne());
		assertEquals("usernameAdminTestId", adminTestFindById.getUsername());

	}

	@Test
	public void testFindAllAdmins() {
		List<Administrateur> admins = iDaoAdmin.findAll();
		iDaoAdmin.findAll();
		assertTrue(admins.size() > 0);
	}

	@Test
	public void testUpdateAdmin() {
		Administrateur adminToUpdate = new Administrateur(null, "prenomAdminTestUpdate",
				"nomAdminTestUpdate",
				"emailAdminTestUpdate",
				"telephoneAdminTestUpdate",
				"adresseAdminTestUpdate",
				"usernameAdminTestUpdate",
				"passwordAdminTestUpdate");
		iDaoAdmin.create(adminToUpdate);
		
		Administrateur adminTestUpdate = iDaoAdmin.findById(adminToUpdate.getIdPersonne());
		adminTestUpdate.setUsername("usernameAdminTestUpdate");
		iDaoAdmin.update(adminTestUpdate);

		logger.debug("adminTestUpdate = " + adminTestUpdate.getUsername());
		assertEquals("usernameAdminTestUpdate", adminTestUpdate.getUsername());

	}

	@Test
	public void testDeleteAdmin() {
		Administrateur admin = new Administrateur(null, "prenomAdminTest",
				"nomAdminTest",
				"emailAdminTest",
				"telephoneAdminTest",
				"adresseAdminTest",
				"usernameAdminTest",
				"passwordAdminTest");
		iDaoAdmin.create(admin);
		Administrateur adminTestDelete = iDaoAdmin.findById(admin.getIdPersonne());
		
		iDaoAdmin.delete(adminTestDelete.getIdPersonne());
		adminTestDelete = iDaoAdmin.findById(adminTestDelete.getIdPersonne());
		assertTrue(adminTestDelete == null);
		
	}
}