package com.m2i.filRouge.idao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.m2i.filRouge.idao.IDaoGeneric;

@Transactional
public abstract class DaoGeneric<E, PK> implements IDaoGeneric<E, PK> {
	
	private Class<E> entityClass; 
	
	public DaoGeneric(Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	
	public abstract EntityManager getEntityManager();

	
	@Override
	public E findById(PK id) {
		return getEntityManager().find(entityClass, id);
	}

	@Override
	public List<E> findAll() {
		return getEntityManager().createQuery("FROM " + entityClass.getSimpleName(),
				entityClass)
	            .getResultList();
	}

	@Override
	public E create(E e) {
		getEntityManager().persist(e);
		return e;
	}

	@Override
	public void update(E e) {
		getEntityManager().merge(e);
		
	}

	@Override
	public void delete(PK id) {
		E e= getEntityManager().find(entityClass, id);
		getEntityManager().remove(e);
		
	}

}
