package com.m2i.filRouge.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Personne {

	private String prenom;
	private String nom;
	private String email;
	private String telephone;
	private String adresse;
}
