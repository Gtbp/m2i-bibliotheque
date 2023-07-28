/*
 CE SCRIPT SERT A INITIALISER ENTIEREMENT UNE BASE DE DONNEES ORACLE
 (EN FIN DE Developpement, Debut de PRODUCTION)
 De manière à ce que l'appli SpringJPA puisse fonctionner normalement
 sans créations et réinitialisations automatiques des tables
 (sans option spring.jpa.hibernate.ddl-auto=create)
 ========
 CE SCRIPT EST UN DES LIVRABLES IMPORTANTS ATTENDUS !!!
 ========
 Avant de lancer ce script, il est conseillé de créer un USER... (ex: init-user.sql en SYSTEM)
 puis on lance ce script en étant connecté à sqlplus en tant que USER...
 après un début de session de ce genre:
  alter session set "_ORACLE_SCRIPT"=true;
 */
/*
 * Detruire les anciennes versions des séquences et tables
 * en faisant en sorte que les contraintes d'intégrité soient supprimées ou respéctées
 * en controllant bien l'ordre des suppressions (souvent ordre inverse des insert into)
 */
drop table domaine cascade constraints;
drop table emprunt cascade constraints;
drop table incident cascade constraints;
drop table livre cascade constraints;
drop table personne cascade constraints;


/*
 création des tables 
 */
create table domaine (id_domaine number(19,0) generated as identity,
nom varchar2(255 char),
description varchar2(255 char),
primary key (id_domaine));

create table livre (id_livre number(19,0) generated as identity,
titre varchar2(255 char),
auteur varchar2(255 char),
editeur varchar2(255 char),
dispo number(1,0),
etat_livre number(10,0),
domaine number(19,0),
primary key (id_livre));

create table personne (
id_personne number(19,0) generated as identity,
prenom varchar2(255 char),
nom varchar2(255 char),
email varchar2(255 char),
telephone varchar2(255 char),
adresse varchar2(255 char),
type_personne varchar2(31 char) not null,
password varchar2(255 char),
username varchar2(255 char),
primary key (id_personne));

create table incident (id_incident number(19,0) generated as identity,
motif varchar2(255 char),
primary key (id_incident));								
                                
create table emprunt (id_emprunt number(19,0) generated as identity,
 date_debut timestamp,
date_fin timestamp,
type number(10,0),
motif number(19,0), 
id_lecteur number(19,0) not null, 
id_livre number(19,0) not null, 
primary key (id_emprunt));
					
					
/*
 création des contraintes relationnelles (fk --> pk valide) 
 */		
 alter table livre add constraint FK_DomaineLivre foreign key (domaine) references domaine;		
alter table emprunt add constraint UK_OneToOneLivreEmprunt unique (id_livre);
alter table emprunt add constraint FK_IncidentEmprunt foreign key (motif) references incident;
alter table emprunt add constraint FK_PersonneEmprunt foreign key (id_lecteur) references personne;
alter table emprunt add constraint FK_LivreEmprunt foreign key (id_livre) references livre;


COMMIT;

/*
 INSERT INTO .... VALUES ... (jeux de données)
 */
INSERT INTO Domaine(id_domaine, nom, description)
VALUES(null, "initDomaineNomAa","initDomainDescriptionAa");
INSERT INTO Domaine(id_domaine, nom, description)
VALUES(null, "initDomaineNomBb","initDomainDescriptionBb");
INSERT INTO Domaine(id_domaine, nom, description)
VALUES(null, "initDomaineNomCc","initDomainDescriptionCc");


INSERT INTO Livre(id_livre,titre,auteur,editeur,dispo,etat,domaine)
VALUES(null, "initTitreAa", "initAuteurAa", "initEditeurAa", true, 0, 1);
INSERT INTO Livre(id_livre,titre,auteur,editeur,dispo,etat,domaine)
VALUES(null, "initTitreBb", "initAuteurBb", "initEditeurBb", true, 0, 1);
INSERT INTO Livre(id_livre,titre,auteur,editeur,dispo,etat,domaine)
VALUES(null, "initTitreCc", "initAuteurCc", "initEditeurCc", true, 0, 1);

INSERT INTO Personne
INSERT INTO Personne(id_personne, prenom, nom, email, telephone, adresse, type_personne)
VALUES (null, "initPrenomAA", "initNomAA", "initEmailAA", "initTelephoneAA", "initadresseAA", "Lecteur");

INSERT INTO Personne(id_personne, prenom, nom, email, telephone, adresse, type_personne)
VALUES (null, "initPrenomBB", "initNomBB", "initEmailBB", "initTelephoneBB", "initadresseBB, Lecteur");

INSERT INTO Personne(id_personne, prenom, nom, email, telephone, adresse, type_personne)
VALUES (null, "initPrenomCC", "initNomCC", "initEmailCC", "initTelephoneCC", "initadresseCC", "Lecteur");

INSERT INTO Personne(id_personne, prenom, nom, email, telephone, adresse, type_personne)
VALUES (null, "initPrenomDD", "initNomDD", "initEmailDD", "initTelephoneDD", "initadresseDD", "Lecteur");

INSERT INTO Personne(id_personne, prenom, nom, email, telephone, adresse, type_personne, username, password)
VALUES (null, "initPrenomEE", "initNomEE", "initEmailEE", "initTelephoneEE", "initAdresseEE", "Administrateur", "initUsernameEE", "initUsernameEE");

INSERT INTO Personne(id_personne, prenom, nom, email, telephone, adresse, type_personne, username, password)
VALUES (null, "initPrenomFF", "initNomFF", "initEmailFF", "initTelephoneFF", "initAdresseFF", "Administrateur", "initUsernameFF", "initPasswordFF");

INSERT INTO Personne(id_personne, prenom, nom, email, telephone, adresse, type_personne, username, password)
VALUES (null, "initPrenomGG", "initNomGG", "initEmailGG", "initTelephoneGG", "initAdresseGG", "Administrateur", "initUsernameGG", "initPasswordGG");

INSERT INTO Personne(id_personne, prenom, nom, email, telephone, adresse, type_personne, username, password)
VALUES (null, "initPrenomHH", "initNomHH", "initEmailHH", "initTelephoneHH", "initAdresseHH", "Administrateur", "initUsernameHH", "initPasswordHH");

/*
INSERT INTO Incident
*/

INSERT INTO Emprunt(id_emprunt, date_debut, date_fin, type, motif, id_lecteur, id_livre)
VALUES(null,  to_date('28-07-2023', 'dd-mm-yyyy'), to_date('29-07-2023', 'dd-mm-yyyy'),null, null, 1, 1  );
INSERT INTO Emprunt(id_emprunt, date_debut, date_fin, type, motif, id_lecteur, id_livre)
VALUES(null,  to_date('30-07-2023', 'dd-mm-yyyy'), to_date('31-07-2023', 'dd-mm-yyyy'),null, null, 2, 1  );
INSERT INTO Emprunt(id_emprunt, date_debut, date_fin, type, motif, id_lecteur, id_livre)
VALUES(null,  to_date('01-08-2023', 'dd-mm-yyyy'), to_date('15-08-2023', 'dd-mm-yyyy'),null, null, 1, 2  );
INSERT INTO Emprunt(id_emprunt, date_debut, date_fin, type, motif, id_lecteur, id_livre)
VALUES(null,  to_date('16-08-2023', 'dd-mm-yyyy'), to_date('17-08-2023', 'dd-mm-yyyy'),null, null, 2, 2  );

/*
 SELECT **** POUR VERIFIER
 */
SELECT * FROM Domaine;
SELECT * FROM Livre;
SELECT * FROM Personne;
SELECT * FROM Incident;
SELECT * FROM Emprunt;

COMMIT;