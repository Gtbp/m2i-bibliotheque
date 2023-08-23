package com.m2i.filRouge.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.m2i.filRouge.converter.MyConverter;
import com.m2i.filRouge.dto.DtoEmprunt;
import com.m2i.filRouge.entities.Emprunt;
import com.m2i.filRouge.entities.Lecteur;
import com.m2i.filRouge.entities.Livre;
import com.m2i.filRouge.idao.IDaoEmprunt;
import com.m2i.filRouge.idao.IDaoLecteur;
import com.m2i.filRouge.idao.IDaoLivre;

@Service
@Transactional
public class ServiceEmprunt extends AbstractGenericService<Emprunt, Long, DtoEmprunt>  implements IServiceEmprunt {
	
	@Autowired
	IDaoEmprunt iDaoEmprunt;
	
	@Autowired
	IDaoLivre iDaoLivre;
	
	@Autowired
	IDaoLecteur iDaoLecteur;

	public ServiceEmprunt(CrudRepository<Emprunt, Long> dao) {
		super(dao);
	}

	@Override
	public Class<DtoEmprunt> getDtoClass() {
		return DtoEmprunt.class;
	}

	@Override
	public DtoEmprunt saveOrUpdateDtoEmprunt(DtoEmprunt dtoEmprunt) {
		Emprunt empruntEntity = MyConverter.map(dtoEmprunt, Emprunt.class);
		if(dtoEmprunt.getIdEmprunt() != null) {
			
			Livre livreEntity = iDaoLivre.findById(dtoEmprunt.getIdEmprunt()).get();
			Lecteur lecteurEntity = iDaoLecteur.findById(dtoEmprunt.getIdEmprunt()).get();
			
			empruntEntity.setLecteur(lecteurEntity);
			empruntEntity.setLivre(livreEntity);
				
		}
		iDaoEmprunt.save(empruntEntity);
		dtoEmprunt.setIdEmprunt(empruntEntity.getIdEmprunt());
		
		
		return dtoEmprunt;
	}


}
