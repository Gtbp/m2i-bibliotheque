package com.m2i.filRouge.service;

import com.m2i.filRouge.dto.DtoEmprunt;
import com.m2i.filRouge.entities.Emprunt;
import com.m2i.filRouge.entities.Livre;

public interface IServiceEmprunt extends GenericService<Emprunt,Long,DtoEmprunt>{

	// méthode pour rallonger durée de l'emprunt
	
	DtoEmprunt saveOrUpdateDtoEmprunt(DtoEmprunt dtoEmprunt);
	Livre findLivreByIdEmprunt(Long idEmprunt);
	
}
