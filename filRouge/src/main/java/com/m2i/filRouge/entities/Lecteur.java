package com.m2i.filRouge.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "Lecteur")
@Getter @Setter @NoArgsConstructor 
public class Lecteur extends Personne{

	
	@OneToMany(fetch= FetchType.LAZY, mappedBy= "idEmprunt", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Emprunt> emprunts;
	
	public Lecteur(Long idPersonne, String prenom, String nom, String email, String telephone, String adresse) {
		super(idPersonne, prenom, nom, email, telephone, adresse);
		
	}

	
}
