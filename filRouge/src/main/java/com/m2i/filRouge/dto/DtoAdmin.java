package com.m2i.filRouge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DtoAdmin extends DtoPersonne{

	private String username;
	private String password;
	
	
	public DtoAdmin(Long idPersonne, String prenom, String nom, String email, String telephone, String adresse, String username, String password) {
		super(idPersonne, prenom, nom, email, telephone, adresse);
		this.username = username;
		this.password = password;
	}
	
	

}
