package com.m2i.filRouge.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Lecteur extends Personne{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idLecteur")
	private Long idLecteur;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy= "emprunt", cascade = CascadeType.ALL)
	private List<Emprunt> emprunts;
}
