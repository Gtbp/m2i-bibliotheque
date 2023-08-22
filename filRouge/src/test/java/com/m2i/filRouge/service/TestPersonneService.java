package com.m2i.filRouge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.m2i.filRouge.dto.DtoPersonne;
import com.m2i.filRouge.entities.Administrateur;
import com.m2i.filRouge.entities.Lecteur;
import com.m2i.filRouge.entities.Personne;
import com.m2i.filRouge.idao.IDaoAdmin;
import com.m2i.filRouge.idao.IDaoLecteur;
import com.m2i.filRouge.idao.IDaoPersonne;

@SpringBootTest
@ActiveProfiles({"oracle"})
public class TestPersonneService {

	Logger logger = LoggerFactory.getLogger(TestPersonneService.class);

	@Autowired
	private ServicePersonne servicePersonne;

	@Autowired
	private ServiceLecteur serviceLecteur;

	@Autowired
	private ServiceAdmin serviceAdmin;

	// Tests Personne
	@Test
	public void testCreatePersonne() {
		Personne personneTestCreate = new Personne(null, "prenomTest", "nomTest", "emailTest", "telephoneTest",
				"adresseTest");
		servicePersonne.save(personneTestCreate);
		logger.debug("personneTestCreate = " + personneTestCreate.getPrenom());

		assertTrue(personneTestCreate.getIdPersonne() > 0 && personneTestCreate.getPrenom() == "prenomTest"
				&& personneTestCreate.getNom() == "nomTest" && personneTestCreate.getEmail() == "emailTest"
				&& personneTestCreate.getTelephone() == "telephoneTest"
				&& personneTestCreate.getAdresse() == "adresseTest");
	}

	@Test
	public void testFindPersonneById() {
		Personne personneTestFindById = servicePersonne.findById((long) 1);
		logger.debug("personneTestFindById = " + personneTestFindById.getIdPersonne());
		assertTrue(personneTestFindById.getIdPersonne() == 1);
	}

	@Test
	public void testFindAllPersonnes() {
		List<Personne> personnes = servicePersonne.findAll();
		assertTrue(personnes.size() > 0);
	}

	@Test
	public void testUpdatePersonne() {
		Personne personneTestUpdate = servicePersonne.findById((long) 1);
		personneTestUpdate.setPrenom("prenomUpdateTest");
		servicePersonne.save(personneTestUpdate);

		logger.debug("personneTestUpdate = " + personneTestUpdate.getPrenom());
		assertEquals("prenomUpdateTest", personneTestUpdate.getPrenom());
	}

	@Test
	public void testDeletePersonne() {
		Personne personne = new Personne(null, "", "", "", "", "");
		servicePersonne.save(personne);
		Personne personneTestDelete = servicePersonne.findById(personne.getIdPersonne());
		servicePersonne.deleteById(personneTestDelete.getIdPersonne());

		personneTestDelete = servicePersonne.findById(personneTestDelete.getIdPersonne());
		assertTrue(personneTestDelete == null);

	}

	// Tests Lecteur
	@Test
	public void testCreateLecteur() {
		Lecteur lecteurTestCreate = new Lecteur(null, "prenomLecteurTest", "nomLecteurTest", "emailLecteurTest",
				"telephoneLecteurTest", "adresseLecteurTest");
		serviceLecteur.save(lecteurTestCreate);
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
		serviceLecteur.save(lecteurTestId);
		Lecteur lecteurTestFindById = serviceLecteur.findById(lecteurTestId.getIdPersonne());
		logger.debug("lecteurTestFindById = " + lecteurTestFindById.getIdPersonne());
		assertEquals( "prenomLecteurTestId",lecteurTestFindById.getPrenom());

	}

	@Test
	public void testFindAllLecteurs() {
		List<Lecteur> lecteurs = serviceLecteur.findAll();
		serviceLecteur.findAll();
		assertTrue(lecteurs.size() > 0);
	}

	@Test
	public void testUpdateLecteur() {
		Lecteur lecteurTestAUpdate = new Lecteur(null, "prenomLecteurTestUpdate", "nomLecteurTestUpdate", "emailLecteurTestUpdate",
				"telephoneLecteurTestUpdate", "adresseLecteurTestUpdate");
		serviceLecteur.save(lecteurTestAUpdate);
		
		Lecteur lecteurTestUpdate = serviceLecteur.findById(lecteurTestAUpdate.getIdPersonne());
		lecteurTestUpdate.setPrenom("lecteurUpdateTest");
		serviceLecteur.save(lecteurTestUpdate);

		logger.debug("lecteurTestUpdate = " + lecteurTestUpdate.getPrenom());
		assertEquals("lecteurUpdateTest", lecteurTestUpdate.getPrenom());

	}

	@Test
	public void testDeleteLecteur() {
		Lecteur lecteur = new Lecteur(null, "", "", "", "", "");
		serviceLecteur.save(lecteur);
		Lecteur lecteurTestDelete = serviceLecteur.findById(lecteur.getIdPersonne());

		serviceLecteur.deleteById(lecteurTestDelete.getIdPersonne());
		lecteurTestDelete = serviceLecteur.findById(lecteurTestDelete.getIdPersonne());
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
		serviceAdmin.save(adminTestCreate);
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
		serviceAdmin.save(TestFindId);
		
		Administrateur adminTestFindById = serviceAdmin.findById(TestFindId.getIdPersonne());
		assertEquals("usernameAdminTestId", adminTestFindById.getUsername());

	}

	@Test
	public void testFindAllAdmins() {
		List<Administrateur> admins = serviceAdmin.findAll();
		serviceAdmin.findAll();
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
		serviceAdmin.save(adminToUpdate);
		
		Administrateur adminTestUpdate = serviceAdmin.findById(adminToUpdate.getIdPersonne());
		adminTestUpdate.setUsername("usernameAdminTestUpdate");
		serviceAdmin.save(adminTestUpdate);

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
		serviceAdmin.save(admin);
		Administrateur adminTestDelete = serviceAdmin.findById(admin.getIdPersonne());
		
		serviceAdmin.deleteById(adminTestDelete.getIdPersonne());
		adminTestDelete = serviceAdmin.findById(adminTestDelete.getIdPersonne());
		assertTrue(adminTestDelete == null);
		
	}
}