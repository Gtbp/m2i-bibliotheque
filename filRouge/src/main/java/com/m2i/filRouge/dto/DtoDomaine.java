package com.m2i.filRouge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DtoDomaine {

	// Dto simplifié de domaine
	
	private Long idDomaine;
	private String nom;
	private String description;
	
	
	public DtoDomaine(Long idDomaine, String nom, String description) {
		super();
		this.idDomaine = idDomaine;
		this.nom = nom;
		this.description = description;
	}
	
	
	
	
}
