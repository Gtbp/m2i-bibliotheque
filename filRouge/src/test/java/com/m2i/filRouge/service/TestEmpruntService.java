package com.m2i.filRouge.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.m2i.filRouge.entities.Emprunt;
import com.m2i.filRouge.entities.Emprunt.TypesEmprunt;
import com.m2i.filRouge.entities.Lecteur;
import com.m2i.filRouge.entities.Livre;
import com.m2i.filRouge.entities.Livre.EtatLivre;
import com.m2i.filRouge.idao.IDaoDomaine;
import com.m2i.filRouge.idao.IDaoEmprunt;
import com.m2i.filRouge.idao.IDaoLecteur;
import com.m2i.filRouge.idao.IDaoLivre;

@SpringBootTest
@ActiveProfiles({"oracle"})
public class TestEmpruntService {

Logger logger = LoggerFactory.getLogger(TestEmpruntService.class);
	
	
	@Autowired
	private ServiceLivre serviceLivre;
	
	@Autowired
	private ServiceLecteur serviceLecteur;
	
	@Autowired
	private ServiceEmprunt serviceEmprunt;
	
	@Autowired
	private ServiceDomaine serviceDomaine;
	
	@Test
	public void testCreateEmprunt() {
		
		// create un livre de test Emprunt
		Livre livreTestEmprunt = new Livre(null,
				"livreTestEmprunt",
				"auteurTestEmprunt",
				"editeurTestEmprunt",
				true,
				EtatLivre.BON_ETAT,
				serviceDomaine.findById((long) 1));
		
		// create un lecteur de test Emprunt
		Lecteur lecteurTestEmprunt = new Lecteur(
				null, 
				"prenomTestEmprunt",
				"nomTestEmprunt",
				"emailTestEmprunt",
				"telephoneTestEmprunt",
				"adresseTestEmprunt");
		
		// create en base
		serviceLivre.save(livreTestEmprunt);
		logger.debug("livre test emprunt : " + livreTestEmprunt.getIdLivre());
		serviceLecteur.save(lecteurTestEmprunt);
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
		
		serviceEmprunt.save(empruntTest);
		livreTestEmprunt.setDispo(false);
		
		
		assertTrue(empruntTest.getIdEmprunt() > 0);
		assertTrue(empruntTest.getLecteur() == lecteurTestEmprunt); 
		assertTrue(empruntTest.getLivre() == livreTestEmprunt);
		assertTrue(empruntTest.getDate_debut() == date_debut);
		assertTrue(empruntTest.getDate_fin() == date_fin);
		
	}
	
	@Test
	public void testFindEmpruntById() {
		Emprunt empruntTestFindById = serviceEmprunt.findById((long) 1);
		assertTrue(empruntTestFindById.getIdEmprunt() == 1);
	}
	
	@Test
	public void testFindAllEmprunts() {
		List <Emprunt> emprunts = serviceEmprunt.findAll();
		assertTrue(emprunts.size() > 0);
		}
	
	@Test
	public void testUpdateEmprunt() {
		Emprunt empruntTest = serviceEmprunt.findById((long) 1);
		empruntTest.setType(TypesEmprunt.RESERVATION);
		serviceEmprunt.save(empruntTest);
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
				serviceDomaine.findById((long) 1));
		
		serviceLivre.save(livreTestDeleteEmprunt);
		
		Lecteur lecteurTestDeleteEmprunt = serviceLecteur.findById((long) 1);
		Date date_debut = new Date();
		Date date_fin = new Date();
		
		Emprunt empruntDeleteTest = new Emprunt(null,
				date_debut,
				date_fin,
				TypesEmprunt.EFFECTIF,
				lecteurTestDeleteEmprunt,
				livreTestDeleteEmprunt);
		serviceEmprunt.save(empruntDeleteTest);
		logger.debug("emprunt a delete avant delete "+ empruntDeleteTest.getIdEmprunt());
		serviceEmprunt.deleteById(empruntDeleteTest.getIdEmprunt());
		
		empruntDeleteTest = serviceEmprunt.findById(empruntDeleteTest.getIdEmprunt());
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
