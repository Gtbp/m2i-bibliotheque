package com.m2i.filRouge.idao;

import java.util.Date;

import com.m2i.filRouge.entities.Emprunt;

public interface IDaoEmprunt extends IDaoGeneric<Emprunt, Long>{

	public Emprunt prolonger(Long idEmprunt, Date date_fin);
}
