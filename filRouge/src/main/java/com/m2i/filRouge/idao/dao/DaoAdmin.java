package com.m2i.filRouge.idao.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.m2i.filRouge.entities.Administrateur;
import com.m2i.filRouge.idao.IDaoAdmin;

@Repository
@Transactional
public class DaoAdmin extends DaoGeneric<Administrateur, Long> implements IDaoAdmin   {

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}
	
	public DaoAdmin() {
		super(Administrateur.class);
	}
}
