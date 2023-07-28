package com.m2i.filRouge.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
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
	
	@ManyToOne
		@JoinColumn(name= "domaine")
	private Domaine domaine;
	
	// Constructeur

	public Livre(Long idLivre, String titre, String auteur, String editeur, Boolean dispo, EtatLivre etat,
			Domaine domaine) {
		super();
		this.idLivre = idLivre;
		this.titre = titre;
		this.auteur = auteur;
		this.editeur = editeur;
		this.dispo = dispo;
		this.etat = etat;
		this.domaine = domaine;
	}

	
	
	
	
	
	
	
}
