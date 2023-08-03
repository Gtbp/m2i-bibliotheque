package com.m2i.filRouge.service;

import java.util.List;

public interface GenericService<E,ID,DTO> {

	 	public E findById(ID id);
	 	public DTO findDtoById(ID id); // pour pas utiliser le converter dans notre rest controller et directement faire un findDtoByid dans la methode Get
	    public E save(E entity);
	    public void deleteById(ID id);
	    public boolean existById(ID id);
	    public List<E> findAll();
	    public List<DTO> findAllDto(); // pareil que findDto
}
