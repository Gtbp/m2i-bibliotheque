package com.m2i.filRouge.entities;

import java.util.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Emprunt {
	
	public enum TypesEmprunt {RESERVATION, EFFECTIF};
	
	@Column(name="idEmprunt")
	private Long idEmprunt;
	
	@Column(name="date_debut")
	private Date date_debut;
	
	@Column(name="date_fin")
	private Date date_fin;
	
	@Column(name="type")
	private TypesEmprunt type;

}
