package com.m2i.filRouge.dao;

import java.util.List;

public interface DaoGeneric<E,PK> {
	
	E findById(PK id);
    List<E> findAll();
    E create(E e); 
    void update(E e);
    void delete(PK num);
    
}
