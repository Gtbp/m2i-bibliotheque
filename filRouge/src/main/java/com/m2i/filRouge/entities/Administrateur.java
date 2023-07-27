package com.m2i.filRouge.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "Administrateur")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Administrateur extends Personne{

	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;

}
