package com.m2i.filRouge.idao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.filRouge.entities.Administrateur;

public interface IDaoAdmin extends JpaRepository<Administrateur, Long> {

}
