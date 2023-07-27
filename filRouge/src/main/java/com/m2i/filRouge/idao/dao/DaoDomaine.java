package com.m2i.filRouge.idao.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.m2i.filRouge.entities.Domaine;
import com.m2i.filRouge.idao.IDaoDomaine;

@Repository
@Transactional
public class DaoDomaine extends DaoGeneric<Domaine, Long> implements IDaoDomaine {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public DaoDomaine(){
		super(Domaine.class);
		}

}
