package com.m2i.filRouge.service;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.m2i.filRouge.dto.DtoLecteur;
import com.m2i.filRouge.entities.Lecteur;

@Service
@Transactional
public class ServiceLecteur extends AbstractGenericService<Lecteur,Long,DtoLecteur> implements IServiceLecteur{

	public ServiceLecteur(CrudRepository<Lecteur, Long> dao) {
		super(dao);
	}

	@Override
	public Class<DtoLecteur> getDtoClass() {
		return DtoLecteur.class;
	}

}
