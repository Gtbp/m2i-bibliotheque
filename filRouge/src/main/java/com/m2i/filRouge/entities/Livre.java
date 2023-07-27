package com.m2i.filRouge.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Livre {

	public enum EtatLivre {BON_ETAT, ABIME, HORS_SERVICE};
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idLivre")
	private Long idLivre;
	
	@Column(name="titre")
	private String titre;
	
	@Column(name="auteur")
	private String auteur;
	
	@Column(name="editeur")
	private String editeur;
	
	@Column(name="dispo")
	private Boolean dispo;
	
	@Column(name="EtatLivre")
	private EtatLivre etat = EtatLivre.BON_ETAT;
	
}
