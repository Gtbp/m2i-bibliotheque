package com.m2i.filRouge.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.m2i.filRouge.entities.Domaine;
import com.m2i.filRouge.entities.Livre;
import com.m2i.filRouge.entities.Livre.EtatLivre;
import com.m2i.filRouge.idao.IDaoDomaine;
import com.m2i.filRouge.idao.IDaoLivre;

@SpringBootTest
public class TestLivreDao {

	Logger logger = LoggerFactory.getLogger(TestLivreDao.class);
	
	@Autowired
	private IDaoLivre iDaoLivre;
	
	@Autowired
	private IDaoDomaine iDaoDomaine;
	
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

		Livre livreTestFindById =  iDaoLivre.findById((long) 1);
		 assertTrue(livreTestFindById.getIdLivre() == 1);
		 
	 }
	 
	 @Test
	 public void testFindAllLivres() {
		 List <Livre> livres = iDaoLivre.findAll();
		 assertTrue(livres.size() > 0);
	 }
	 
	 @Test
	 public void testUpdateLivre() {
//		 Livre livreTest =  iDaoLivre.findById((long) 1);
//		 livreTest.setTitre("titreUpdateTest");
//		 iDaoLivre.update(livreTest);
//		 Livre livreUpdateTest = livreTest;
//		 System.out.println(livreUpdateTest);
//		 assertTrue(livreUpdateTest.getTitre() == "titreUpdateTest" );
//		 
		 
	 }
	 
	 @Test
	 public void testDelete() {
//		 Domaine domaineTest = iDaoDomaine.findById((long) 1);
//		 Livre livreDeleteTest = iDaoLivre.create(new Livre(null,
//				 "titreDeleteTest",
//				 "auteurDeleteTest",
//				 "editeurDeleteTest",
//				 true,
//				 EtatLivre.BON_ETAT,
//				 domaineTest));
//				 
//		System.out.println("sysout avant delete" + livreDeleteTest.getTitre());		 
//		iDaoLivre.delete(livreDeleteTest.getIdLivre());
//		
//		Livre livre1 = null;
//		Optional <Livre> optionalLivre = iDaoLivre.findById(livreDeleteTest.getIdLivre());
//		if(optionalLivre.isPresent()) {
//			livre1 = optionalLivre.get();
//			
//		}
//		 assertTrue(livre1.getAuteur() == null );
//		

	     
		 
	 }
	 
	 
}
