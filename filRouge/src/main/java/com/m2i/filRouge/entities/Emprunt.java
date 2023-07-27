package com.m2i.filRouge.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Emprunt {
	
	public enum TypesEmprunt {RESERVATION, EFFECTIF};
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idEmprunt")
	private Long idEmprunt;
	
	@Column(name="date_debut")
	private Date date_debut;
	
	@Column(name="date_fin")
	private Date date_fin;
	
	@Column(name="type")
	private TypesEmprunt type;
	
	@ManyToOne
		@JoinColumn(name= "lecteur")
	private Lecteur lecteur;
	
	@OneToOne(optional=false)
		@MapsId
			@JoinColumn(name="idLivre")  // pas sûr de l'id ici
	private Livre livre;

	@OneToOne
		@MapsId
			@JoinColumn(name="motif")
	private Incident incident;
}
