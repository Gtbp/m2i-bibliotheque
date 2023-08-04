package com.m2i.filRouge.idao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.filRouge.entities.Personne;

public interface IDaoPersonne extends JpaRepository<Personne, Long>{

}
