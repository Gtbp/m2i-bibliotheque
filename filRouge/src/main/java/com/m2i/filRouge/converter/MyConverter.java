package com.m2i.filRouge.converter;

import java.util.List;

import com.m2i.filRouge.dto.DtoEmprunt;
import com.m2i.filRouge.dto.DtoLivre;
import com.m2i.filRouge.entities.Emprunt;
import com.m2i.filRouge.entities.Livre;

public class MyConverter extends GenericConverter {

	// exemple d'utilisation : GenericConverter.map(LivreEntity,LivreDto.class)
		public static <S,D> D map(S source , Class<D> targetClass) {
			
			if(source instanceof Livre) {
				Livre sourceLivre = (Livre) source;
				DtoLivre dtoLivre = new DtoLivre(
						sourceLivre.getIdLivre(),
						sourceLivre.getTitre(),
						sourceLivre.getAuteur(),
						sourceLivre.getEditeur(),
						sourceLivre.getDispo(),
						sourceLivre.getEtat(),
						sourceLivre.getDomaine().getNom()
						);
				
			return	GenericConverter.map(dtoLivre,targetClass);
			}
			else if(source instanceof Emprunt) {
				
				Emprunt sourceEmprunt = (Emprunt) source;
				DtoEmprunt dtoEmprunt = new DtoEmprunt(
						sourceEmprunt.getIdEmprunt(),
						sourceEmprunt.getDate_debut(),
						sourceEmprunt.getDate_fin(),
						sourceEmprunt.getType(),
						sourceEmprunt.getLivre().getTitre(),
						sourceEmprunt.getLecteur().getNom()
						);
				
			
				return GenericConverter.map(dtoEmprunt,targetClass);
				
			}
				 	
			
			else 
				
				return  GenericConverter.map(source,targetClass);
		

	        
	    }
		// exemple d'utilisation : GenericConverter.map(ListeLivreEntity,LivreDto.class)
				public static <S,D> List<D> map(List<S> sourceList , Class<D> targetClass){
					return  sourceList.stream()
						   .map((source)->map(source,targetClass))
						   .toList();
				}

}
