package com.m2i.filRouge.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Personne {

	@Column(name="prenom")
	private String prenom;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="adresse")
	private String adresse;
}
