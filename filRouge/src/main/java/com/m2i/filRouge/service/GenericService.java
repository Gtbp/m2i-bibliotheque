package com.m2i.filRouge.service;

import java.util.List;

public interface GenericService<E,ID,DTO> {

	 	public E findById(ID id);
	    public E save(E entity);
	    public void deleteById(ID id);
	    public boolean existById(ID id);
	    public List<E> findAll();
}
