package com.m2i.filRouge.init;

import java.text.SimpleDateFormat;
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
		 
		 Domaine poesie = iDaoDomaine.save(new Domaine(null, "Poésie", "Expression, émotion, vers"));
		 Domaine histoire = iDaoDomaine.save(new Domaine(null, "Histoire", "Evènements, passé, narration"));
		 Domaine scienceFiction = iDaoDomaine.save(new Domaine(null, "Science-Fiction", "Futur, imagination, technologie"));
		 Domaine fantasy = iDaoDomaine.save(new Domaine(null, "Fantasy", "Magie, monde, aventure"));
		 Domaine policier = iDaoDomaine.save(new Domaine(null, "Policier", "Enquête, mystère, crime"));
		 Domaine theatre = iDaoDomaine.save(new Domaine(null, "Théâtre", "Drame, dialogue, scène"));
		 
		 
		 Livre poesie1 = iDaoLivre.save(new Livre(null,"Les Contemplations", "Victor Hugo","Lgf", true, EtatLivre.BON_ETAT, poesie));
		 Livre poesie2 = iDaoLivre.save(new Livre(null,"Chaque grain d'amour", "Tan Elbaz","Broché", true, EtatLivre.BON_ETAT, poesie));
		 Livre poesie3 = iDaoLivre.save(new Livre(null,"Les échos du coeur", "Luna Osho","Gallimard", true, EtatLivre.BON_ETAT, poesie));
		 iDaoLivre.save(new Livre(null,"Les Châtiments", "Victor Hugo","Lgf", true, EtatLivre.HORS_SERVICE, poesie));
		 iDaoLivre.save(new Livre(null,"La fin de Satan", "Victor Hugo","Lgf", true, EtatLivre.BON_ETAT, poesie));
		 
		 iDaoLivre.save(new Livre(null, "Guerre et Paix", "Léon Tolstoï", "Gallimard", true, EtatLivre.ABIME, histoire));
		 iDaoLivre.save(new Livre(null, "Entrez dans l'Histoire", "Lorent Deutsch", "Broché", true, EtatLivre.ABIME, histoire));
		 iDaoLivre.save(new Livre(null, "Histoires de guerre", "Mamytwink", "Broché", true, EtatLivre.BON_ETAT, histoire));
		 iDaoLivre.save(new Livre(null, "Découvrir, comprende De Gaulle", "Alain Kerhervé", "Broché", true, EtatLivre.BON_ETAT, histoire));
		 iDaoLivre.save(new Livre(null, "1180-1328: L'âge d'or capétien", "Jean-Christophe Cassard", "Folio", true, EtatLivre.BON_ETAT, histoire));
		 
		 iDaoLivre.save(new Livre(null, "Arcania", "Lucas Bertolami", "Librinova", true, EtatLivre.ABIME, scienceFiction));
		 iDaoLivre.save(new Livre(null, "Le Problème à trois corps", "Cixin Liu", "Babel", true, EtatLivre.ABIME, scienceFiction));
		 iDaoLivre.save(new Livre(null, "Dark Gravity", "Christian Clément", "Broché", false, EtatLivre.BON_ETAT, scienceFiction));
		 iDaoLivre.save(new Livre(null, "Extinction", "Guy-Roger Duvert", "Broché", true, EtatLivre.BON_ETAT, scienceFiction));
		 iDaoLivre.save(new Livre(null, "La Boîte de Pandore", "Bernard Werber", "Le Livre de Poche", true, EtatLivre.BON_ETAT, scienceFiction));
		 
		 iDaoLivre.save(new Livre(null, "Un destin à tracer", "Opale Pion", "Kindle", true, EtatLivre.BON_ETAT, fantasy));
		 iDaoLivre.save(new Livre(null, "Dragheine: la cité contre la Vague", "Lucie Rose", "Le Livre de Poche", true, EtatLivre.BON_ETAT, fantasy));
		 iDaoLivre.save(new Livre(null, "Trois Royaumes", "Sophie Winter", "Broché", true, EtatLivre.BON_ETAT, fantasy));
		 iDaoLivre.save(new Livre(null, "Cendres et Sortilèges", "Kim Richardson", "FablePRINT", false, EtatLivre.ABIME, fantasy));
		 iDaoLivre.save(new Livre(null, "Le défi de la couronne", "Jupiter Phaeton", "Broché", false, EtatLivre.ABIME, fantasy));
		 iDaoLivre.save(new Livre(null, "Le Sang et la cendre", "Jennifer L. Armentrout", "Relié", true, EtatLivre.BON_ETAT, fantasy));
		 iDaoLivre.save(new Livre(null, "Eléria: l'éveil du feu", "Laura Esquine", "Broché", true, EtatLivre.BON_ETAT, fantasy));
		 
		 iDaoLivre.save(new Livre(null, "Meurtre en Mésopotamie", "Agatha Christie", "Relié", false, EtatLivre.BON_ETAT, policier));
		 iDaoLivre.save(new Livre(null, "Un hiver meurtrier", "Franck Esposito", "Broché", true, EtatLivre.BON_ETAT, policier));
		 iDaoLivre.save(new Livre(null, "Par accident", "Harlan Coben", "Pocket", false, EtatLivre.BON_ETAT, policier));
		 iDaoLivre.save(new Livre(null, "On se reverra", "Lisa Jewell", "Hauteville", true, EtatLivre.ABIME, policier));
		 iDaoLivre.save(new Livre(null, "L'empathie", "Antoine Renand", "Le Livre de Poche", true, EtatLivre.ABIME, policier));
		 iDaoLivre.save(new Livre(null, "Les oubliés", "John Grisham", "Broché", true, EtatLivre.BON_ETAT, policier));
		 
		 iDaoLivre.save(new Livre(null, "Le malade imaginaire", "Molière", "Broché", false, EtatLivre.BON_ETAT, theatre));
		 iDaoLivre.save(new Livre(null, "Le tartuffe", "Molière", "Broché", true, EtatLivre.BON_ETAT, theatre));
		 iDaoLivre.save(new Livre(null, "Le Mariage de Figaro", "Beaumarchais", "Relié", true, EtatLivre.BON_ETAT, theatre));
		 iDaoLivre.save(new Livre(null, "Le barbier de Séville", "Beaumarchais", "Poche", true, EtatLivre.BON_ETAT, theatre));
		 
		 
		 Lecteur lecteurAa = iDaoLecteur.save(new Lecteur(null,"Francois", "Feldman", "ffeldam@yahoo.com", "0645329574", "Clamart"));
		 iDaoLecteur.save(new Lecteur(null,"Daniel", "Balavoine", "danielbalavoine@outlook.fr", "0687984125", "Charenton"));
		 iDaoLecteur.save(new Lecteur(null,"Eric", "Faure", "faureeric@hotmail.fr", "0642223876", "Versailles"));
		 iDaoLecteur.save(new Lecteur(null,"Stephane", "Verrouil", "stephaneV@netcourrier.com", "0659524168", "Biarritz"));
		 iDaoLecteur.save(new Lecteur(null,"Maxime", "Larmand", "larmand.maxime@hotmail.fr", "0692568415", "Chartres"));
		 iDaoLecteur.save(new Lecteur(null,"Laurence", "Boulle", "laurenceboule@laposte.net", "0617241837", "Velizy"));
		 iDaoLecteur.save(new Lecteur(null,"Mohammed", "Ali", "mohammedali@alice.fr", "0685235598", "Cannes"));
		 iDaoLecteur.save(new Lecteur(null,"Herve", "Vilard", "herve@vilard.fr", "0785423514", "Sens"));
		 iDaoLecteur.save(new Lecteur(null,"Emile", "Legrand", "emilelegrand@free.fr", "0632445867", "Nantes"));
		 iDaoLecteur.save(new Lecteur(null,"Jacques", "Legros", "jacki.legros@netcourrier.com", "0747854137", "Metz"));
		 iDaoLecteur.save(new Lecteur(null,"Jordan", "Rousselot", "rousselot@hotmail.fr", "0622189423", "Deauville"));
		 iDaoLecteur.save(new Lecteur(null,"Mathieu", "Gautier", "mathieugautier@free.fr", "0773951976", "Marseille"));
		 iDaoLecteur.save(new Lecteur(null,"Muriel", "Dreyer", "murieldreyer@gmail.com", "0628653301", "Evry"));
		 iDaoLecteur.save(new Lecteur(null,"Julie", "Boll", "julieB@hotmail.fr", "0745841455", "Lille"));
		 iDaoLecteur.save(new Lecteur(null,"Roland", "Cottereau", "r.cottereau@gmail.fr", "0636642984", "Nancy"));
		 iDaoLecteur.save(new Lecteur(null,"Sarah", "Schrub", "sarahs@gmail.com", "0683553687", "Sceaux"));
		 iDaoLecteur.save(new Lecteur(null,"Christine", "Laporte", "g.christine@hotmail.com", "0620158411", "Anglet"));
		 iDaoLecteur.save(new Lecteur(null,"Marina", "LeGallic", "marina.lg@gmail.com", "0622278653", "Bayonne"));
		 iDaoLecteur.save(new Lecteur(null,"Sylvie", "Descourts", "descourtss@hotmail.fr", "0678966632", "Plessis-Robinson"));
		 iDaoLecteur.save(new Lecteur(null,"Pierre", "Bachelet", "pierrot@hotmail.fr", "0623300589", "Lyon"));
		 
		 
		 Administrateur adminAa = iDaoAdmin.save(new Administrateur(null, 
				 "initNameAdmin",
				 "initSurNameAdmin",
				 "initEmailAdmin", 
				 "initTelephoneAdmin",
				 "initAdresseAdmin",
				 "initUsernameAdmin",
				 "initPasswordAdmin"));
		 
		 String pattern = "yyyy-MM-dd";
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		 String date_debut = simpleDateFormat.format(new Date());
		 String date_fin = simpleDateFormat.format(new Date());
			
		 Emprunt empruntAa = iDaoEmprunt.save(new Emprunt(null,
				 date_debut,
				 date_fin,
				 TypesEmprunt.EFFECTIF,
				 lecteurAa,
				 poesie1
				 ));
		 
		 poesie1.setDispo(false);
		
		

	 }
}
