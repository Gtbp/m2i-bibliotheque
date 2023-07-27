package com.m2i.filRouge.idao.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.m2i.filRouge.entities.Livre;
import com.m2i.filRouge.idao.IDaoLivre;

@Repository
@Transactional
public class DaoLivre extends DaoGeneric<Livre, Long> implements IDaoLivre  {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public DaoLivre(){
		super(Livre.class);
		}
}
