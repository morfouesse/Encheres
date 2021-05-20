package fr.eni.javaee.encheres.categorie;

import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.dal.DaoFactory;

public class CategorieManager {

	public Categorie selectCategorieById(int noCategorie) {
		return DaoFactory.getCategorieDao().selectCategorieById(noCategorie);
	}
	
	
}
