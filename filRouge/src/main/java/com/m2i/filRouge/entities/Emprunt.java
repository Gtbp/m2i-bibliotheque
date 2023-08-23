package com.m2i.filRouge.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor 
public class Emprunt {
	
	public enum TypesEmprunt {RESERVATION, EFFECTIF};
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idEmprunt")
	private Long idEmprunt;
	
	@Column(name="date_debut")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", locale = "fr_FR", timezone="Europe/Paris")
	private Date date_debut;
	
	@Column(name="date_fin")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", locale = "fr_FR", timezone="Europe/Paris")
	private Date date_fin;
	
	@Column(name="type")
	private TypesEmprunt type;
	
	@JsonIgnore
	@ManyToOne
		@JoinColumn(name= "idLecteur")
	private Lecteur lecteur;
	
	@JsonIgnore
	@OneToOne(optional=false)
			@JoinColumn(name="idLivre")  // pas sûr de l'id ici
	private Livre livre;

	@OneToOne
			@JoinColumn(name="motif")
	private Incident incident;

	public Emprunt(Long idEmprunt, Date date_debut, Date date_fin, TypesEmprunt type, Lecteur lecteur, Livre livre) {
		super();
		this.idEmprunt = idEmprunt;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.type = type;
		this.lecteur = lecteur;
		this.livre = livre;
	}
	
	
	public Date initDateFin() {
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(date_fin); 
		c.add(Calendar.DATE, 14);
		date_fin = c.getTime();
		
		return date_fin;
	}
	
	
}
