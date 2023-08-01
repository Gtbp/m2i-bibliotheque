package com.m2i.filRouge.idao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.filRouge.entities.Lecteur;

public interface IDaoLecteur extends JpaRepository<Lecteur, Long> {

}
