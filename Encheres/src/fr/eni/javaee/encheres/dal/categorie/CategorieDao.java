package fr.eni.javaee.encheres.dal.categorie;

import fr.eni.javaee.encheres.bo.Categorie;

public interface CategorieDao {

	Categorie selectCategorieById(int noCategorie);
	
}