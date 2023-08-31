package com.m2i.filRouge.dto;

import com.m2i.filRouge.entities.Emprunt.TypesEmprunt;
import com.m2i.filRouge.entities.Lecteur;
import com.m2i.filRouge.entities.Livre;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DtoEmprunt {
	

	private Long idEmprunt;	
	private String date_debut;
	private String date_fin;
	private TypesEmprunt type;
	private Lecteur lecteur;
	private Livre livre;
	
	
	public DtoEmprunt(Long idEmprunt, String date_debut, String date_fin, TypesEmprunt type, Lecteur lecteur, Livre livre) {
		super();
		this.idEmprunt = idEmprunt;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.type = type;
		this.lecteur = lecteur;
		this.livre = livre;
	}
	
	
}
