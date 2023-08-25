package com.m2i.filRouge.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.m2i.filRouge.converter.MyConverter;
import com.m2i.filRouge.dto.DtoLivre;
import com.m2i.filRouge.entities.Domaine;
import com.m2i.filRouge.entities.Livre;
import com.m2i.filRouge.idao.IDaoDomaine;
import com.m2i.filRouge.idao.IDaoLivre;

@Service
@Transactional
public class ServiceLivre extends AbstractGenericService<Livre, Long, DtoLivre> implements IServiceLivre {

	public ServiceLivre(CrudRepository<Livre, Long> dao) {
		super(dao);
	}

	@Autowired
	private IDaoLivre iDaoLivre;
	
	@Autowired
	private IDaoDomaine iDaoDomaine;
	
	@Override
	public Class<DtoLivre> getDtoClass() {
		return DtoLivre.class;
	}

	@Override
	public DtoLivre saveOrUpdateDtoLivre(DtoLivre dtoLivre) {
		Livre livreEntity = MyConverter.map(dtoLivre, Livre.class);
		if(dtoLivre.getDomaine()!=null) {
			Domaine domaineEntity = iDaoDomaine.findById(dtoLivre.getDomaine().getIdDomaine()).orElse(null);
			livreEntity.setDomaine(domaineEntity);
		}
		iDaoLivre.save(livreEntity);
		dtoLivre.setIdLivre(livreEntity.getIdLivre());
		return dtoLivre;
	}

	

	
}
