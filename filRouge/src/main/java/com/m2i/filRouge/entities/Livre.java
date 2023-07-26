package com.m2i.filRouge.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Livre {

	public enum EtatLivre {BON_ETAT, ABIME, HORS_SERVICE};
	
	private Long idLivre;
	private String titre;
	private String auteur;
	private String editeur;
	private Boolean dispo;
	private EtatLivre etat = EtatLivre.BON_ETAT;
	
}
