create table domaine (id_domaine number(19,0) generated as identity, description varchar2(255 char), nom varchar2(255 char), primary key (id_domaine));
create table emprunt (id_emprunt number(19,0) generated as identity, date_debut timestamp, date_fin timestamp, type number(10,0), motif number(19,0), id_lecteur number(19,0), id_livre number(19,0) not null, primary key (id_emprunt));
create table incident (id_incident number(19,0) generated as identity, motif varchar2(255 char), primary key (id_incident));
create table livre (id_livre number(19,0) generated as identity, auteur varchar2(255 char), dispo number(1,0), editeur varchar2(255 char), etat_livre number(10,0), titre varchar2(255 char), domaine number(19,0), primary key (id_livre));
create table personne (type_personne varchar2(31 char) not null, id_personne number(19,0) generated as identity, adresse varchar2(255 char), email varchar2(255 char), nom varchar2(255 char), prenom varchar2(255 char), telephone varchar2(255 char), password varchar2(255 char), username varchar2(255 char), primary key (id_personne));
alter table emprunt add constraint UK_nauf36pnfpjw7depdk3yl50ik unique (id_livre);
alter table emprunt add constraint FK5owklyslb4g6njcu8omc53unp foreign key (motif) references incident;
alter table emprunt add constraint FK4wj65w7ulgjwick3b8hwn5tem foreign key (id_lecteur) references personne;
alter table emprunt add constraint FK2c4tf1n0sr8wpaxsw4f3ec2ur foreign key (id_livre) references livre;
alter table livre add constraint FKno6aivssoq8j0vo4ddxgp9eyd foreign key (domaine) references domaine;
create table domaine (id_domaine number(19,0) generated as identity, description varchar2(255 char), nom varchar2(255 char), primary key (id_domaine));
create table emprunt (id_emprunt number(19,0) generated as identity, date_debut timestamp, date_fin timestamp, type number(10,0), motif number(19,0), id_lecteur number(19,0), id_livre number(19,0) not null, primary key (id_emprunt));
create table incident (id_incident number(19,0) generated as identity, motif varchar2(255 char), primary key (id_incident));
create table livre (id_livre number(19,0) generated as identity, auteur varchar2(255 char), dispo number(1,0), editeur varchar2(255 char), etat_livre number(10,0), titre varchar2(255 char), domaine number(19,0), primary key (id_livre));
create table personne (type_personne varchar2(31 char) not null, id_personne number(19,0) generated as identity, adresse varchar2(255 char), email varchar2(255 char), nom varchar2(255 char), prenom varchar2(255 char), telephone varchar2(255 char), password varchar2(255 char), username varchar2(255 char), primary key (id_personne));
alter table emprunt add constraint UK_nauf36pnfpjw7depdk3yl50ik unique (id_livre);
alter table emprunt add constraint FK5owklyslb4g6njcu8omc53unp foreign key (motif) references incident;
alter table emprunt add constraint FK4wj65w7ulgjwick3b8hwn5tem foreign key (id_lecteur) references personne;
alter table emprunt add constraint FK2c4tf1n0sr8wpaxsw4f3ec2ur foreign key (id_livre) references livre;
alter table livre add constraint FKno6aivssoq8j0vo4ddxgp9eyd foreign key (domaine) references domaine;
create table domaine (id_domaine number(19,0) generated as identity, description varchar2(255 char), nom varchar2(255 char), primary key (id_domaine));
create table emprunt (id_emprunt number(19,0) generated as identity, date_debut timestamp, date_fin timestamp, type number(10,0), motif number(19,0), id_lecteur number(19,0), id_livre number(19,0) not null, primary key (id_emprunt));
create table incident (id_incident number(19,0) generated as identity, motif varchar2(255 char), primary key (id_incident));
create table livre (id_livre number(19,0) generated as identity, auteur varchar2(255 char), dispo number(1,0), editeur varchar2(255 char), etat_livre number(10,0), titre varchar2(255 char), domaine number(19,0), primary key (id_livre));
create table personne (type_personne varchar2(31 char) not null, id_personne number(19,0) generated as identity, adresse varchar2(255 char), email varchar2(255 char), nom varchar2(255 char), prenom varchar2(255 char), telephone varchar2(255 char), password varchar2(255 char), username varchar2(255 char), primary key (id_personne));
alter table emprunt add constraint UK_nauf36pnfpjw7depdk3yl50ik unique (id_livre);
alter table emprunt add constraint FK5owklyslb4g6njcu8omc53unp foreign key (motif) references incident;
alter table emprunt add constraint FK4wj65w7ulgjwick3b8hwn5tem foreign key (id_lecteur) references personne;
alter table emprunt add constraint FK2c4tf1n0sr8wpaxsw4f3ec2ur foreign key (id_livre) references livre;
alter table livre add constraint FKno6aivssoq8j0vo4ddxgp9eyd foreign key (domaine) references domaine;
create table domaine (id_domaine number(19,0) generated as identity, description varchar2(255 char), nom varchar2(255 char), primary key (id_domaine));
create table emprunt (id_emprunt number(19,0) generated as identity, date_debut timestamp, date_fin timestamp, type number(10,0), motif number(19,0), id_lecteur number(19,0), id_livre number(19,0) not null, primary key (id_emprunt));
create table incident (id_incident number(19,0) generated as identity, motif varchar2(255 char), primary key (id_incident));
create table livre (id_livre number(19,0) generated as identity, auteur varchar2(255 char), dispo number(1,0), editeur varchar2(255 char), etat_livre number(10,0), titre varchar2(255 char), domaine number(19,0), primary key (id_livre));
create table personne (type_personne varchar2(31 char) not null, id_personne number(19,0) generated as identity, adresse varchar2(255 char), email varchar2(255 char), nom varchar2(255 char), prenom varchar2(255 char), telephone varchar2(255 char), password varchar2(255 char), username varchar2(255 char), primary key (id_personne));
alter table emprunt add constraint UK_nauf36pnfpjw7depdk3yl50ik unique (id_livre);
alter table emprunt add constraint FK5owklyslb4g6njcu8omc53unp foreign key (motif) references incident;
alter table emprunt add constraint FK4wj65w7ulgjwick3b8hwn5tem foreign key (id_lecteur) references personne;
alter table emprunt add constraint FK2c4tf1n0sr8wpaxsw4f3ec2ur foreign key (id_livre) references livre;
alter table livre add constraint FKno6aivssoq8j0vo4ddxgp9eyd foreign key (domaine) references domaine;
create table domaine (id_domaine number(19,0) generated as identity, description varchar2(255 char), nom varchar2(255 char), primary key (id_domaine));
create table emprunt (id_emprunt number(19,0) generated as identity, date_debut timestamp, date_fin timestamp, type number(10,0), motif number(19,0), id_lecteur number(19,0), id_livre number(19,0) not null, primary key (id_emprunt));
create table incident (id_incident number(19,0) generated as identity, motif varchar2(255 char), primary key (id_incident));
create table livre (id_livre number(19,0) generated as identity, auteur varchar2(255 char), dispo number(1,0), editeur varchar2(255 char), etat_livre number(10,0), titre varchar2(255 char), domaine number(19,0), primary key (id_livre));
create table personne (type_personne varchar2(31 char) not null, id_personne number(19,0) generated as identity, adresse varchar2(255 char), email varchar2(255 char), nom varchar2(255 char), prenom varchar2(255 char), telephone varchar2(255 char), password varchar2(255 char), username varchar2(255 char), primary key (id_personne));
alter table emprunt add constraint UK_nauf36pnfpjw7depdk3yl50ik unique (id_livre);
alter table emprunt add constraint FK5owklyslb4g6njcu8omc53unp foreign key (motif) references incident;
alter table emprunt add constraint FK4wj65w7ulgjwick3b8hwn5tem foreign key (id_lecteur) references personne;
alter table emprunt add constraint FK2c4tf1n0sr8wpaxsw4f3ec2ur foreign key (id_livre) references livre;
alter table livre add constraint FKno6aivssoq8j0vo4ddxgp9eyd foreign key (domaine) references domaine;
create table domaine (id_domaine number(19,0) generated as identity, description varchar2(255 char), nom varchar2(255 char), primary key (id_domaine));
create table emprunt (id_emprunt number(19,0) generated as identity, date_debut timestamp, date_fin timestamp, type number(10,0), motif number(19,0), id_lecteur number(19,0), id_livre number(19,0) not null, primary key (id_emprunt));
create table incident (id_incident number(19,0) generated as identity, motif varchar2(255 char), primary key (id_incident));
create table livre (id_livre number(19,0) generated as identity, auteur varchar2(255 char), dispo number(1,0), editeur varchar2(255 char), etat_livre number(10,0), titre varchar2(255 char), domaine number(19,0), primary key (id_livre));
create table personne (type_personne varchar2(31 char) not null, id_personne number(19,0) generated as identity, adresse varchar2(255 char), email varchar2(255 char), nom varchar2(255 char), prenom varchar2(255 char), telephone varchar2(255 char), password varchar2(255 char), username varchar2(255 char), primary key (id_personne));
alter table emprunt add constraint UK_nauf36pnfpjw7depdk3yl50ik unique (id_livre);
alter table emprunt add constraint FK5owklyslb4g6njcu8omc53unp foreign key (motif) references incident;
alter table emprunt add constraint FK4wj65w7ulgjwick3b8hwn5tem foreign key (id_lecteur) references personne;
alter table emprunt add constraint FK2c4tf1n0sr8wpaxsw4f3ec2ur foreign key (id_livre) references livre;
alter table livre add constraint FKno6aivssoq8j0vo4ddxgp9eyd foreign key (domaine) references domaine;
