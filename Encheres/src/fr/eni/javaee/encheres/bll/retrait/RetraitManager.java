package fr.eni.javaee.encheres.bll.retrait;

import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.dal.DaoFactory;

public class RetraitManager {

	public Retrait insertRetrait(Retrait retrait) {
		return DaoFactory.getRetraitDao().insertRetrait(retrait);
	}
	
	
}
