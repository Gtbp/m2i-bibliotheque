package com.m2i.filRouge.idao.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.m2i.filRouge.entities.Emprunt;
import com.m2i.filRouge.idao.IDaoEmprunt;

public class DaoEmprunt extends DaoGeneric<Emprunt, Long> implements IDaoEmprunt  {

	@PersistenceContext
	private EntityManager entityManager;

	public DaoEmprunt() {
		super(Emprunt.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
