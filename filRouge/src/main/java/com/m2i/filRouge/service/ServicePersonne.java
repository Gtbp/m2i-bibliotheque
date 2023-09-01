package com.m2i.filRouge.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.m2i.filRouge.dto.DtoPersonne;
import com.m2i.filRouge.entities.Personne;

@Service
@Transactional
public class ServicePersonne extends AbstractGenericService<Personne,Long,DtoPersonne> implements IServicePersonne{

	public ServicePersonne(CrudRepository<Personne, Long> dao) {
		super(dao);
	}

	@Override
	public Class<DtoPersonne> getDtoClass() {
		return DtoPersonne.class;
	}
	
}
