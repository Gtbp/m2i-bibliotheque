package com.m2i.filRouge.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.m2i.filRouge.entities.Emprunt;
import com.m2i.filRouge.entities.Emprunt.TypesEmprunt;
import com.m2i.filRouge.entities.Lecteur;
import com.m2i.filRouge.entities.Livre;
import com.m2i.filRouge.entities.Livre.EtatLivre;
import com.m2i.filRouge.idao.IDaoDomaine;
import com.m2i.filRouge.idao.IDaoEmprunt;
import com.m2i.filRouge.idao.IDaoLecteur;
import com.m2i.filRouge.idao.IDaoLivre;
import com.m2i.filRouge.idao.dao.DaoEmprunt;

@SpringBootTest
public class TestEmpruntDao {

Logger logger = LoggerFactory.getLogger(TestEmpruntDao.class);
	
	
	@Autowired
	private IDaoLivre iDaoLivre;
	
	@Autowired
	private IDaoLecteur iDaoLecteur;
	
	@Autowired
	private IDaoEmprunt iDaoEmprunt;
	
	@Autowired
	private IDaoDomaine iDaoDomaine;
	
	@Test
	public void testCreateEmprunt() {
		
		// create un livre de test Emprunt
		Livre livreTestEmprunt = new Livre(null,
				"livreTestEmprunt",
				"auteurTestEmprunt",
				"editeurTestEmprunt",
				true,
				EtatLivre.BON_ETAT,
				iDaoDomaine.findById((long) 1));
		
		// create un lecteur de test Emprunt
		Lecteur lecteurTestEmprunt = new Lecteur(
				null, 
				"prenomTestEmprunt",
				"nomTestEmprunt",
				"emailTestEmprunt",
				"telephoneTestEmprunt",
				"adresseTestEmprunt");
		
		// create en base
		iDaoLivre.create(livreTestEmprunt);
		logger.debug("livre test emprunt : " + livreTestEmprunt.getIdLivre());
		iDaoLecteur.create(lecteurTestEmprunt);
		logger.debug("livrelecteur  emprunt : " + lecteurTestEmprunt.getIdPersonne());
		
		// create emprunt 
	
		Date date_debut = new Date();
		Date date_fin = new Date();
		
		Emprunt empruntTest = new Emprunt(null,
				date_debut,
				date_fin,
				TypesEmprunt.EFFECTIF,
				lecteurTestEmprunt,
				livreTestEmprunt);
		
		iDaoEmprunt.create(empruntTest);
		livreTestEmprunt.setDispo(false);
		
		
		assertTrue(empruntTest.getIdEmprunt() > 0);
		assertTrue(empruntTest.getLecteur() == lecteurTestEmprunt); 
		assertTrue(empruntTest.getLivre() == livreTestEmprunt);
		assertTrue(empruntTest.getDate_debut() == date_debut);
		assertTrue(empruntTest.getDate_fin() == date_fin);
		
	}
	
	@Test
	public void testFindEmpruntById() {
		Emprunt empruntTestFindById = iDaoEmprunt.findById((long) 1);
		assertTrue(empruntTestFindById.getIdEmprunt() == 1);
	}
	
	@Test
	public void testFindAllEmprunts() {
		List <Emprunt> emprunts = iDaoEmprunt.findAll();
		assertTrue(emprunts.size() > 0);
		}
	
	@Test
	public void testUpdateEmprunt() {
		Emprunt empruntTest = iDaoEmprunt.findById((long) 1);
		empruntTest.setType(TypesEmprunt.RESERVATION);
		iDaoEmprunt.update(empruntTest);
		Emprunt empruntUpdateTest = empruntTest;
		assertTrue(empruntUpdateTest.getType() == TypesEmprunt.RESERVATION);
		
		
	}
	@Test
	public void testDeleteEmprunt() {
		
		Livre livreTestDeleteEmprunt = new Livre(null,
				"livreTestDeleteEmprunt",
				"auteurTestEmprunt",
				"editeurTestEmprunt",
				true,
				EtatLivre.BON_ETAT,
				iDaoDomaine.findById((long) 1));
		
		iDaoLivre.create(livreTestDeleteEmprunt);
		
		Lecteur lecteurTestDeleteEmprunt = iDaoLecteur.findById((long) 1);
		Date date_debut = new Date();
		Date date_fin = new Date();
		
		Emprunt empruntDeleteTest = new Emprunt(null,
				date_debut,
				date_fin,
				TypesEmprunt.EFFECTIF,
				lecteurTestDeleteEmprunt,
				livreTestDeleteEmprunt);
		iDaoEmprunt.create(empruntDeleteTest);
		logger.debug("emprunt a delete avant delete "+ empruntDeleteTest.getIdEmprunt());
		iDaoEmprunt.delete(empruntDeleteTest.getIdEmprunt());
		
		empruntDeleteTest = iDaoEmprunt.findById(empruntDeleteTest.getIdEmprunt());
		assertTrue(empruntDeleteTest == null);
		
	}
	
	@Test
	public void testProlonger() {
		
//		Emprunt emprunt = iDaoEmprunt.findById((long) 2);
//		
//		IDaoEmprunt daoEmprunt = new DaoEmprunt();
//		
//		Date newDate_fin = new Date();
//		Calendar c = Calendar.getInstance(); 
//		c.setTime(newDate_fin); 
//		c.add(Calendar.DATE, 1);
//		newDate_fin = c.getTime();
//		
//		daoEmprunt.prolonger((long) 2, newDate_fin);
//		
//		assertTrue(emprunt.getDate_fin() == newDate_fin);
	}
	
	
}
