package com.m2i.filRouge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DtoPersonne {
	
	private Long idPersonne;
	private String prenom;
	private String nom;
	private String email;
	private String telephone;
	private String adresse;
	
	
	public DtoPersonne(Long idPersonne, String prenom, String nom, String email, String telephone, String adresse) {
		super();
		this.idPersonne = idPersonne;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
	}
	
}
