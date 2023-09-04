package com.m2i.filRouge.idao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.filRouge.entities.Emprunt;
import com.m2i.filRouge.entities.Livre;

public interface IDaoEmprunt extends JpaRepository<Emprunt, Long>{

	Livre findLivreByIdEmprunt(Long IdEmprunt);

//	public Emprunt prolonger(Long idEmprunt, Date date_fin);
	
	
//	public Emprunt prolonger(Long idEmprunt, Date newDate_fin) {
//		Emprunt emprunt = iDaoEmprunt.findById(idEmprunt);
//		emprunt.setDate_fin(newDate_fin);
//
//	return emprunt; 
//	}
}
