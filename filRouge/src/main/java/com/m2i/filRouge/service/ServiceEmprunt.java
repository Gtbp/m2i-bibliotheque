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
	    // Obtenez les entités Livre et Lecteur
	    Livre livreEntity = null;
	    Lecteur lecteurEntity = null;
	    
	    if (dtoEmprunt.getLivre() != null) {
	        livreEntity = iDaoLivre.findById(dtoEmprunt.getLivre().getIdLivre()).orElse(null);
	    }
	    
	    if (dtoEmprunt.getLecteur() != null) {
	        lecteurEntity = iDaoLecteur.findById(dtoEmprunt.getLecteur().getIdPersonne()).orElse(null);
	    }
	    
	    // Vérifiez la disponibilité du livre
	    if (livreEntity != null && livreEntity.getDispo()) {
	        // Créez l'entité Emprunt
	        Emprunt empruntEntity = MyConverter.map(dtoEmprunt, Emprunt.class);

	        // Associez les entités Livre et Lecteur à Emprunt
	        empruntEntity.setLivre(livreEntity);
	        empruntEntity.setLecteur(lecteurEntity);

	        // Mettez à jour la disponibilité du livre
	        livreEntity.setDispo(false);
	        
	        // Enregistrez l'entité Emprunt
	        iDaoEmprunt.save(empruntEntity);
	        
	        // Mettez à jour l'ID de DtoEmprunt
	        dtoEmprunt.setIdEmprunt(empruntEntity.getIdEmprunt());
	        
	        return dtoEmprunt;
	    } else {
	        System.err.println("Error with SaveOrUpdateDtoEmprunt: Livre not available");
	        return null;
	    }
	}

	@Override
	public Livre findLivreByIdEmprunt(Long idEmprunt) {
		// TODO Auto-generated method stub
		return iDaoEmprunt.findLivreByIdEmprunt(idEmprunt);
	}



}
