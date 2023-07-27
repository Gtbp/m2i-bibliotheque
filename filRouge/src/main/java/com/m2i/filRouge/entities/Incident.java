package com.m2i.filRouge.entities;

import javax.persistence.Column;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Incident {

	@Column(name="motif")
	private String motif;
	
	@OneToOne(mappedBy="incident")
	private Emprunt emprut;
}
