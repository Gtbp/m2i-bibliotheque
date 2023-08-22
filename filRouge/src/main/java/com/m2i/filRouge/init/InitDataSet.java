package com.m2i.filRouge.init;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.m2i.filRouge.entities.Administrateur;
import com.m2i.filRouge.entities.Domaine;
import com.m2i.filRouge.entities.Emprunt;
import com.m2i.filRouge.entities.Emprunt.TypesEmprunt;
import com.m2i.filRouge.entities.Lecteur;
import com.m2i.filRouge.entities.Livre;
import com.m2i.filRouge.entities.Livre.EtatLivre;
import com.m2i.filRouge.idao.IDaoAdmin;
import com.m2i.filRouge.idao.IDaoDomaine;
import com.m2i.filRouge.idao.IDaoEmprunt;
import com.m2i.filRouge.idao.IDaoLecteur;
import com.m2i.filRouge.idao.IDaoLivre;

@Profile("init")
@Component
public class InitDataSet {

	 @Autowired
	 private IDaoDomaine iDaoDomaine;
	
	@Autowired
	private IDaoLivre iDaoLivre;
	
	@Autowired
	private IDaoLecteur iDaoLecteur;
	
	 @Autowired
	 private IDaoEmprunt iDaoEmprunt;
	 
	 @Autowired
	 private IDaoAdmin iDaoAdmin;
	 
	 @PostConstruct
	 public void initData() {
		 
		 Domaine domaineAa = iDaoDomaine.save(new Domaine(null, "domaineAa", "descriptionAa"));
		 
		 Livre livreAa = iDaoLivre.save(new Livre(null,
				 "titreAa",
				 "auteurAa",
				 "editeurAa",
				 true,
				 EtatLivre.BON_ETAT,
				 domaineAa));
		 
		 Livre livreBb = iDaoLivre.save(new Livre(null,
				 "titreBb",
				 "auteurBb",
				 "editeurBb",
				 true,
				 EtatLivre.BON_ETAT,
				 domaineAa));
		 
		 
		 Lecteur lecteurAa = iDaoLecteur.save(new Lecteur(null,"Aa", "aa", "aa", "aa", "aa"
				 ));
		 Lecteur lecteurBb = iDaoLecteur.save(new Lecteur(null,"bb", "bb", "bb", "bb", "bb"
				 ));
		 
		 Administrateur adminAa = iDaoAdmin.save(new Administrateur(null, 
				 "initNameAdmin",
				 "initSurNameAdmin",
				 "initEmailAdmin", 
				 "initTelephoneAdmin",
				 "initAdresseAdmin",
				 "initUsernameAdmin",
				 "initPasswordAdmin"));
		 
		 	Date date_debut = new Date();
		 
			Date date_fin = new Date();
			
			
		 Emprunt empruntAa = iDaoEmprunt.save(new Emprunt(null,
				 date_debut,
				 date_fin,
				 TypesEmprunt.EFFECTIF,
				 lecteurAa,
				 livreAa
				 ));
		 
		 livreAa.setDispo(false);
		
		

	 }
}
