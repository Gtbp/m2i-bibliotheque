package com.m2i.filRouge.idao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.filRouge.entities.Domaine;

public interface IDaoDomaine extends JpaRepository<Domaine, Long>{

	Optional<Domaine> findIdByNom(String nom);
	
}
