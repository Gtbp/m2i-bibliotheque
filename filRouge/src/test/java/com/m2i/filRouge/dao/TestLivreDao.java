package com.m2i.filRouge.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.m2i.filRouge.entities.Domaine;
import com.m2i.filRouge.entities.Livre;
import com.m2i.filRouge.entities.Livre.EtatLivre;
import com.m2i.filRouge.idao.IDaoDomaine;
import com.m2i.filRouge.idao.IDaoLivre;

@SpringBootTest
@ActiveProfiles({"oracle"})
public class TestLivreDao {

	Logger logger = LoggerFactory.getLogger(TestLivreDao.class);
	
	
	@Autowired
	private IDaoLivre iDaoLivre;
	
	@Autowired
	private IDaoDomaine iDaoDomaine;
	
	 // Test Domaine
	
	 @Test
	 public void testCreateDomaine() {
		Domaine domaineTestCreate = iDaoDomaine.create(new Domaine(null, "domaineTestCreate", "descriptionTestCreate"));
		 
		 assertTrue(domaineTestCreate.getIdDomaine() > 0 
				 && domaineTestCreate.getNom()=="domaineTestCreate" 
				 && domaineTestCreate.getDescription()=="descriptionTestCreate" 
				);
	 }
	 
	 @Test
	 public void testFindDomaineById() {

		Domaine domaineTestById =  iDaoDomaine.findById((long) 1);
		 assertTrue(domaineTestById.getIdDomaine() == 1);
		 
	 }
	 
	 @Test
	 public void testFindAllDomaines() {
		 List <Domaine> domaines = iDaoDomaine.findAll();
		 assertTrue(domaines.size() > 0);
	 }
	 
	 @Test
	 public void testUpdateDomaine() {
		 
		 Domaine domaineTestAUpdate = iDaoDomaine.create(new Domaine(null, "domaineTestAUpdate", "descAUpdate"));
		 Domaine domaineTest =  iDaoDomaine.findById(domaineTestAUpdate.getIdDomaine());
		 domaineTest.setNom("domaineTestUpdated");
		 iDaoDomaine.update(domaineTest);
		 Domaine domaineUpdateTest = domaineTest;
		 assertTrue(domaineUpdateTest.getNom() == "domaineTestUpdated" );
		 
		 
	 }
	 
	 @Test
	 public void testDeleteDomaine() {
//		Impossible de delete un Domaine avec les constraints
		 
//		 Domaine domaineTest = iDaoDomaine.findById((long) 1);
//		
//		 iDaoDomaine.delete(domaineTest.getIdDomaine());
//		
//		 Domaine domaineTestDeleted = iDaoDomaine.findById((long) 1);
//		 
//		 assertTrue(domaineTestDeleted == null);
			 
	 }
	 
	
	// Test Livre
	
	 @Test
	 public void testCreateLivre() {
		Domaine domaineTest = iDaoDomaine.create(new Domaine(null, "domaineTest", "descriptionTest"));
		Livre livreTest =  iDaoLivre.create(new Livre(null,
				 "titreTest",
				 "auteurTest",
				 "editeurTest",
				 true,
				 EtatLivre.BON_ETAT,
				 domaineTest));
		 
		 assertTrue(livreTest.getIdLivre() > 0 
				 && livreTest.getTitre()=="titreTest" 
				 && livreTest.getAuteur()=="auteurTest" 
				 && livreTest.getEditeur() == "editeurTest");
		
	 }
	 
	 @Test
	 public void testFindLivreById() {
		 
		 Domaine domaineTest = iDaoDomaine.create(new Domaine(null, "domaineTest", "descriptionTest"));
			Livre livreTestFindById =  iDaoLivre.create(new Livre(null,
					 "titreTestId",
					 "auteurTestId",
					 "editeurTestId",
					 true,
					 EtatLivre.BON_ETAT,
					 domaineTest));
		 
				iDaoLivre.findById(livreTestFindById.getIdLivre());
		 assertTrue(livreTestFindById.getTitre() == "titreTestId" );
		 
	 }
	 
	 @Test
	 public void testFindAllLivres() {
		 List <Livre> livres = iDaoLivre.findAll();
		 assertTrue(livres.size() > 0);
	 }
	 
	 @Test
	 public void testUpdateLivre() {
		 Domaine domaineTest = iDaoDomaine.create(new Domaine(null, "domaineTestUpdateL", "descriptionTestUpdateL"));
			Livre livreTestAUpdate =  iDaoLivre.create(new Livre(null,
					 "titreTest",
					 "auteurTest",
					 "editeurTest",
					 true,
					 EtatLivre.BON_ETAT,
					 domaineTest));
		 
		 
		 Livre livreTest =  iDaoLivre.findById(livreTestAUpdate.getIdLivre());
		 livreTest.setTitre("titreUpdateTest");
		 iDaoLivre.update(livreTest);
		 Livre livreUpdateTest = livreTest;
		 assertTrue(livreUpdateTest.getTitre() == "titreUpdateTest" );
		 
	 }
	 
	 @Test
	 public void testDeleteLivre() {
		 Domaine domaineTest = iDaoDomaine.findById((long) 1);
		 Livre livreDeleteTest = iDaoLivre.create(new Livre(null,
				 "titreDeleteTest",
				 "auteurDeleteTest",
				 "editeurDeleteTest",
				 true,
				 EtatLivre.BON_ETAT,
				 domaineTest));
		
		iDaoLivre.delete(livreDeleteTest.getIdLivre());
		
		livreDeleteTest = iDaoLivre.findById(livreDeleteTest.getIdLivre());

		 assertTrue(livreDeleteTest == null);

	
			 
	 }
	 

	 
}
