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
import lombok.ToString;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Incident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idIncident")
	private Long idIncident;
	
	@Column(name="motif")
	private String motif;
	

}
