package com.m2i.filRouge.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
