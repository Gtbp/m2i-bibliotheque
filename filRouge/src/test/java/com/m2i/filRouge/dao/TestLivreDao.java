package com.m2i.filRouge.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
	 public void testCreate() {
		Domaine domaineTest = iDaoDomaine.create(new Domaine(null, "domaineTest", "descriptionTest"));
		Livre livreTest =  iDaoLivre.create(new Livre(null,
				 "titreTest",
				 "auteurTest",
				 "editeurTest",
				 true,
				 EtatLivre.BON_ETAT,
				 domaineTest));
		 
		 assertTrue(livreTest.getTitre()=="titreTest");
	 }
	 
//	 @Test
//	 public void testFindBy() {
//		 
//	 }
//	 
//	 @Test
//	 public void testFindAll() {
//		 
//	 }
//	 
//	 @Test
//	 public void testUpdate() {
//		 
//	 }
//	 
//	 @Test
//	 public void testDelete() {
//		 
//	 }
//	 
	 
}
