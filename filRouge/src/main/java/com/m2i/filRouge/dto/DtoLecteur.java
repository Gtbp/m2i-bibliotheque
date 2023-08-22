package com.m2i.filRouge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DtoLecteur extends DtoPersonne{
	
	public DtoLecteur(Long idPersonne, String prenom, String nom, String email, String telephone, String adresse) {
		super(idPersonne, prenom, nom, email, telephone, adresse);
	}

	
	
}
