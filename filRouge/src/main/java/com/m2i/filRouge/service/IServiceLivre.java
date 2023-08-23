package com.m2i.filRouge.service;

import com.m2i.filRouge.dto.DtoLivre;
import com.m2i.filRouge.entities.Livre;

public interface IServiceLivre extends GenericService<Livre,Long,DtoLivre> {

	DtoLivre saveOrUpdateDtoLivre(DtoLivre dtoLivre); 
	

}
