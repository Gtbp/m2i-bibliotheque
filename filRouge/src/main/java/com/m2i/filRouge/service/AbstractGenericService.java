package com.m2i.filRouge.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public abstract class AbstractGenericService<E,ID,DTO> implements GenericService<E,ID,DTO>{

	private CrudRepository<E, ID>  dao = null;
	
	// constructeur nécessaire pour appeler CrudRepository
	public AbstractGenericService(CrudRepository<E, ID>  dao) {
		this.dao = dao;
	}

	@Override
	public E findById(ID id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public E save(E entity) {
		return dao.save(entity);
	}

	@Override
	public void deleteById(ID id) {
		dao.deleteById(id);
	}

	@Override
	public boolean existById(ID id) {
		return dao.existsById(id);
	}

	@Override
	public List<E> findAll() {
		return (List<E>) dao.findAll();
	}
	
	


}
