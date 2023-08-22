package com.m2i.filRouge.service;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.m2i.filRouge.dto.DtoAdmin;
import com.m2i.filRouge.entities.Administrateur;

@Service
@Transactional
public class ServiceAdmin extends AbstractGenericService<Administrateur,Long,DtoAdmin> implements IServiceAdmin{

	public ServiceAdmin(CrudRepository<Administrateur, Long> dao) {
		super(dao);
	}

	@Override
	public Class<DtoAdmin> getDtoClass() {
		return DtoAdmin.class;
	}

}
