package com.m2i.filRouge.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.m2i.filRouge.converter.GenericConverter;
import com.m2i.filRouge.exception.NotFoundException;

public abstract class AbstractGenericService<E,ID,DTO> implements GenericService<E,ID,DTO>{

	public abstract Class<DTO> getDtoClass();
	
	private CrudRepository<E, ID>  dao = null;
	
	// constructeur nécessaire pour appeler CrudRepository
	public AbstractGenericService(CrudRepository<E, ID>  dao) {
		this.dao = dao;
	}

	@Override
	public E findById(ID id) {
		return dao.findById(id).orElse(null);
	}
	
	// pour pas utiliser le converter dans notre rest controller et direct faire un findDtoByid dans la methode Get
	public DTO findDtoById(ID id) throws NotFoundException {
		E e = this.findById(id);
		if(e != null) {
			return GenericConverter.map(e,getDtoClass());
		} else {
			throw new NotFoundException("Entity not found for id "+id);
		}
		
	}

	@Override
	public E save(E entity) {
		return dao.save(entity);
	}
	
	@Override
	public void deleteById(ID id) throws NotFoundException{
		
		if(!(dao.existsById(id)))
			throw new NotFoundException("no entity to delete for id: "+id);
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
	
	public List<DTO> findAllDto() {
		return GenericConverter.map(this.findAll(), getDtoClass());
	}
	
	


}
