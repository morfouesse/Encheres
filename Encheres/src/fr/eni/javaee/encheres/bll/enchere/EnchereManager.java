package fr.eni.javaee.encheres.bll.enchere;

import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.dal.DaoFactory;

public class EnchereManager {

	public Enchere insertEnchere(Enchere enchere) {
		return DaoFactory.getEnchereDao().insertEnchere(enchere);
	}
	
	
}
