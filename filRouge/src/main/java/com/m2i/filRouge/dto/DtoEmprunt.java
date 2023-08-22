package com.m2i.filRouge.dto;

import java.util.Date;

import com.m2i.filRouge.entities.Emprunt.TypesEmprunt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DtoEmprunt {
	

	// dto simplifié 
	private Long idEmprunt;	
	private Date date_debut;
	private Date date_fin;
	private TypesEmprunt type;
	
	
	public DtoEmprunt(Long idEmprunt, Date date_debut, Date date_fin, TypesEmprunt type) {
		super();
		this.idEmprunt = idEmprunt;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.type = type;
	}
	
	
}
