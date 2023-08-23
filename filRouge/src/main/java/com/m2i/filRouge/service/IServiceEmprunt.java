package com.m2i.filRouge.service;

import com.m2i.filRouge.dto.DtoEmprunt;
import com.m2i.filRouge.entities.Emprunt;

public interface IServiceEmprunt extends GenericService<Emprunt,Long,DtoEmprunt>{

	// méthode pour rallonger durée de l'emprunt
	
	DtoEmprunt saveOrUpdateDtoEmprunt(DtoEmprunt dtoEmprunt);
	
}
