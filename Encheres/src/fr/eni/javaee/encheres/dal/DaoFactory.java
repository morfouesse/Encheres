package fr.eni.javaee.encheres.dal;

import fr.eni.javaee.encheres.dal.articleVendu.ArticleVenduDao;
import fr.eni.javaee.encheres.dal.articleVendu.ArticleVenduDaoJdbcImpl;

public class DaoFactory {

	/*
	 * SINGLETON
	 */
	private static DaoFactory instance;
	
	public static DaoFactory getInstance() {
		if(instance == null) {
			instance = new DaoFactory();
		}
		return instance;
	}
	
	private DaoFactory() { }
	
	/*
	 * FIN SINGLETON
	 */
	
	//on peut enlever le static si on utilise le singleton
	public static ArticleVenduDao getRepasDao() {
		return new ArticleVenduDaoJdbcImpl();
	}
	
}
