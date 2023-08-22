package com.m2i.filRouge.service;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.m2i.filRouge.dto.DtoDomaine;
import com.m2i.filRouge.entities.Domaine;

@Service
@Transactional
public class ServiceDomaine extends AbstractGenericService<Domaine, Long, DtoDomaine> implements IServiceDomaine {

	public ServiceDomaine(CrudRepository<Domaine, Long> dao) {
		super(dao);
	}

	@Override
	public Class<DtoDomaine> getDtoClass() {
		return DtoDomaine.class;
	}

	

	
}
