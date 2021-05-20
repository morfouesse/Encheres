package fr.eni.javaee.encheres.dal;

import fr.eni.javaee.encheres.dal.articleVendu.ArticleVenduDao;
import fr.eni.javaee.encheres.dal.articleVendu.ArticleVenduDaoJdbcImpl;
import fr.eni.javaee.encheres.dal.categorie.CategorieDao;
import fr.eni.javaee.encheres.dal.categorie.CategorieDaoJdbcImpl;
import fr.eni.javaee.encheres.dal.enchere.EnchereDao;
import fr.eni.javaee.encheres.dal.enchere.EnchereDaoJdbcImpl;
import fr.eni.javaee.encheres.dal.retrait.RetraitDao;
import fr.eni.javaee.encheres.dal.retrait.RetraitDaoJdbcImpl;
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
	
	public static EnchereDao getEnchereDao() {
		return new EnchereDaoJdbcImpl();
	}
	
	public static CategorieDao getCategorieDao() {
		return new CategorieDaoJdbcImpl();
	}
	
	public static RetraitDao getRetraitDao() {
		return new RetraitDaoJdbcImpl();
	}
	
}
