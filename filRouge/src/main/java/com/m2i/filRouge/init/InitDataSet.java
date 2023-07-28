package com.m2i.filRouge.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.m2i.filRouge.entities.Domaine;
import com.m2i.filRouge.entities.Lecteur;
import com.m2i.filRouge.entities.Livre;
import com.m2i.filRouge.entities.Livre.EtatLivre;
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
	 
	 @PostConstruct
	 public void initData() {
		 
		 Domaine domaineAa = iDaoDomaine.create(new Domaine(null, "domaineAa", "descriptionAa"));
		 
		 Livre livreAa = iDaoLivre.create(new Livre(null,
				 "titreAa",
				 "auteurAa",
				 "editeurAa",
				 true,
				 EtatLivre.BON_ETAT,
				 domaineAa));
		 
		 Livre livreBb = iDaoLivre.create(new Livre(null,
				 "titreBb",
				 "auteurBb",
				 "editeurBb",
				 true,
				 EtatLivre.BON_ETAT,
				 domaineAa));
		 
		 
		 Lecteur lecteurAa = iDaoLecteur.create(new Lecteur(null,"Aa", "aa", "aa", "aa", "aa"
				 ));
		 Lecteur lecteurBb = iDaoLecteur.create(new Lecteur(null,"bb", "bb", "bb", "bb", "bb"
				 ));
		 
		 
		 
		 
	 }
}
