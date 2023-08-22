package com.m2i.filRouge.service;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.m2i.filRouge.dto.DtoLivre;
import com.m2i.filRouge.entities.Livre;

@Service
@Transactional
public class ServiceLivre extends AbstractGenericService<Livre, Long, DtoLivre> implements IServiceLivre {

	public ServiceLivre(CrudRepository<Livre, Long> dao) {
		super(dao);
	}

	@Override
	public Class<DtoLivre> getDtoClass() {
		return DtoLivre.class;
	}

	

	
}
