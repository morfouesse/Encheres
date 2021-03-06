package fr.eni.javaee.encheres.dal;

import fr.eni.javaee.encheres.dal.articleVendu.ArticleVenduDao;
import fr.eni.javaee.encheres.dal.articleVendu.ArticleVenduDaoJdbcImpl;
import fr.eni.javaee.encheres.dal.utilisateur.UtilisateurDao;
import fr.eni.javaee.encheres.dal.utilisateur.UtilisateurDaoJdbcImpl;

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
	public static ArticleVenduDao getArticleVenduDao() {
		return new ArticleVenduDaoJdbcImpl();
	}
	
	public static UtilisateurDao getUtilisateurDao() {
		return new UtilisateurDaoJdbcImpl();
	}
	
}
