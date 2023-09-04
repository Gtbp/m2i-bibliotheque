package com.m2i.filRouge.idao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.filRouge.entities.Livre;

public interface IDaoLivre extends JpaRepository<Livre, Long>{

	 List<Livre> findByTitreContainingOrAuteurContaining(String titre,String auteur);
}
