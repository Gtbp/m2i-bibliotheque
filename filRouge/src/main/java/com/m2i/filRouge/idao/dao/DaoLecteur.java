package com.m2i.filRouge.idao.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.m2i.filRouge.entities.Lecteur;
import com.m2i.filRouge.idao.IDaoLecteur;

@Repository
@Transactional
public class DaoLecteur extends DaoGeneric<Lecteur, Long> implements IDaoLecteur  {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}
	
	public DaoLecteur() {
		super(Lecteur.class);
	}

}
