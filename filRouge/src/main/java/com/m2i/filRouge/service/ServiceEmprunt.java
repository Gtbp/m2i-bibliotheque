package com.m2i.filRouge.service;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.m2i.filRouge.dto.DtoEmprunt;
import com.m2i.filRouge.entities.Emprunt;

@Service
@Transactional
public class ServiceEmprunt extends AbstractGenericService<Emprunt, Long, DtoEmprunt>  implements IServiceEmprunt {

	public ServiceEmprunt(CrudRepository<Emprunt, Long> dao) {
		super(dao);
	}

	@Override
	public Class<DtoEmprunt> getDtoClass() {
		return DtoEmprunt.class;
	}


}
