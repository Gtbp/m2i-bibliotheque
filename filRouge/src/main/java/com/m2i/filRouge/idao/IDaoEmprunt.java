package com.m2i.filRouge.idao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.filRouge.entities.Emprunt;

public interface IDaoEmprunt extends JpaRepository<Emprunt, Long>{

//	public Emprunt prolonger(Long idEmprunt, Date date_fin);
	
	
//	public Emprunt prolonger(Long idEmprunt, Date newDate_fin) {
//		Emprunt emprunt = iDaoEmprunt.findById(idEmprunt);
//		emprunt.setDate_fin(newDate_fin);
//
//	return emprunt; 
//	}
}
